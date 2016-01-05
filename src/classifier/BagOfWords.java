package classifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class BagOfWords {
	
	private Map<String, Integer> words;
	private int id;
	private String name;
	
	public BagOfWords(int id, String name){
		this.name = name;
		id = this.id;
		words = new HashMap<String, Integer>();
	}
	
	public void addWords(List<String> in){
		for(String word : in) {
			if (words.containsKey(word)) {
				int number = words.get(word) + 1;
				words.put(word, number);
			}
			else {
				words.put(word, 1);
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
	
	public void readFile() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(Path.path + name);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        words = (Map<String, Integer>) in.readObject();
        in.close();
        fileIn.close();
	}
	
	public BagOfWords getBag(String name) {
		if(name.equals(this.name)){
			return this;
		} else { 
		return null; }
	}
	
	public String getName(){
		return this.name;
	}
	
	public Map<String, Integer> getBagWords() {
		return words;
	}
}