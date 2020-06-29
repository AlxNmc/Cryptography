import java.math.BigInteger;
import java.util.Scanner;

public class BBS_CSPRNG {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter plaintext bits:");
        String s_plaintext = in.nextLine();

        byte[] plaintext = processLine(s_plaintext);
        byte[] key = getKey(plaintext.length);
        byte[] ciphertext = encode(plaintext, key);

        System.out.printf("Key:\n%s\n", bytesToString(key));
        System.out.printf("Ciphertext:\n%s\n", bytesToString(ciphertext));

    }

    static byte[] getKey(int len)
    {
        byte[] result = new byte[len];

        // large primes congruent to 3 mod 4
        BigInteger p = new BigInteger("24672462467892469787");
        BigInteger q = new BigInteger("396736894567834589803");
        BigInteger n = p.multiply(q);


        // value coprime to n
        BigInteger x = new BigInteger("873245647888478349013");

        // fill x arrays with values x0 - x8
        x = x.modPow(BigInteger.TWO, n);
        int i = 0;
        for(; i < len; i++)
        {
            x = x.modPow(BigInteger.TWO, n);
            result[i] = (byte)((x.mod(BigInteger.TWO) != BigInteger.ZERO) ? 1 : 0);
        }

        return result;
    }

    static byte[] encode(byte[] plaintext, byte[] key)
    {
        byte[] ciphertext = new byte[plaintext.length];
        for(int i = 0; i < ciphertext.length; i++)
        {
            ciphertext[i] = (byte)(plaintext[i] ^ key[i]);
        }

        return ciphertext;
    }

    static byte[] processLine(String line)
    {
        char[] c_input = line.replaceAll("\\s+", "").toCharArray();
        byte[] result = new byte[c_input.length];
        for(int i = 0; i < c_input.length; i++)
        {
            result[i] = (byte)(c_input[i] != '0' ? 1 : 0);
        }

        return result;
    }

    static String bytesToString(byte[] b_arr)
    {
        String result = "";
        int i = 0;
        for(byte b: b_arr)
        {
            if(i % 4 == 0 && i != 0) result += " ";
            result += Byte.toString(b);
            i++;
        }
        return result;
    }
}
