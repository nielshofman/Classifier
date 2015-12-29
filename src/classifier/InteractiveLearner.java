package classifier;
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;

	public InteractiveLearner() {
		tokenizedList = new LinkedList<String>();
	}

	public void filterWords() throws IOException {
		this.removeWordsOnAccurance();
		this.removeStopwords();
	}
	
	public void removeWordsOnAccurance() {
		Set<String> list = new HashSet<String>();
		int count = tokenizedList.size();
		float high = (float) (count * 0.1);
		int limithigh = Math.round(high);
		float low = (float) (count * 0.005);
		int limitlow = Math.round(low);
		for(String word : tokenizedList) {
			int tmpcount = 0;
			for(String woord : tokenizedList) {
				if (woord.equals(word)) {
					tmpcount++;
				}
			} 
			if (tmpcount >= limithigh | tmpcount <= limitlow) {
				list.add(word);
			}
		}
		tokenizedList.removeAll(list);
	}
	
	public void removeStopwords() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(Path.path + "filter/stopwords.txt"));
		String word = "";
		while((word = in.readLine()) != null) {
			if(tokenizedList.contains(word)){
				tokenizedList.remove(word);
			}
		}
		in.close();
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
		Model ml = new Model();
		InteractiveLearner il = new InteractiveLearner();
		il.tokenize(ml.getFile(Path.path + "/test.txt"));
		il.filterWords();
		System.out.println(il.getWords());
	}
}