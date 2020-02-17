import edu.duke.*;
/**
 * Write a description of class WordLengths here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordLengths
{
    public void countWordLengths(FileResource resource, int []counts) {
      int totalWords = 0;
      for(String word : resource.words()) {
          int length = word.length();
          if(!Character.isLetter(word.charAt(0)))
            length -= 1;
          if(!Character.isLetter(word.charAt(word.length() - 1)))
            length -= 1;
          counts[length] += 1;
          totalWords += 1;
      } 
      for(int i = 0 ; i < counts.length ; i++) {
          if(counts[i] != 0)
            System.out.println(i + " " + counts[i]);
      }
      System.out.println("Index of maximum length word is " + indexOfMax(counts));
    }
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int []counts = new int[31];
        countWordLengths(fr, counts);
    }
    public int indexOfMax(int []values) { 
      int max = 0;
      int index = -1;
      for(int i = 0 ; i < values.length ; i++) {
         if(values[i] > max) {
             max = values[i];
             index = i;
         }
      }
      return index;
    }
}
