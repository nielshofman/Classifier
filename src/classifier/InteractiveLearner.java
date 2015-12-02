package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public static void main(String[] args) {
		
		tokenize("<author> = henkdegroot@hotmail.com Heee    &^&*^*)_+:}   FI=X dEZ*e && ShizZlE $#%^&");
		
	}
	
	public void tokenize(String in){
		
		String out = "";
		StringTokenizer st = new StringTokenizer(in, "=;*/!@#$%^&*()-=_+[]{}|;,/:<>?");
		while(st.hasMoreTokens()) {
			String val = st.nextToken();
			tokenizedList.add(val);
			out = out + val;
		} 
		
		System.out.println(out.toLowerCase().replaceAll(" +", " "));
		
	}
	

}
