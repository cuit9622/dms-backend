package cuit9622.dms.sys.tools;

import cuit9622.dms.common.util.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AuthTools {

    @Resource
    RedisTemplate<String, Objects> redisTemplate;

    public void deleteByUserId(List<Long> ids) {
        for (Long id : ids) {
            redisTemplate.delete(RedisUtils.TOKEN_KEY + id);
            redisTemplate.delete(RedisUtils.PERMISSIONS_KEY + id);
        }
    }
}
