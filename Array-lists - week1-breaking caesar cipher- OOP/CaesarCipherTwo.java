import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CaesarCipherTwo
{
    private String alpha;
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2) 
    {
        mainKey1 = key1;
        mainKey2 = key2;
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlpha1 = alpha.substring(mainKey1) + alpha.substring(0, mainKey1);
        shiftedAlpha2 = alpha.substring(mainKey2) + alpha.substring(0, mainKey2);
    }
    public String encryptTwoKeys(String input)
    {
       String encryptedString = "";
        String inputUpperCase = input.toUpperCase();
        for(int i = 0 ; i < input.length() ; i++) 
        {
            if(Character.isLetter(inputUpperCase.charAt(i))) {
                if(i % 2 == 0) 
                {
                    if(Character.isUpperCase(input.charAt(i))) {
                        encryptedString += shiftedAlpha1.charAt(alpha.indexOf(inputUpperCase.charAt(i)));
                    }
                    else {                  
                        encryptedString += Character.toLowerCase(shiftedAlpha1.charAt(alpha.indexOf(inputUpperCase.charAt(i))));
                    }
                }
                else
                {
                    if(Character.isUpperCase(input.charAt(i))) {
                        encryptedString += shiftedAlpha2.charAt(alpha.indexOf(inputUpperCase.charAt(i)));
                    }
                    else {
                        encryptedString += Character.toLowerCase(shiftedAlpha2.charAt(alpha.indexOf(inputUpperCase.charAt(i))));
                    }
                }
            }
            else
                encryptedString += input.charAt(i);
        }
        return encryptedString;
    }
    
    public String decrypt(String input)
    {
        CaesarCipherTwo cipher = new CaesarCipherTwo(26 - 23, 26 - 17);
        return cipher.encryptTwoKeys(input);
    }
}