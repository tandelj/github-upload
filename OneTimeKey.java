//Implementation Of OneTime Key
package secure;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class OneTimeKey { 
      /*public static void main(String[] args) throws Exception {
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
    }*/
    
    public static byte[] generateKey(int n) {
        byte[] bytes = new byte[n];
        Random random = new Random();
        random.nextBytes(bytes);
        return bytes;
    }

    /*
    public static void printKey(byte[] array, OutputStream outputStream) throws IOException {
        for (int i = 0; i < array.length; ++i) {
            outputStream.write(array[i]);
        }
    }*/
    
    public static byte[] encrypt(byte[] message, byte[] key) {
        //if (array.length % array2.length != 0) {
         //   throw new RuntimeException("Wrong Length for OTP");
       // }
        byte[] temp = new byte[message.length];
        System.arraycopy(message, 0, temp, 0, message.length);
        int n = 0;
        for (int i = 0; i < message.length / key.length; ++i) {
            for (int j = 0; j < key.length; ++j) {
                temp[n] ^= key[j];
                ++n;
            }
        }
        return temp;
    }
}