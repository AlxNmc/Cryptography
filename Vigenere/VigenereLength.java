public class VigenereLength {

    public static void main(String[] args) {
        char[] ciphertext = ("QHDLXNQLYNGAIGWBCERJFEARNIBKXUSVGZXKYNPXXTKGAATZRQCRFYIDCCLYXHUQXEIXFAFGEAMMAL" +
                            "YRGAYXQMTGACDJSYRTLEXUVRVIYFFEGXFKOYSPHGBBYTRESOXUNTXXAKLUAWYDINAAWCZWIFVMCROI" +
                            "UCEIFJYDJAYZJBEOTMUSGAGAYYQNIPTFPYMCBOYDYYSVGWDOJTBZLMFBYJXLQCUDRRIGMIUYWMQUUF" +
                            "RPCZQHTVJOUJSMNRVQQZEJYLACNHRFCPTFENZYEJCLYMBQUCGUMYQDBUAWLQTMOAXCZJBEABHQJYEA" +
                            "MQQDNIRLNTUINRMCYUJAQTZQMGOEXUDEONQPIDBXWNKNIEUNQMBQDUFGXLFXYBVKNTEZCBFJGJUTVH" +
                            "HMBWOZIFQNCTLMBQELYVGNTUHIAXNQUHSROYZJCEFUIACVOBFVAEGBBHGNEIMOHIYRIOZQ").toCharArray();
        for(int shift = 1; shift <= 14; shift++){
            int coincidenceCount = 0;
            for(int i = 0; i < ciphertext.length; i++){
                int shiftedIndex = (i + shift) % ciphertext.length;
                if(ciphertext[i] == ciphertext[shiftedIndex]){
                    coincidenceCount++;
                }
            }
            System.out.printf("shift: %d\tcoincidences: %d\n", shift, coincidenceCount);
        }
    }
}
