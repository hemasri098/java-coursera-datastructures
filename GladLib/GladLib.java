import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    public ArrayList<String> usedWords;

    private int replacedWordsCount = 0;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("country")) {
            //replacedWordsCount += 1;
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            //replacedWordsCount += 1;
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            //replacedWordsCount += 1;
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            //replacedWordsCount += 1;
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            //replacedWordsCount += 1;
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            //replacedWordsCount += 1;
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            //replacedWordsCount += 1;
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            //replacedWordsCount += 1;
            return ""+myRandom.nextInt(50)+5;
        }
        if(label.equals("verb")){
            //replacedWordsCount += 1;
            return randomFrom(verbList);
        }
        if(label.equals("fruit")){
            //replacedWordsCount += 1;
            return randomFrom(fruitList);
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){

        //System.out.println(w);
        System.out.println(usedWords);
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

    public void makeStory(){
        usedWords = new ArrayList<String>();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        usedWords = new ArrayList<String>();
        String story2 = fromTemplate("data/madtemplate2.txt");
        printOut(story2, 60);

    }

}
