package classifier;
import java.util.*;
import java.io.*;

public class InteractiveLearner {
	
	public List<String> tokenizedList;
	public Set<String> list;

	public InteractiveLearner() throws IOException {
		tokenizedList = new LinkedList<String>();
		list = new HashSet<String>();
		initiateStopwords();
	}

	public void initiateStopwords() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(Path.path + "filter/stopwords.txt"));
		String word = "";
		while((word = in.readLine()) != null) {
			list.add(word);
		}
		in.close();
	}
	
	public void removeWordsOnAccurance() {
		Set<String> list = new HashSet<String>();
		int count = tokenizedList.size();
		float high = (float) (count * 0.05);
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
	
	public void tokenize(String in) {
		StringTokenizer st = new StringTokenizer(in, "= ;*/!@#$%^`~&*()-=_+[]{}|;,.:\"<>?1234567890");
		while(st.hasMoreTokens()) {
			String val = st.nextToken().toLowerCase().replaceAll(" +", " ");
			tokenizedList.add(val);
		}
		tokenizedList.removeAll(this.list);
	}
	
	public List<String> getWords() {
		return tokenizedList;
	}
	
	public void clearWords() {
		tokenizedList.clear();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Model ml = new Model();
		InteractiveLearner il = new InteractiveLearner();
		il.tokenize(ml.getFile(Path.path + "/test.txt"));
		il.removeWordsOnAccurance();
		System.out.println(il.getWords());
	}
}