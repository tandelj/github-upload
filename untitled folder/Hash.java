//Implementation Of Hash Function
//Calculates CheckSum, pack and unpack the data

package secure;

import java.math.BigInteger;

public class Hash {

    private int nDatabytes;
    private int nCheckbytes;
    private byte pattern;
    private int k;

    public Hash() {
        nDatabytes = 0;
        nCheckbytes = 0;
        pattern = 0;
        k = 0;
    }
    public Hash(int i1, int i2, byte b1, int i3) {
        nDatabytes = i1;
        nCheckbytes = i2;
        pattern = b1;
        k = i3;
    }
    /*public int getNumberOfDataBytes() {
        return nDatabytes;
    }
    public int getPacketSize() {
        return nDatabytes + this.nCheckbytes + 1;
    }*/
    /*public static void main (String[] args) throws Exception {
        if(args.length < 5 ){
            System.out.println("java secure.Hash <databytes> <checkbytes> <pattern> <k> <text> [ <text> ... ]");
            System.exit(1);
        }
        //final Hash h = new Hash(13, 23, (byte)3, 3);
        //System.out.println(h.getNumberOfDataBytes());
        //System.out.println(args.length);
        int i1 = Integer.parseInt(args[0]);
        int i2 = Integer.parseInt(args[1]);
        byte b1 = (byte)Integer.parseInt(args[2]);
        int i3 = Integer.parseInt(args[3]);
        //pack and unpack all the text inputs
        for (int i = 4; i < args.length; ++i) {
            byte[] packed = pack(args[i].getBytes(), i1, i2, b1, i3);
            System.out.println("packed Bytes: ");
            System.out.println(new String(packed));
            //System.out.println(packed);
            System.out.println("unpacked Bytes");
            byte[] str = unpack(packed, i1, i2, b1, i3);
            System.out.println(new String (str));
        }
        
    }*/
    public static byte[] pack (byte[] text, int n, int n2, byte b, int n3) throws Exception{
        if (n > 256) {
            throw new Exception("Maximum DataByte size is 255.");
        }
        int length = text.length;
        int n4 = n + n2 + 1;
        int n5 = (length % n == 0) ? (length / n) : (length / n + 1);
        byte[] ptext = new byte[n5 * n4];
        int n6 = 0;
        for (int i = 0; i < n5; ++i) {
             byte b2 = (byte)(((i + 1) * n > length) ? (length % n) : n);
            ptext[i * n4] = b2;
            BigInteger bigInteger = BigInteger.valueOf(0L);
            for (byte b3 = 0; b3 < b2; ++b3) {
                byte b4 = text[n6];
                ++n6;
                bigInteger = bigInteger.add(BigInteger.valueOf((b & b4) * n3));
                ptext[i * n4 + b3 + 1] = b4;
            }
            BigInteger mod = bigInteger.mod(BigInteger.valueOf((int)Math.pow(2.0, 8 * n2)));
            int length2 = mod.toByteArray().length;
            for (int j = 0; j < n2; ++j) {
                if (n2 - j > length2) {
                    ptext[i * n4 + n + j + 1] = 0;
                }
                else {
                    ptext[i * n4 + n + j + 1] = mod.toByteArray()[j - (n2 - length2)];
                }
            }
        }
        return ptext;
    }

    public static byte[] unpack(byte[] ptext, int n, int n2, byte b, int n3) throws Exception{
        if (n > 256) {
            throw new Exception("Maximum Databyte Size is 256");
        }
        int length = ptext.length;
        int n4 = 1 + n + n2;
        if (length % n4 != 0) {
            throw new Exception("Wrong Packet Size");
        }
        int n5 = length / n4;
        int n6 = 0;
        for (int i = 0; i < n5; ++i) {
            n6 += ptext[i * n4];
        }
        byte[] uptext = new byte[n6];
        int j = 0;
        int n7 = 0;
        int n8 = 0;
        while (j < n5) {
            byte b2 = ptext[j * n4];
            BigInteger bigInteger = BigInteger.valueOf(0L);
            ++n7;
            for (byte b3 = 0; b3 < b2; ++b3) {
                byte b4 = ptext[n7];
                ++n7;
                bigInteger = bigInteger.add(BigInteger.valueOf((b4 & b) * n3));
                uptext[n8] = b4;
                ++n8;
            }
            if (b2 < n) {
                n7 += n - b2;
            }
            BigInteger mod = bigInteger.mod(BigInteger.valueOf((int)Math.pow(2.0, 8 * n2)));
            int length2 = mod.toByteArray().length;
            for (int k = n2 - length2; k < n2; ++k) {
                if (k >= 0 && ptext[j * n4 + n + k + 1] != mod.toByteArray()[length2 - n2 + k]) {
                    throw new Exception("wrong checksum");
                }
            }
            n7 += n2;
            ++j;
        }
        return uptext;
    }
}