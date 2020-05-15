package secure;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Arrays;

public class RSAEncrypt {
    protected BigInteger e;
    protected BigInteger n;
    protected String message;
    protected byte [] temp;
    protected String encryptedMessage;

    public RSAEncrypt(BigInteger[] publicKey, String message){
        this.message = message;
        this.e = publicKey[0];
        this.n = publicKey[1];
    }

    protected void encrypt(BigInteger e, BigInteger n, String message) throws UnsupportedEncodingException {
        /*
        msgbytes takes the conversion of the String message to a byte array of type charset UTF 16 Big
         */
        byte []  msgbytes = message.getBytes();//this takes the string message and converts to a byte array named msgbytes
        System.out.println("What the array of bytes looks like");
        for(int i = 0; i < msgbytes.length ; i++){
            System.out.print(msgbytes[i]);
        }
        System.out.println();
        temp = (new BigInteger(msgbytes)).modPow(e,n).toByteArray();//this allows us to add the modPow to the byte array then back to a byte array found online www.sanfoundry.com





//        String t = "";
//        for(byte b : msgbytes){//takes the byte array and turns it into a string
//            t += Byte.toString(b);
//        }
//        System.out.println(t);
//        System.out.println();



    }








}
