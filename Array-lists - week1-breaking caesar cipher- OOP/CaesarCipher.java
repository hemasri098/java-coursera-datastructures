import edu.duke.*;
/**
 * Write a description of class CaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CaesarCipher
{
    
    public String encrypt(String input,int key)
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encryptedString = "";
        String encryptedAlpha = alpha.substring(key) + alpha.substring(0, key);
        //System.out.println(encryptedAlpha);
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
    public void testCaesar() {
        System.out.println(encrypt("cateee!", 23));
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encryptedString = encrypt(message, 10);
        System.out.println("key is " + 10 + "\n" + encryptedString);
        System.out.println(encryptTwoKeys("First Legioneeeee", 23, 17));
    }
    public String encryptTwoKeys(String input,int key1, int key2)
    {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encryptedString = "";
        String encryptedKey1 = alpha.substring(key1) + alpha.substring(0, key1);
        String encryptedKey2 = alpha.substring(key2) + alpha.substring(0, key2);
        //System.out.println(encryptedAlpha);
        String inputUpperCase = input.toUpperCase();
        for(int i = 0 ; i < input.length() ; i++) 
        {
            if(Character.isLetter(inputUpperCase.charAt(i))) {
                if(i % 2 == 0) 
                {
                    if(Character.isUpperCase(input.charAt(i))) {
                        encryptedString += encryptedKey1.charAt(alpha.indexOf(inputUpperCase.charAt(i)));
                    }
                    else {
                        encryptedString += Character.toLowerCase(encryptedKey1.charAt(alpha.indexOf(inputUpperCase.charAt(i))));
                    }
                }
                else
                {
                    if(Character.isUpperCase(input.charAt(i))) {
                        encryptedString += encryptedKey2.charAt(alpha.indexOf(inputUpperCase.charAt(i)));
                    }
                    else {
                        encryptedString += Character.toLowerCase(encryptedKey2.charAt(alpha.indexOf(inputUpperCase.charAt(i))));
                    }
                }
            }
            else
                encryptedString += input.charAt(i);
        }
        return encryptedString;
    }
    
}