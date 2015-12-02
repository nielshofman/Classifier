package classifier;

import java.io.*;
import java.util.*;

public class Classifier {
	
	public List<String> tokenizedList;
	private List<String> classifiedList;
	private List<BagOfWords> bags;
	private BagOfWords winningBag;

	
	public Classifier(List<String> words, List<BagOfWords> bags){
		this.bags = new LinkedList<BagOfWords>();
		this.classifiedList = new LinkedList<String>();
		this.tokenizedList = words;
		this.classifiedList = words;

	}
	
	public void classify(List<String> unClassified){
		
		for (BagOfWords bag : bags){
			classifyPerBag(unClassified, bag);
		}
		
	}
	
	public void classifyPerBag(List<String> unClassified, BagOfWords bag){
		
		for(String word : unClassified){
			
		}
	}
	
	public static void main(String[] args) {
        
    }

}
