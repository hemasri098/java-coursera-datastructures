import edu.duke.*;
/**
 * Write a description of class TestCaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestCaesarCipher
{
    public int[] countLetters(String encrypt) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int []countArray = new int[26];
        for(int i = 0 ; i < encrypt.length() ; i++) {
            int index = alpha.indexOf(Character.toUpperCase(encrypt.charAt(i)));
            if(index != -1)
                countArray[index] += 1;
        }
        return countArray;
    }
    public int maxIndex(int []occurences) {
        int maxValue = 0;
        int maxindex = 0;
        for(int i = 0 ; i < occurences.length ; i++) {
            if(maxValue < occurences[i]) {
                maxValue = occurences[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
    public String breakCaesarCipher(String input) {
        int []occurences = countLetters(input);
        int index = maxIndex(occurences);
        int decryptKey  = index - 4;
        if(index < 4) {
            decryptKey = 26 - (4 - index);
        }
        CaesarCipherOOP cipher = new CaesarCipherOOP(26 - decryptKey);
        return cipher.decrypt(input);
    }
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherOOP cipher = new CaesarCipherOOP(18);
        String encrypted = cipher.encrypt(message);
        System.out.println(encrypted);
        System.out.println(cipher.decrypt(encrypted));
        System.out.println(breakCaesarCipher(encrypted));
    }
}
