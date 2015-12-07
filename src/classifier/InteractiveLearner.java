package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public InteractiveLearner() {
		tokenizedList = new LinkedList<String>();
	}

	public void tokenize(String in) {
		StringTokenizer st = new StringTokenizer(in, "=;*/!@#$%^&*()-=_+[]{}|;,/:<>?1234567890");
		while(st.hasMoreTokens()) {
			String val = st.nextToken().toLowerCase().replaceAll(" +", " ");
			tokenizedList.add(val);
		}
	}
	
	public List<String> getWords() {
		return tokenizedList;
	}

}