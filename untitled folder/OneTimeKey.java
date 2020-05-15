//Implementation Of OneTime Key
package secure;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class OneTimeKey { 
      public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("java secure.OneTimeKey <key>  <text> [ <text> ... ]");
            System.exit(1);
        }
        byte[] bytes = args[0].getBytes();
        for (int i = 1; i < args.length; ++i) {
            System.out.println("original text: " + args[i]);
            byte[] encrypt = encrypt(args[i].getBytes(), bytes);
            System.out.println("Encoded text: " + new String(encrypt));
            System.out.println("Decoded text: " + new String(encrypt(encrypt, bytes)));
        }
    }
    public static byte[] newKey(int n) {
        return newKey(new Random(), n);
    }
    
    public static byte[] newKey(Random random, int n) {
        byte[] bytes = new byte[n];
        random.nextBytes(bytes);
        return bytes;
    }
    
    public static void printKey(byte[] array, OutputStream outputStream) throws IOException {
        for (int i = 0; i < array.length; ++i) {
            outputStream.write(array[i]);
        }
    }
    
    public static byte[] encrypt(byte[] array, byte[] array2) {
        if (array.length % array2.length != 0) {
            throw new RuntimeException("Wrong Length for OTP");
        }
        byte[] array3 = new byte[array.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        int n = 0;
        for (int i = 0; i < array.length / array2.length; ++i) {
            for (int j = 0; j < array2.length; ++j) {
                array3[n] ^= array2[j];
                ++n;
            }
        }
        return array3;
    }
}