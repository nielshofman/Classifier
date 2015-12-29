package classifier;

import java.util.*;

public class Classifier {
	
	public List<String> tokenizedList;
	private List<BagOfWords> bags;
	private Map<BagOfWords, Double> probabilities;

	
	public Classifier(List<String> words, List<BagOfWords> bags){
		this.bags = new LinkedList<BagOfWords>();
		this.tokenizedList = words;
		this.probabilities = new HashMap<BagOfWords, Double>();
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
			if (bag.getBagWords().containsKey(word)) {
				waardes.add((bag.getBagWords().get(word)+1)/(bag.getBagWords().size()+2));
			}
		}
		for(int i = 0; i < waardes.size(); i++){
				chanceOfBag = chanceOfBag + (Math.log10(waardes.get(i)));
			}
		return chanceOfBag;
	}
	
	public Map<BagOfWords, Double> getProbabilities(){
		return this.probabilities;
	}
	
	public static void main(String[] args) {
        
    }
}