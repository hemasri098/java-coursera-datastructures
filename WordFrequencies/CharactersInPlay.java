import java.util.*;
import edu.duke.*;

/**
 * Write a description of class CharactersInPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CharactersInPlay
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void update(String person) {
        person = person.toLowerCase();
        int index = myWords.indexOf(person);
        if (index == -1){
            myWords.add(person);
            myFreqs.add(1);
        }
        else {
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
        }
    }
    public void findAllCharacters(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            update(s);
         }
    }
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k = 0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void charactersWithNumParts(int num1, int num2) {
        for(int k = 0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) >= num1 && myFreqs.get(k) <= num2){
                System.out.println(myWords.get(k));
            }
        }
    }
    public void tester()
    {
        findAllCharacters();
        //System.out.println("# unique words: "+myWords.size());
        int index = findIndexOfMax();
        System.out.println("max word/freq: " + myWords.get(index) + " " + myFreqs.get(index));
    }
}
