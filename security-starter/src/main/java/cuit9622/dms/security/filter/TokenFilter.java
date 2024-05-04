package cuit9622.dms.security.filter;

import cuit9622.dms.common.enums.ErrorCodes;
import cuit9622.dms.common.model.CommonResult;
import cuit9622.dms.common.model.DMSUserDetail;
import cuit9622.dms.common.util.JWTUtils;
import cuit9622.dms.common.util.RedisUtils;
import cuit9622.dms.common.util.ServletUtil;
import cuit9622.dms.security.util.SecurityUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class TokenFilter extends OncePerRequestFilter {
    @Resource
    RedisTemplate<String, List<String>> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            try {
                Long userId = JWTUtils.getUserId(token);
                DMSUserDetail userDetails = new DMSUserDetail();
                userDetails.setID(userId);
                List<String> authorities = redisTemplate
                        .opsForValue().get(RedisUtils.PERMISSIONS_KEY + userId);
                //将验证过后的用户信息放入context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, SecurityUtil.convertStringToGrantedAuthority(authorities));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                CommonResult<?> commonResult = CommonResult.error(ErrorCodes.FORBIDDEN);
                ServletUtil.writeJSON(response, commonResult);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
