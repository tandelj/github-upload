package secure;
import secure.Hash.*;
import java.util.Scanner;

class TestHash {
    public static void main (String[] args) throws Exception {
        Scanner input = new Scanner (System.in);
        System.out.println("Enter Number of dataBytes: ");
        int n1 = input.nextInt();
        System.out.println("Enter Number of checksum bytes: ");
        int n2 = input.nextInt();
        System.out.println("Enter Hash Function byte Pattern: ");
        byte b1 = input.nextByte();
        System.out.println("Enter Hash Function k: ");
        int n3 = input.nextInt();
        System.out.println("Enter the String: ");
        String in = input.next();
        Hash myHash = new Hash();
        byte[] pack = myHash.pack(in.getBytes(),n1,n2,b1,n3);
        System.out.println("Packed Bytes: " + new String(pack));
        byte[] unpack = myHash.unpack(pack, n1, n2, b1, n3);
        System.out.println("UnPacked Bytes: " + new String(unpack));
    }
};