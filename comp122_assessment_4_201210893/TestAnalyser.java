import  org.junit.Test;
import  static  org.junit.Assert .*;  

/**
 * TestAnalyser
 * 
 * JUnit test file
 * 
 * @author Andrew Lawler
 * @version jdk 11.0.2
 */

public class TestAnalyser {

    /**
     * testableMain
     * 
     * Simple test for the main method of my program. I send in a simple file name as an argument and then i test that the output is correct
     */
    @Test 
    public void testableMain(){
        String[] args = new String[] {"run.txt"};
        String result = Analyser.testableMain(args);
        String expected = "Length: 4\na: 1; 0.25\nb: 1; 0.25\nc: 1; 0.25\nd: 1; 0.25";
        assertEquals(result, expected);
    }

    /**
     * getLength
     * 
     * this is a test method for my length method from my program
     */
    @Test
    public void getLength(){
        Analyser a1 = new Analyser("string");
        assertTrue(a1.getLength()==6);
    }

    /**
     * getCount
     * 
     * this is a test method for getCount from my main program. This tests the count of specific characters.
     */
    @Test 
    public void getCount(){
        Analyser a1 = new Analyser("abc");
        assertTrue(a1.getCount('a')==1);
    }

    /**
     * getFrequency
     * 
     * this is a test for my frequency method from Analyser. This method returns the frequency of a letter. I tested this by sending in a string which i knew the frequencies for. This way i could compare them.  
     */
    @Test 
    public void getFrequency(){
        Analyser a1 = new Analyser("abcde");
        assertTrue(a1.getFrequency('a')==0.2);
    }

    /**
     * getFrequencies
     * 
     * this is a test for the output of my getFrequencies method. I simply send in a string to my Analyser class and then check if position 1 is equal to .25. This tests that its correct!
     */
    @Test 
    public void getFrequencies(){
        Analyser a1 = new Analyser("abcd");
        double[] arr = a1.getFrequencies();
        assertTrue(arr[0]==0.25);
    }

}