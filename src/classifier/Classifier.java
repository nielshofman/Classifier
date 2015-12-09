package classifier;

import java.io.*;
import java.util.*;

public class Classifier {
	
	public List<String> tokenizedList;
	private List<BagOfWords> bags;
	private HashMap<String, Integer> mapje = new HashMap<String, Integer>();
	private HashMap<BagOfWords, Double> probabilities;

	
	public Classifier(List<String> words, List<BagOfWords> bags){
		this.bags = new LinkedList<BagOfWords>();
		this.tokenizedList = words;
	}
	
	public void classify(List<String> unClassified){
		for (BagOfWords bag : bags){
			probabilities.put(bag, classifyPerBag(unClassified, bag));
		}
	}
	
	public double classifyPerBag(List<String> unClassified, BagOfWords bag){
		List<Integer> waardes = new LinkedList<Integer>();
		double chanceOfBag = 0;
		for(String word : unClassified){
			if (mapje.containsKey(word)) {
				waardes.add((mapje.get(word)+1)/(mapje.size()+2));
			}
		}
		for(int i = 0; i < waardes.size(); i++){
				chanceOfBag = chanceOfBag + (Math.log10(waardes.get(i)));
			}
		return chanceOfBag;
	}
	
	public static void main(String[] args) {
        
    }

}
