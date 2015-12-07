package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public InteractiveLearner() {
		tokenizedList = new LinkedList<String>();
	}
	
	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);
		InteractiveLearner il = new InteractiveLearner();
		String input;
		while(user_input.hasNext()) {
			input = user_input.nextLine();
			il.tokenize(input);
		}
		user_input.close();		
	}
	
	public List<String> tokenize(String in) {
		StringTokenizer st = new StringTokenizer(in, "=;*/!@#$%^&*()-=_+[]{}|;,/:<>?");
		while(st.hasMoreTokens()) {
			String val = st.nextToken().toLowerCase().replaceAll(" +", " ");
			tokenizedList.add(val);
		} 
		return tokenizedList;
	}
}