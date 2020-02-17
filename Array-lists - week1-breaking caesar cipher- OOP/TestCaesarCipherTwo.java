import edu.duke.*;
/**
 * Write a description of class TestCaesarCipherTwo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class TestCaesarCipherTwo
{
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public int[] countLetters(String encrypt) {
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
    public String halfOfString(String message, int start) {
        String halfString = "";
        for(int i = start ; i < message.length() ; i = i + 2) {
            halfString += message.charAt(i);
        }
        return halfString;
    }
    public int getKey(String s) {
        int []occurences = countLetters(s);
        int maxindex = maxIndex(occurences);
        int decryptKey  = maxindex - 4;
        if(maxindex < 4) {
            decryptKey = 26 - (4 - maxindex);
        }
        return decryptKey;
    }
    public String breakCaesarCipher(String encrypted) {
        String halfOfString_1 = halfOfString(encrypted, 0);
        String halfOfString_2 = halfOfString(encrypted, 1);
        int decryptKey1 = getKey(halfOfString_1);
        int decryptKey2 = getKey(halfOfString_2);
        CaesarCipherTwo cipher = new CaesarCipherTwo(decryptKey1, decryptKey2);
        return cipher.decrypt(encrypted);
    }
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //System.out.println(breakCaesarCipher("HDFIEHFHRFHWEE"));
        //System.out.println(breakCaesarCipher("dhhheehehhhee"));
        System.out.println(breakCaesarCipher(message));
    }
}
