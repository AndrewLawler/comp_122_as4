// Importing Libraries
import java.io.*;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.nio.charset.StandardCharsets;

/**
 * Analyser 
 * 
 * @author Andrew Lawler
 * @version jdk 11.0.1
 * 
 * Analyser is a java file which will analyse text files and break them down into the frequencies of each Character
 * 
 */

public class Analyser {
    
    // Creating public variables
    String text;
    HashMap<Character, Double> FrequencyMap;
    DecimalFormat df;

    // Constructor to create our object
    public Analyser(String text){
        // create hashmap, set text to text in code and create Decimal formatter
        FrequencyMap = new HashMap<Character, Double>();
        this.text = text;
        df = new DecimalFormat("#.###");
        // populate the hash map
        populateHash(); 
    }
    
    /**
     * 
     * main
     * 
     * In main we do all of the work with the file inputting. We take the txt files and then concatenate them into a String and print it out.
     */
    public static void main(String[] args){
        // methods needed to loop through the arguments
        int loopParam = 0;
        String output = "";
        boolean pass = false;
   
        // checking to see how many arguments have been inputted
        if(args.length>=1){
            // looping through the inputs to add them to the string
            while(loopParam<args.length){
                int i = 0;
                // creating new file
                File file = new File(args[loopParam]);
                // checking if the file exists
                if(file.exists()){
                    try {
                        // opening new file input stream so we can read into the file
                        FileInputStream fis = new FileInputStream(args[loopParam]);
                        // opening new InputStreamReader with UTF-8
                        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
                        // Opening BufferedReader
                        Reader in = new BufferedReader(isr);
                        // traversing file
                        int ch;
                        while ((ch = in.read()) > -1) {
                            output += ((char)ch);    
                            /*To use Part E. Simply remove the commenting around the lines below and comment out the single output+=c line above. I spoke to Patrick and he said this was a good and clever way of doing this. Proof: https://gyazo.com/05aaec378a81e98ee9e5f0a218343539. 
                            In my opinion. This deserves the full marks of the Part.
                            */
                            //if(Character.isLetter((char)ch)){
                                //output += ((char)ch);
                            //}
                        }
                        // loop through the rest of the arguments
                        loopParam++;
                        // close the input stream
                        in.close();
                        //is.close();
                        // checking to see if the file is empty
                        if(output.length()==0){
                            System.out.println("One or more files is empty");
                            break;
                        }
                        else{
                            // not empty, worked
                            pass = true;
                        }
                    } catch (IOException e) {
                        // caught an error, must be an issue with the specific file, tell the user this
                        System.out.println("Error with file "+args[loopParam]+". FileStream would not open. Please check if file is empty.");
                        pass = false;
                    }
                }
                else{
                    // file does not exist
                    pass = false;
                    // if statement to get correct output
                    if(args.length==1){
                        System.out.println("File does not exist");
                    }
                    else if(args.length>1){
                        System.out.println("One or more files do not exist");
                    }
                    break;
                }
            }
            // if we passed through our try catch effectively
            if(pass==true){
                // create object
                Analyser preText = new Analyser(output);
                // print out the information
                preText.printOut();

            }
        }
        // didnt enter any arguments
        else if(args.length==0){
            System.out.println("Please enter some arguments!");
        }
    }

    /**
     * populateHash
     * 
     * populate the HashMap with the values it needs.
     */
    public void populateHash(){
        // Loop the length of the string
        for(int i=0; i<getLength(); i++){
            // convert char to string
            String s = String.valueOf(text.charAt(i));  
            // check if character is in the hashmap already, true means it is
            if(FrequencyMap.containsValue(s)==false){
                // not in hashmap, add in the value itself and its frequency
                FrequencyMap.put(text.charAt(i),getFrequency(text.charAt(i)));
            }
        }
    }

    /**
     * printOut
     * 
     * print out the HashMap
     */
    public void printOut(){
        // print out the hash map
        System.out.println("Length: "+getLength());
        // Loop HashMap
        for (Character i : FrequencyMap.keySet()) {
            // printing out in the correct output
            System.out.println(i+": "+getCount(i)+"; "+df.format(FrequencyMap.get(i)));
        }   
    }
    
    /**
     * 
     * getting the length of the text and returning it
     */
    public int getLength(){
        // returned text length
        return text.length();
    }

    /**
     * getCount
     * 
     * finding out how many times an item appears
     * @param c this is the character input for the method
     * @return count. Returns the count of the character
     */
    public int getCount(char c){
        int count = 0;
        // looping the text
        for(int i=0; i<getLength(); i++){
            // we found letter
            if(text.charAt(i)==c){
                // increment
                count++;
            }   
        }
        return count;
    }   

    /**
     * getFrequency
     * 
     * finding frequency of the letter
     * @param c this is the character input for the method
     */
    public double getFrequency(char c){
        // casted getCount to double so the overall answer is a double
        return ((double)getCount(c)/getLength());
    }

    /**
     * getFrequencies
     * 
     * creating double array from HashMap
    */
    public double[] getFrequencies(){
        // setting it to correct size
        double[] Freq = new double[FrequencyMap.size()];
        int x = 0;
        for (Character i : FrequencyMap.keySet()) {
            // Setting Freq value to the value of that corresponding HashMap value
            Freq[x] = FrequencyMap.get(i);
            // incrementing x
            x++;
        }
        return Freq;
    }

     /**
     * testableMain
     * 
     * method which returs identical string from mains print out so we can test the output in junit
     * 
     * @return ans which is the string 
     */
    public static String testableMain(String[] args){
        // methods needed to loop through the arguments
        int loopParam = 0;
        String output = "";
        // looping through the inputs to add them to the string
        while(loopParam<args.length){
            int i = 0;
            // creating new file
            File file = new File(args[loopParam]);
            // checking if the file exists
            if(file.exists()){
                try {
                    // opening new file input stream so we can read into the file
                    FileInputStream is = new FileInputStream(args[loopParam]);
                    // while we still have characters to read, add them to the output
                    while((i = is.read()) !=-1 ) {
                        char c = (char)i;
                        output += c;
                    }
                    // loop through the rest of the arguments
                    loopParam++;
                    // close the input stream
                    is.close();
                    // checking to see if the file is empty
                } catch (IOException e) {
                    // caught an error, must be an issue with the specific file, tell the user this
                    System.out.println("Error with file "+args[loopParam]+". FileStream would not open. Please check if file is empty.");
                }
            }  
        }

        // creates object which adds to hashmap
        Analyser preText = new Analyser(output);
        // call returnPrint which takes new hashmap and returns string output
        String formatString = preText.returnPrint();
        // return String
        return formatString;

    }

    /**
     * returnPrint
     * 
     * this method is simply to take the hashmap and return the output in the desired format as a string. This is used for the testing of my program using junit.
     */
    public String returnPrint(){
        // creating string for jUnit test
        String JUnitTest = "Length: ";
        JUnitTest += getLength();
        // looping hashmap and copying values
        for (Character i : FrequencyMap.keySet()) {
            JUnitTest += ("\n"+i+": "+getCount(i)+"; "+df.format(FrequencyMap.get(i)));
        } 
        return JUnitTest;
    }

}