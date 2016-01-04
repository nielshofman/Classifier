package classifier;

import java.math.BigDecimal;
import java.util.*;

public class Classifier {
	
	public List<String> tokenizedList;
	private List<BagOfWords> bags;
	private Map<BagOfWords, BigDecimal> probabilities;
	private int vocabulaire;
	public BigFunctions calc;

	public Classifier(List<String> words, List<BagOfWords> bags){
		this.bags = bags;
		calc = new BigFunctions();
		this.tokenizedList = words;
		this.probabilities = new HashMap<BagOfWords, BigDecimal>();
	}
	
	public void classify(List<String> unClassified){
		for (BagOfWords bag : bags){
			probabilities.put(bag, classifyPerBag(unClassified, bag));
		}
	}
	
	public BigDecimal classifyPerBag(List<String> unClassified, BagOfWords bag){
		List<java.math.BigDecimal> waardes = new LinkedList<java.math.BigDecimal>();
		BigDecimal chanceOfBag = new java.math.BigDecimal("0");
		this.getVocabulaire();
		int values = this.getValuesSum(bag);
		for(String word : unClassified){
			if (bag.getBagWords().containsKey(word)) {
				BigDecimal nr1 = new java.math.BigDecimal(String.valueOf((bag.getBagWords().get(word)+1)));
				BigDecimal nr2 = new java.math.BigDecimal(values + this.vocabulaire);
				waardes.add(nr1.divide(nr2, 15, BigDecimal.ROUND_UP));
			}
		}
		for(int i = 0; i < waardes.size(); i++){
				chanceOfBag = chanceOfBag.add(calc.ln(waardes.get(i), 10));
			}
		return chanceOfBag;
	}
	
	public void getVocabulaire() {
		List<String> vocabulaire = new LinkedList<String>();
		vocabulaire.addAll(bags.get(0).getBagWords().keySet());
		for(int i = 1; i < bags.size(); i++){
			for(String word : bags.get(i).getBagWords().keySet()){
				if(vocabulaire.contains(word)){
					
				} else {
					vocabulaire.add(word);
				}
			}
		}
		this.vocabulaire = vocabulaire.size();
	}
	
	public int getValuesSum(BagOfWords bag) {
		int value = 0;
		for(String word : bag.getBagWords().keySet()){
			value += bag.getBagWords().get(word);
		}
		return value;
	}
	
	public Map<BagOfWords, BigDecimal> getProbabilities(){
		return this.probabilities;
	}
}