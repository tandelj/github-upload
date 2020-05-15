package secure;
import secure.OneTimeKey.*;
import java.util.Scanner;

public class TestOTK {
    public static void main (String[] args) throws Exception {
        Scanner input = new Scanner (System.in);
        System.out.println("Enter input Text: ");
        String in = input.next();
        //System.out.println("Enter number of bytes for key: ");
        int n = in.length();
        byte[] key = OneTimeKey.generateKey(n);
        System.out.println("input text: " + in);
        byte[] encrypt = OneTimeKey.encrypt(in.getBytes(), key);
        System.out.println("Generated Key: " + new String(key));
        System.out.println("Encoded text: " + new String(encrypt));
        System.out.println("Decoded text: " + new String(OneTimeKey.encrypt(encrypt, key)));

    }
}