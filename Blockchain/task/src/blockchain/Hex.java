package blockchain;

public class Hex {
    private final byte[] bytes;

    public Hex(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        StringBuilder hexString = new StringBuilder();
        for (byte elem : bytes) {
            hexString.append(byteToHex(elem));
        }
        return hexString.toString();
    }

    private static String byteToHex(byte bits) {
        String hex = Integer.toHexString(0xff & bits);
        return hex.length() < 2 ? "0" + hex : hex;
    }
}
