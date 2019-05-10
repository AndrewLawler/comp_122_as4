import  org.junit.Test;
import  static  org.junit.Assert .*;  

public class TestAnalyser {

    @Test
    public void getLength(){
        Analyser a1 = new Analyser("string");
        assertTrue(a1.getLength()==6);
    }

    @Test 
    public void getCount(){
        Analyser a1 = new Analyser("abc");
        assertTrue(a1.getCount('a')==1);
    }

    @Test 
    public void getFrequency(){
        Analyser a1 = new Analyser("abcde");
        assertTrue(a1.getFrequency('a')==0.2);
    }

    @Test 
    public void getFrequencies(){
        Analyser a1 = new Analyser("abcd");
        double[] arr = a1.getFrequencies();
        assertTrue(arr[0]==0.25);
    }

}