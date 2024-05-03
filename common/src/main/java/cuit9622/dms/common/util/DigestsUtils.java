package cuit9622.dms.common.util;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.mysql.cj.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DigestsUtils {

    public static final DigestAlgorithm ALGORITHM = DigestAlgorithm.SHA1;

    // 迭代次数
    public static final Integer ITERATIONS = 520;

    /**
     * @param text 明文
     * @param salt 加密盐
     * @return 密文
     * @Description 讲明文根据盐值进行SHA-1加密
     */
    public static String sha1(String text, String salt) {
        Digester digester = new Digester(ALGORITHM);
        digester.setDigestCount(ITERATIONS);
        digester.setSalt(salt.getBytes());
        return digester.digestHex(text);
    }

    /**
     * @return 盐值
     * @Description 随机生成加密盐值
     */
    public static String createSalt() {
        return BCrypt.gensalt();
    }

    /**
     * @param text 明文
     * @return map集合
     * @Description 返回map，map里有密文和加密盐值
     */
    public static Map<String, String> encrypt(String text) {
        Map<String, String> map = new HashMap<>();
        String salt = createSalt();
        String password = sha1(text, salt);
        map.put("salt", salt);
        map.put("password", password);
        return map;
    }

    // 匹配密码是否正确
    public static boolean matches(String password, String salt, String cryptPassword) {
        if (StringUtils.isNullOrEmpty(password)) {
            return false;
        }
        String sha1 = sha1(password, salt);
        if (Objects.equals(sha1, cryptPassword)) {
            return true;
        }
        if (sha1 == null || cryptPassword == null) {
            return false;
        }
        if (sha1.length() != cryptPassword.length()) {
            return false;
        }
        final int length = sha1.length();
        for (int i = 0; i < length; i++) {
            if (sha1.charAt(i) != cryptPassword.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}