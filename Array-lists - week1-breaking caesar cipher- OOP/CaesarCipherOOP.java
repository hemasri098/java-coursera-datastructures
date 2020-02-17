import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CaesarCipherOOP
{
    private String alpha;
    private String encryptedAlpha;
    private int mainKey;
    public CaesarCipherOOP(int key) {
        mainKey = key;
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        encryptedAlpha = alpha.substring(key) + alpha.substring(0, key);
    }
    public String encrypt(String input)
    {
        String encryptedString = "";
        String inputUpperCase = input.toUpperCase();
        for(int i = 0 ; i < input.length() ; i++) {
            if(Character.isLetter(inputUpperCase.charAt(i))) {
                if(Character.isUpperCase(input.charAt(i))) {
                    encryptedString += encryptedAlpha.charAt(alpha.indexOf(inputUpperCase.charAt(i)));
                }
                else {
                    encryptedString += Character.toLowerCase(encryptedAlpha.charAt(alpha.indexOf(inputUpperCase.charAt(i))));
                }
            }
            else
                encryptedString += input.charAt(i);
        }
        return encryptedString;
    }
    public String decrypt(String encryptedMessage)
    {
        CaesarCipherOOP cipher = new CaesarCipherOOP(26 - mainKey);
        return cipher.encrypt(encryptedMessage);
    }
}