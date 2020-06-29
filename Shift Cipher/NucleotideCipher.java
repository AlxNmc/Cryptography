import java.util.HashMap;
import java.util.Scanner;

public class NucleotideCipher {

    static char[] alphabet = {'A', 'C', 'G', 'T'};
    static HashMap<Character, Integer> char_map = new HashMap<>();

    public static void main(String[] args) {
        // populate character map
        for(int i = 0; i < alphabet.length; i++) {
            char_map.put(alphabet[i], i);
        }

        // plaintext and shift value from pg 60 problem 6.a
        String s_plaintext = "GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCTCTAGAACT";
        char[] plaintext = s_plaintext.toCharArray();
        int shift = 1;

        // print plaintext and encode into ciphertext
        System.out.println("---------- Problem 6.a: shift cipher with shift of 1 ----------");
        System.out.printf("\nplaintext:  %s\n", s_plaintext);
        char[] ciphertext = shift(plaintext, shift);

        // print ciphertext
        System.out.printf("ciphertext: %s\n\n", String.copyValueOf(ciphertext));

        //-----------------------------------------------------------------------------------

        System.out.println("---------- Problem 6.b: affine cipher  ----------");
        // get user input for affine cipher
        Scanner in = new Scanner(System.in);
        char[] userText = getAffinePlaintext(in);
        int a = getAddKey(in);
        int m = getMultKey(in);

        char[] affineCiphertext = affineShift(userText, a, m);
        System.out.print("\nciphertext: ");
        for(char c: affineCiphertext) System.out.print(c);
    }

    static char[] shift(char[] plaintext, int shift){
        char[] result = new char[plaintext.length];
        for(int i = 0; i < plaintext.length; i++) {
            result[i] = alphabet[(char_map.get(plaintext[i])+shift) % alphabet.length];
        }
        return result;
    }

    static char[] affineShift(char[] plaintext, int a, int m){
        char[] result = new char[plaintext.length];
        for(int i = 0; i < plaintext.length; i++){
            int val = char_map.get(plaintext[i]);
            result[i] = alphabet[(val * m + a) % alphabet.length];
        }
        return result;
    }

    // gets multiplicative key for aphine cipher and checks if valid (co-prime with 4)
    static int getMultKey(Scanner in)
    {
        while(true){
            System.out.print("Enter multiplicative key: ");
            int val = in.nextInt();
            if(val != 1 && val != 3)
            {
                System.out.println("Number must be co-prime with and less than 4.");
            } else {
                return val;
            }
        }
    }

    // gets additive key for aphine cipher
    static int getAddKey(Scanner in)
    {
        System.out.print("Enter additive key: ");
        return in.nextInt();
    }

    static char[] getAffinePlaintext(Scanner in)
    {
        while(true) {
            System.out.print("Enter plaintext: ");
            char[] userText = in.nextLine().toUpperCase().toCharArray();
            Boolean correct = false;
            for (char c : userText) {
                if(!(correct = char_map.containsKey(c))){ break;}
            }
            if(correct) {
                return userText;
            } else {
                System.out.println("Please use the nucleotide alphabet only.");
            }
        }
    }

}
