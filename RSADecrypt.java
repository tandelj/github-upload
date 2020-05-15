package secure;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;

public class RSADecrypt {

    protected BigInteger d;
    protected BigInteger n;
    protected byte[] message;
    private byte[] temp;

    public RSADecrypt(BigInteger [] privateKey, byte[] message){
        this.d = privateKey[0];
        this.n = privateKey[1];
        this.message = message;

    }


    public void decrypt(byte[] message, BigInteger d, BigInteger n) throws UnsupportedEncodingException {


       message = (new BigInteger (message)).modPow(d,n).toByteArray();//found on sanfoundry.com only part

        String finalString = new String(message);
        System.out.println("decrypted string: " + finalString);

    }




}
