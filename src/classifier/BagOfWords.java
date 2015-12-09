package classifier;

import java.util.*;

public class BagOfWords {
	
	public HashMap<BagOfWords, Integer> bags = new HashMap<BagOfWords, Integer>();
	public Map<String, Integer> words;
	public int id;
	public String name;
	
	public BagOfWords(String name){
		if (bags.isEmpty()) {
			bags.put(this, 1);
		}
		for(int q = 1; q > 0; q++){
			if(!bags.containsValue(q)){
				bags.put(this, q);
				break;
			}
		}
		this.name = name;
		words = new HashMap<String, Integer>();
	}
	
	public void addWords(List<String> in){
		for(String word : in) {
			if(words.isEmpty()){
				words.put(word,1);
			}
			else{
				int number = words.get(word) + 1;
				words.put(word, number);
			}
		}
	}
	
	public int countWords(){
		int amount = 0;
		for (int f : words.values()){
			amount += f;
		}
		return amount;
	}
}