package cuit9622.dms.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JWTUtils {
    // 私钥
    private static final String SECRET_KEY = "SJD(O!I@#()SKD<?X<?Z<D)P:K@_)#IO)_SI[KDL;AO)PQ@I#FKDJNFKL";

    public static final Integer EXPIRE_TIME = 3;

    /**
     * @param username 用户名
     * @return
     * @Description 创建token
     */
    public static String creatToken(String username) {

        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(UUID.randomUUID().toString())// 设置token唯一标识
                .withSubject(username) // 设置token的主体
                .withIssuer("9622")// 签发者
                .withIssuedAt(new Date()); //签发时间
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, EXPIRE_TIME);
        builder.withExpiresAt(instance.getTime());
        //签发
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * @param map      数据集合
     * @param username 用户名
     * @param expire   过期时间
     * @return
     * @Description 含有map数据的token
     */
    public static String creatToken(Map<String, Object> map, String username, Date expire) {

        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(UUID.randomUUID().toString())// 设置token唯一标识
                .withSubject(username) // 设置token的主体
                .withIssuer("9622")// 签发者
                .withIssuedAt(new Date()) //签发时间
                .withPayload(map); // 存入动态数据
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, EXPIRE_TIME);
        builder.withExpiresAt(instance.getTime());
        //签发
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * @return
     * @Description 解析token
     */
    public static DecodedJWT verify(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
    }
}
