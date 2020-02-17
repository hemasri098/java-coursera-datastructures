import java.lang.*;
import java.io.*;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of class CodonCount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordsInFiles
{
   private HashMap<String, ArrayList<String>> wordCounts;
   public WordsInFiles(){
      wordCounts = new HashMap<String, ArrayList<String>>();
   }
   public void addWordsFromFile(File f) {
       FileResource fr = new FileResource(f);
       for(String word : fr.words())
           if(!wordCounts.containsKey(word)) {
               ArrayList<String> filename = new ArrayList<String>();
               //System.out.println(f.getName());
               filename.add(f.getName());
               wordCounts.put(word, filename);
           }
           else {
               ArrayList<String> filename = wordCounts.get(word);
               filename.add(f.getName());
               //System.out.println();
              wordCounts.put(word, filename);
           }
    }
   public void buildWordFileMap() {
       wordCounts.clear();
      DirectoryResource dr = new DirectoryResource();
      for(File f : dr.selectedFiles()) {
       addWordsFromFile(f);
      }
   }
   public int maxNumber() {
       int max = 0;
       
       for(String key : wordCounts.keySet()) {
           if( max < wordCounts.get(key).size()) {
               max = wordCounts.get(key).size();
              
           }
       }
       return max;
    }
    public ArrayList<String> printFilesIn(int number) {
        ArrayList<String> words = new ArrayList<String>();
       for(String key : wordCounts.keySet()) {
           if(number == wordCounts.get(key).size()) {
               words.add(key);
           }
       }
       return words;
   }
   public void tester() {
       buildWordFileMap();
       System.out.println(printFilesIn(2));
       System.out.println(maxNumber());
       
   }
}
