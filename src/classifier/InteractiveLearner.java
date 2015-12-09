package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public InteractiveLearner() {
		tokenizedList = new LinkedList<String>();
	}

	public void removeSpamwords() {
		int count = tokenizedList.size();
		float temp = (float) (count * 0.1);
		int limit = Math.round(temp);
		for(String word : tokenizedList) {
			int tmpcount = 1;
			for(String woord : tokenizedList) {
				if (woord.equals(word)) {
					tmpcount++;
				}
			} 
			if (tmpcount >= limit) {
				for(int i = 0; i < tmpcount; i++) {
					tokenizedList.remove(word);
				}
			}
		}
	}
	
	public void tokenize(String in) {
		StringTokenizer st = new StringTokenizer(in, "= ;*/!@#$%^&*()-=_+[]{}|;,.:\"<>?1234567890");
		while(st.hasMoreTokens()) {
			String val = st.nextToken().toLowerCase().replaceAll(" +", " ");
			tokenizedList.add(val);
		}
	}
	
	public List<String> getWords() {
		return tokenizedList;
	}
	
	public static void main(String[] args) throws IOException {
		Model ml = new Model(2);
		InteractiveLearner il = new InteractiveLearner();
		il.tokenize(ml.getFile(Path.path + "/Mannentest/M-test11.txt"));
		System.out.println(il.getWords());
	}

}