package classifier;

import java.util.*;

public class BagOfWords {
	
	public HashMap<BagOfWords, Integer> bags = new HashMap<BagOfWords, Integer>();
	public List<String> words;
	public int id;
	public String name;
	
	public BagOfWords(String name){
		if (bags.isEmpty()) {
			bags.put(this, 1);
		}
		for(int q = 1; q > 0; q++){
			if(!bags.containsValue(q)){
				bags.put(this, q);
			}
		}
		this.name = name;
		words = new LinkedList<String>();
	}
	
	private void addWords(List<String> in){
		for(String word : in) {
			words.add(word);
		}
	}

}
