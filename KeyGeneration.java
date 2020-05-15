package secure;
import java.math.BigInteger;
import java.util.*;



public class KeyGeneration {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger totientN;
    private BigInteger e;
    private BigInteger d;
    public BigInteger[] publicKey = new BigInteger[2];
    public BigInteger[] privateKey = new BigInteger[2];

    private void pickLargePrime(int bitLength){
        p = BigInteger.probablePrime(bitLength, new Random());
        System.out.println("p: " + p);
        q = BigInteger.probablePrime(bitLength, new Random());
        System.out.println("q: " +q);

    }

    private void calculateN(){
        n = p.multiply(q);
        System.out.println("n:" +n);

    }

    private void calculateTotientN(){
        totientN = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        System.out.println("toientN: " +totientN);

    }

    private void selectE(int bitLength) {
        e = BigInteger.probablePrime(bitLength-1, new Random());//divded the bit length in two to see if that helps
        while ((!e.gcd(totientN).equals(BigInteger.valueOf(1))) && e.compareTo(BigInteger.valueOf(1)) == 1 && e.compareTo(totientN) == -1) {
            e = BigInteger.probablePrime(bitLength-1, new Random());
        }
        System.out.println("e:" +e);


    }

    private void calculateD(){
        d = e.modInverse(totientN);
        System.out.println("d = " + d);

    }

    public KeyGeneration(int bitLength){
        pickLargePrime(bitLength);
        calculateN();
        calculateTotientN();
        selectE(bitLength);
        calculateD();
        this.publicKey[0] = e;
        this.publicKey[1] = n;
        this.privateKey[0] = d;
        this.privateKey[1] = n;
    }


}