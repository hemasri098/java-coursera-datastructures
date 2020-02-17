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
public class CodonCount
{
   private HashMap<String, Integer> dnaCounts;
   public CodonCount(){
      dnaCounts = new HashMap<String, Integer>();
   }
   public void buildCodonMap(int start, String dna) {
       for(int i = start ; i + 2 < dna.length() ; i = i + 3) {
           String codon = dna.substring(i, i + 3);
           if(!dnaCounts.containsKey(codon)) {
               dnaCounts.put(codon, 1);
           }
           else {
               dnaCounts.put(codon, dnaCounts.get(codon) + 1);
           }
       }

   }
   public void uniqueCodons() {
      for(String key : dnaCounts.keySet()) {
          System.out.println("Codon = " + key + " and count = " + dnaCounts.get(key));
       } 
   }
   public String getMostCommon() {
       int max = 0;
       String commonDNA = "";
       for(String key : dnaCounts.keySet()) {
           if( max < dnaCounts.get(key)) {
               max = dnaCounts.get(key);
               commonDNA = key;
           }
       }
       return commonDNA;
    }
    public void printCodonCounts(int start, int end) {
       for(String key : dnaCounts.keySet()) {
           if(start <= dnaCounts.get(key) && end >= dnaCounts.get(key)) {
               System.out.println("Codon = " + key + " and count = " + dnaCounts.get(key));
           }
       }
   }
   public void tester() {
       //FileResource fr = new FileResource();
       //String s = fr.asString();
       //s = s.toUpperCase();
       String s = "CGTTCAAGTTCAA";
       System.out.println("First Reading");
       buildCodonMap(0, s);
       uniqueCodons();
       printCodonCounts(1, 5);
       System.out.println("Second Reading");
       buildCodonMap(1, s);
       uniqueCodons();
       printCodonCounts(1, 5);
       System.out.println("Third Reading");
       buildCodonMap(2, s);
       uniqueCodons();
       printCodonCounts(1, 5);
   }
}
