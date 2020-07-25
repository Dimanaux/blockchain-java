import java.io.CharArrayWriter;
import java.io.IOException;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        // implement the method
//        return String.join("", words).toCharArray();
        // using CharArrayWriter
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        for (String word : words) {
            charArrayWriter.write(word);
        }
        return charArrayWriter.toCharArray();
    }
}
