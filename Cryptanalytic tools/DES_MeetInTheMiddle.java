// Alex Niemiec 2020

import java.util.ArrayList;

public class DES_MeetInTheMiddle {
    public static void main(String[] args){

        int[] plaintexts = {101, 110};
        int[] ciphertexts = {199, 2816};

        ArrayList<Integer[]> matches = SDES_MeetInTheMiddle(plaintexts, ciphertexts);

        for(Integer[] match : matches){
            System.out.printf("k1: %d \t k2: %d\n", match[1], match[0]);
        }

    }

    static ArrayList<Integer[]> SDES_MeetInTheMiddle(int[] p, int[] c){
        int[] ek = new int[512];
        int[] dk = new int[512];

        // construct encrypted and decrypted lists for every possible key
        for(int i = 0; i < 512; i++){
            ek[i] = SimpleDES.encrypt(p[0], i);
            dk[i] = SimpleDES.decrypt(c[0], i);
        }

        // construct initial list of matches
        ArrayList<Integer[]> matches = new ArrayList<>();
        for(int i = 0; i < 512; i++){
            for(int j = 0; j < 512; j++){
                if(ek[i] == dk[j]){
                    Integer[] match = {i , j};
                    matches.add(match);
                }
            }
        }

        // for each additional plaintext/ciphertext test the matches and discard false positives
        for(int i = 1; i < p.length; i++){
            for(int j = 0; j < matches.size(); j++){
                Integer[] match = matches.get(j);
                int c_test = SimpleDES.encrypt(SimpleDES.encrypt(p[i], match[0]), match[1]);
                if(c[i] != c_test){
                    matches.remove(match);
                    j--;
                }
            }
        }

        return matches;
    }

    static class SimpleDES {
        private static int[][] S1 = {
                {0b101, 0b010, 0b001, 0b110, 0b011, 0b100, 0b111, 0b000},
                {0b001, 0b100, 0b110, 0b010, 0b000, 0b111, 0b101, 0b011}
        };

        private static int[][] S2 = {
                {0b100, 0b000, 0b110, 0b101, 0b111, 0b001, 0b011, 0b010},
                {0b101, 0b011, 0b000, 0b111, 0b110, 0b010, 0b001, 0b100}
        };

        public static int encrypt(int plaintext, int key) {

            // check input
            if(plaintext < 0 || plaintext > 4095){
                System.out.println("Plaintext must be between 0 and 4095.\n");
                return -1;
            }
            else if(key < 0 || key > 511){
                System.out.println("Key must be between 0 and 511");
                return -1;
            }

            int sKeys[] = generateSubkeys(key);

            // perform encryption in 4 steps
            int L = plaintext >>> 6;
            int R = plaintext & 0b111111;
            for(int i = 0; i < 4; i++) {
                int f = applySboxes(exOR(expand(R), sKeys[i]));
                int oldR = R;
                R = exOR(L, f);
                L = oldR;
            }

            return (L << 6) | R;
        }

        public static int decrypt(int ciphertext, int key)
        {
            int sKeys[] = generateSubkeys(key);

            int R = ciphertext >>> 6;
            int L = ciphertext & 0b111111;
            for(int i = 3; i >= 0; i--) {
                int f = applySboxes(exOR(expand(R), sKeys[i]));
                int oldR = R;
                R = exOR(L, f);
                L = oldR;
            }

            return (R << 6) | L;
        }

        private static int[] generateSubkeys(int key)
        {
            int[] sKeys = new int[4];
            for (int i = 0; i < sKeys.length; i++) {
                int shift = 2 *(8) + 7 + i;
                int res = key << shift >>> shift;
                if(i == 0)
                    res >>>= 1;
                else res <<= i - 1;
                res |= key >> 10 - i;
                sKeys[i] = res;
            }
            return sKeys;
        }

        private static int exOR(int val1, int val2)
        {
            return val1 ^ val2;
        }

        private static int expand(int val) {
            int[] expansion = {1, 2, 4, 3, 4, 3, 5, 6};
            int res = 0;
            for (int i = 0; i < expansion.length; i++) {
                int shift = (3 * 8) + 1 + expansion[i];
                int exBit = val << shift >>> (shift + 6 - expansion[i]);
                res |= exBit << 7 - i;
            }
            return res;
        }

        private static int applySboxes(int val) {
            int lhs = val >>> 4;
            int rhs = val & 0b1111;

            int res = S1[lhs>>3][lhs & 0b111];
            res <<= 3;
            res |= S2[rhs>>3][rhs & 0b111];
            return res;
        }
    }
}
