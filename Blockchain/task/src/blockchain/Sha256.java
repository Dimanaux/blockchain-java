package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    private final String string;

    public Sha256(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return new Hex(
                digest(string.getBytes(StandardCharsets.UTF_8))
        ).toString();
    }

    private static byte[] digest(byte[] bytes) {
        return sha256().digest(bytes);
    }
    private static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
