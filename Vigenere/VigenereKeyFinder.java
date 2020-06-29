public class VigenereKeyFinder {
    public static void main(String[] args)
    {
        int keyLength = 6;
        char[] cipherText = ("LNKNWQXCKEWAZHVJWAEORNLUPSSAITOEROVNWUIWTVVTZSIFPUTDAGUUTGE" +
                "VWHTAZGDIEBIPWSZJBUHDZOBELBLPQBQOWPPRLNYWJVWAEPABISFQBUDMVN" +
                "QPDAEZQAWHVCMBOOXEKNORVHIGLOEOWSWHVLZRVEEPBBWHVLIFWIEDIOLTR" +
                "JBFRFKDIGFOEPQAHNKPPRVEWWKGVSVAURGTFIMGRTYNWJVODATVJHKKVGKE" +
                "FNQTLNFBACHCZAAGKAKIGFWEIUWSPYJPMELEJWAVWHROJRHNTWTYHDSUWAH" +
                "OWKCEJRVWBRVTGDQYRSFLPRUSFJULUEKQZAKODAQGRCTQZEHDKKURLNKDIG" +
                "VODABULNXIQTKTGAZUDPJXMZDDVKCGRNKDQFTUVOBVRNSUXNWIVJBYBATYC" +
                "ZXLRPQAJAEZZRILVYBVQGFJIYOSFNBFRFWWKGVWYEKUFOLHLCRSJEJYBHRR" +
                "MNQYSAIELNXKVVWAWPMEIIMAGRDRJSWENIRHTBZEUIGFHLWPWFSETQTNWEF" +
                "JBUHSLXRRFTRJLQUENQXFRMVOPBUTEKBRVTYAARLEEHIEJEUEVVQTFWAXHT" +
                "TDWSWHVYWAFLLOQBQSNDQPKTYAVFHEDALGRMVLZBEASHMSUODPPNWPVNQBG" +
                "TFPPRSRVOMAWDRUQUDVVOBRDDZHGCXRJQMQWHVOIZHOSFMPWIYKXRWHRPQZ" +
                "DYSAMKFUJALSRRVJBRUIECWAWHVOMCHRJKVNODVPIVOSROQTLVVPPRPTFOP" +
                "BZTYWBVKAMAVBWBVAVUDSKUQAFODEVTWORZMPLSZKVZBWFNSVVNFSVRDRCU" +
                "NVQIJDMQEUKWAVWWZHTGDKVIMGZOFNBUUEVIWEHYVWZFWOTKUCOEKAQGDNU" +
                "WAZBHVWTGKIJBIEIRFIAGUOECQUDVVXMRQUICMQWOGQJYLSYPPVVASOBEDC" +
                "KEPNYEDKZRHSGAKVDLCUJRHNZJLHFEUPWQRTYEANVMISIYOATAEURIJJWJV" +
                "TLZGVQGKDMADTLNIYKIJPWEBOWPPRPACWGNUCYEXROAXKPNVAINQIHDRPIY" +
                "POJPMKDCKHGGKEJWURJEEAZNOCFJKYXSZKVFWHRPQUDVVKVGKEFNQTLNFBA" +
                "CHCZAAYDSKUMNUHVOMAWTFIMNPEDKQERNKDQFVUSFMPWWZPPNUEHQMFWTYW" +
                "BVZOLHLSRRNWZQLTKKAVUCYWZYHSCUMYOWYKARQTZPBBWHVHQAQERJABFIV" +
                "PGNQDZPQFSUSHQFKEUEVGKEKDQEGVFHCZHOWPPRMOLNVNOOWPPNWSFYQRWY" +
                "JEZPOYVHTNQDUNPBRKVNEURBFPPXQENKNZBWFNSGKECWBGHRYWDVQGIAIQP" +
                "YJGMGFHFBPBQOLNMQPESUBULNBEVTLTRZDVVASHMGRPLXTVVHNEBUPRNWTY" +
                "DCVOMKFECHMAWMVIWVUSFIMOUIVBMKWRRYBFIRFIULPAEQAPUIGPAGKIJWJ" +
                "FWRRYBJKITDQARWGQJYLSYICFWNVYMFVAIETLEEZIXRUFVYBVFAEJWGKEIA" +
                "OVYEIANRUEEYMFDNUWCGKOIEBVHSWKZZBSVRMEDLJPIGHMVJBFDNUEUHVTK" +
                "NCFWTFPPRUERZMEUEGKAVQGJKURFOEBQQHNTAQAPYRYKHUATUVBGOLXBRUR" +
                "FNAJLLCDIIHCIAXGLNKDWHJHZDWCHIYWDRDLNWGFEEVJKNXTZKCFLNKNCFW" +
                "IECBBJOFZIHWHFNQGLEJWTBQEZYIAKEIAOVYEFJTLWHVCMAHRRHKBQCCQAV" +
                "RNJWBJKITDQUDVVWZELVVZEVWHRBMJIATPAVQICHCFWRRPQBQBLPEULCYEP" +
                "BSEZJUBVTTWARVWZHTFXFWEKRQOFJMPDNWAMYPOIAARQSZXTRWHRJQQROWP" +
                "PRQETAAFLTPKNUHRVWNGHRGQJYLSYEVTLNUABNLLRHTGKEWWKGVWZPPEHFV" +
                "NMAFEJKVJKITDULFOEYTHVIFJAUDVVXMRQGIKCAGEUWVQLHFLMVQAWQBHUE" +
                "NKZXWOUKBULSWKZVDMNATYDWRNMGKAKOKNUCVHGNVIECTRSOZJBVVDZOKHV" +
                "SVZQAWHZODBOUDAWAZHZYPSDCKOKNQNFPJRDDUQKRGOWPMADPGWZRQTCUTR" +
                "DDZJOGRCFJKYXSZKVFGIIAKGOYFLXBVIKABBWHFOMNWWYEKULHRRMNURZRM" +
                "QDFREZEHSLHBPDNSAWOWAZJMQRNCUJLIUCHGFWAKEVTDNUXIYDNTEVTWHVB" +
                "IPWSRJLNUGLIMAWSFJJBWHJELRVOWAIPKQLAAGLOEWVQWHZOKNQNFPXBVSZ" +
                "XTLEEYAZRGOEA").toCharArray();

        // letter frequnecies for English language
        float A[] = new float[]{0.082f, 0.015f, 0.028f, 0.043f, 0.127f, 0.022f, 0.020f,
                                0.061f, 0.070f, 0.002f, 0.008f, 0.040f, 0.024f, 0.067f,
                                0.075f, 0.019f, 0.001f, 0.060f, 0.063f, 0.091f, 0.028f,
                                0.010f, 0.023f, 0.001f, 0.020f, 0.001f};

        float W[] = new float[26];
        char key[] = new char[keyLength];
        for(int i = 0; i < keyLength; i++)
        {
            // compute frequencies for letters i mod keyLength
            int count = 0;
            for(int j = i; j < cipherText.length; j+=keyLength) {
                W[cipherText[j] - 65]++;
                count++;
            }
            for(int j = 0; j < W.length; j++) W[j] /= count;

            // get dot product of W and A for shifts 0 to 25
            float DP[] = new float[26];
            for(int shift = 0; shift < 26; shift++)
            {
                float dp = 0;
                for(int j = 0; j < W.length; j++)
                {
                    float aVal = A[(26 + j - shift)%26];
                    dp += W[j] * aVal;
                }
                DP[shift] = dp;
            }

            // find the shift with highest dot product
            float max = 0;
            for(int j = 0; j < DP.length; j++)
            {
                if(DP[j] > max)
                {
                    max = DP[j];
                    key[i] = (char)(65 + j);
                }
            }
        }
        for(char c: key) System.out.print(c);
    }

}
