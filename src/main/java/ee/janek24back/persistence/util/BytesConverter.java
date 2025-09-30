package ee.janek24back.persistence.util;

import java.nio.charset.StandardCharsets;

public class BytesConverter {

    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] stringToBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }


}
