package secure;
import java.util.*;


public class Test {

    public static String message;
    public static int bitLength;


    public static void main(String[] args){

        Scanner sizeOfBitLength = new Scanner(System.in);
        System.out.println("Please enter bit length size: ");
        bitLength = sizeOfBitLength.nextInt();
        KeyGeneration keys = new KeyGeneration(bitLength);
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a message: ");
        message = input.nextLine();
        RSAEncrypt messageEncrypt = new RSAEncrypt(keys.publicKey, message);


        try {
            messageEncrypt.encrypt(messageEncrypt.e, messageEncrypt.n, messageEncrypt.message);

        }catch(Exception e){
            e.printStackTrace();
        }

        RSADecrypt messageDecrypt = new RSADecrypt(keys.privateKey, messageEncrypt.temp);

        try {
            messageDecrypt.decrypt(messageDecrypt.message,messageDecrypt.d, messageDecrypt.n);

        }catch(Exception e){
            e.printStackTrace();
        }



    }


}
