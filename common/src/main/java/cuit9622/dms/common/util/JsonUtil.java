package cuit9622.dms.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String toJsonString(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T parseJson(String jsonStr, Class<T> clazz) {
        return objectMapper.readValue(jsonStr, clazz);
    }
}
