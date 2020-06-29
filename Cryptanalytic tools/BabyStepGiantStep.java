// Alex Niemiec 2020

import java.math.BigInteger;

public class BabyStepGiantStep {
    public static void main(String[] args){
        int p = 595117;
        int alpha = 1002;
        int alpha_x = 437083;

        int x = breakDiscreteLog(p, alpha, alpha_x);

        System.out.printf("x = %d / %s\n", x, intToAcronym(x));
    }

    public static int breakDiscreteLog(int p, int alpha, int alpha_x){
        BigInteger b_p = new BigInteger(Integer.toString(p));
        BigInteger b_alpha = new BigInteger(Integer.toString(alpha));
        BigInteger b_alpha_x = new BigInteger(Integer.toString(alpha_x));

        int N = (int)Math.sqrt(p - 1) + 1;
        BigInteger[] l1 = new BigInteger[N];
        BigInteger[] l2 = new BigInteger[N];

        for (int i = 0; i < l1.length; i++) {
            l1[i] = b_alpha.modPow(new BigInteger(Integer.toString(i)), b_p);
            int ex = -N * i;
            l2[i] = (b_alpha.modPow(new BigInteger(Integer.toString(-N * i)), b_p))
                    .multiply(b_alpha_x)
                    .mod(b_p);
        }

        int x = 0;
        for(int j = 0; j < l1.length;j++){
            for(int k = 0; k < l2.length; k++){
                if(l1[j].equals(l2[k])){
                    return j + N * k;
                }
            }
        }
        return 0;
    }

    private static String intToAcronym(int n){
        String n_s = Integer.toString(n);
        char[] letters = new char[n_s.length() / 2];
        for (int i = 0; i < n_s.length(); i+=2) {
            letters[i/2] = (char)(Integer.parseInt(n_s.substring(i, i+2)) + 65);
        }
        return new String(letters);
    }
}
