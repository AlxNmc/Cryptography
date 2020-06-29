import java.math.BigInteger;
import java.util.Random;

public class PrimeNumbers {

    static boolean flt_test(long p, int k)
    {
        BigInteger P = new BigInteger(Long.toString(p));
        for(int i = 0; i < k; i++)
        {
            long a = (long)((Math.random() * ((p - 4) + 1) + 2));
            BigInteger A = new BigInteger(Long.toString(a));
            BigInteger pow = A.modPow(P.subtract(BigInteger.ONE), P);
            if(!pow.equals(BigInteger.ONE)) return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        Random rand = new Random();
        double max = Math.pow(2, 25);
        double min = Math.pow(2, 24);
        long p;
        for(int i = 0; i < 16; i++)
        {
            int j = 0;
            do
            {
                p = (long)((Math.random() *((max-min) +1) + min));
                j++;
            } while(!flt_test(p, 12));

            System.out.printf("%d.\tLoops: %d\tPrime: %d\n", i+1, j, p);
        }
    }
}
