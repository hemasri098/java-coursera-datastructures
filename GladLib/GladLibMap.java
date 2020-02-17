import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    // private ArrayList<String> adjectiveList;
    // private ArrayList<String> nounList;
    // private ArrayList<String> colorList;
    // private ArrayList<String> countryList;
    // private ArrayList<String> nameList;
    // private ArrayList<String> animalList;
    // private ArrayList<String> timeList;
    // private ArrayList<String> verbList;
    // private ArrayList<String> fruitList;
    private ArrayList<String> usedWords;

    private int replacedWordsCount = 0;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    private void initializeFromSource(String source) {
        String []categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe"};
        //myMap.put();      
        for(int i = 0 ; i < categories.length ; i++ ) {
            String name = source + "/" + categories[i] + ".txt";
            myMap.put(categories[i], readIt(name));
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        for(String key : myMap.keySet()) {
            if(key.equals(label)) {
                return randomFrom(myMap.get(key));
            }
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        //System.out.println(w);
        //System.out.println(usedWords);
        
        if(!usedWords.contains(w)) {
            usedWords.add(w);
            int first = w.indexOf("<");
            int last = w.indexOf(">",first);
            if (first == -1 || last == -1){
                return w;
            }
            String prefix = w.substring(0,first);
            String suffix = w.substring(last+1);
            String sub = getSubstitute(w.substring(first+1,last));
            replacedWordsCount += 1;
            System.out.println(replacedWordsCount);
            return prefix+sub+suffix;
        }
        return "";
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        
        return list;
    }
    public int totalWordsInMap() {
        int words = 0;
        for(String key : myMap.keySet()) {
           words += myMap.get(key).size();
        }
        return words;
    }
    public int totalWordsConsisdered() {
        GladLib gl = new GladLib();
        ArrayList<String> usedWords = gl.usedWords;
        int words = 0;
        for(String word : usedWords) {
            if(!myMap.containsKey(word))
                words += myMap.get(word).size();
        }
        return words;
    }
    public void makeStory(){
        usedWords = new ArrayList<String>();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println(totalWordsInMap());
        System.out.println(totalWordsConsisdered());
        usedWords = new ArrayList<String>();
        String story2 = fromTemplate("data/madtemplate2.txt");
        printOut(story2, 60);
        System.out.println(totalWordsInMap());
        System.out.println(totalWordsConsisdered());
    }

}

