package cuit9622.dms.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import cuit9622.dms.security.model.DMSUserDetail;
import cuit9622.dms.common.util.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenUtil {
    private static final String ISSUER = "cuit9622";
    private static final Algorithm algorithm = Algorithm.HMAC256("nwgaG3wHKECDuw82RRvCKJTtmv3SEjGEVs9e0upK5RivEscDJgXr65U7wfpT2Ii5");
    private static final JWTCreator.Builder builder = JWT.create();
    private static final JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();

    public static String createAccessToken(DMSUserDetail userDetail) {
        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.HOUR, 2); //访问Token有效期设为2小时
        builder.withJWTId(UUID.randomUUID().toString())// 设置token唯一标识
                .withSubject(JsonUtil.toJsonString(userDetail)) // 设置token的主体
                .withIssuer(ISSUER)// 签发者
                .withIssuedAt(new Date()) //签发时间
                .withExpiresAt(expireTime.getTime()); //设置过期时间
        return builder.sign(algorithm);
    }

    public static DMSUserDetail decodeAccessToken(String token) {
        String userJson = verifier.verify(token).getSubject();
        return JsonUtil.parseJson(userJson, DMSUserDetail.class);
    }
}
