package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public static void main(String[] args) {
		
		Scanner user_input = new Scanner( System.in);
		String input;
		input = user_input.next();
		System.out.print(input);
		user_input.close();
		//tokenize("<author> = henkdegroot@hotmail.com Heee    &^&*^*)_+:}   FI=X dEZ*e && ShizZlE $#%^&");
		
	}
	
	public List<String> tokenize(String in){
		
		StringTokenizer st = new StringTokenizer(in, "=;*/!@#$%^&*()-=_+[]{}|;,/:<>?");
		while(st.hasMoreTokens()) {
			String val = st.nextToken();
			val.toLowerCase().replaceAll(" +", " ");
			tokenizedList.add(val);
			
		} 
		return tokenizedList;
		
		
	}
	
	

}
//System.out.println(out.toLowerCase().replaceAll(" +", " "));
//out = out + val;
//String out = "";