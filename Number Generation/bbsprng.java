import java.math.BigInteger;

public class bbsprng {

    public static void main(String[] args) {

        // large primes congruent to 3 mod 4
        BigInteger p = new BigInteger("24672462467892469787");
        BigInteger q = new BigInteger("396736894567834589803");
        BigInteger n = p.multiply(q);

        // value coprime to n
        BigInteger x = new BigInteger("873245647888478349013");
        BigInteger x_arr[] = new BigInteger[9];

        // fill x arrays with values x0 - x8
        x_arr[0] = x.modPow(BigInteger.TWO, n);
        for(int i = 1; i < x_arr.length; i++)
        {
            x_arr[i] = x_arr[i-1].modPow(BigInteger.TWO, n);
        }

        // print x values
        for(BigInteger i: x_arr) System.out.println(i);
    }
}
