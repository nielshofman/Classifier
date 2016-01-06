package classifier;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Model {

	public static final String menTestPath = "Mannentest/";
	public static final String menTrainPath = "Mannentrain/";
	public static final String womenTestPath = "Vrouwentest/";
	public static final String womenTrainPath = "Vrouwentrain/";
	public static final String spamTrainPath = "Spamtrain/";
	public static final String spamTestPath = "Spamtest/";
	public static final String mailTrainPath = "Mailtrain/";
	public static final String mailTestPath = "Mailtest/";
	private List<BagOfWords> bags;
	private List<String> bagNames;
	private InteractiveLearner il;
	private List<String> listOfFileNames;
	private Map<String, String> choice;

	public Model() throws IOException, ClassNotFoundException {
		int aantal = 0;
		il = new InteractiveLearner();
		listOfFileNames = new LinkedList<String>();
		bags = new LinkedList<BagOfWords>();
		choice = new HashMap<String, String>();
		bagNames = new LinkedList<String>();
		Scanner user_input = new Scanner(System.in);
		System.out.println("Geef het aantal bags");
		while(user_input.hasNextInt()) {
			aantal = user_input.nextInt();
			break;
		}
		this.getFileNames("");
		for(int i = 0; i < aantal; i++) {
			int p = 0;
			while(p == 0){
				BagOfWords tmp = null;
				System.out.println("Geef een naam voor bag nummer " + (i+1));
				while(user_input.hasNext()) {
					String bagName = user_input.next();
					if(!bagNames.contains(bagName)){
						tmp = new BagOfWords((i+1), bagName);
						bags.add(tmp);
						bagNames.add(bagName);
						if(listOfFileNames.contains(bagName)) {
							bags.get(i).readFile();
						}else{
							user_input.nextLine();
							System.out.println("Geef pad van de map met traindata van " + bagName);
							String path = user_input.nextLine();
							listOfFileNames.clear();
							fillBag(path, bagName);
						}
						p = 1;
					}
					else{
						System.out.println("Deze bagnaam bestaat al");
					}
					break;
				}
			}
		}/*
		user_input.nextLine();
		System.out.println("Geef systeempad van het bestand dat geklassificeerd moet worden");
		while(user_input.hasNext()) {
			String file = user_input.next();
			this.classifyFile(file);
			System.out.println("Is dit juist? Toets J voor ja of geef de juiste klasse (bag)");
			int o = 0;
			while(o == 0) {
				while(user_input.hasNext()) {
					String bagName = user_input.next();
					if(bagName.equals("J")){
						this.fillBagWithFile(file);
						o =1;
					} else if(bagNames.contains(bagName)){
						this.fillBagWithFile(file, bagName);
						o = 1;
					} else { 
						System.out.println("Geef juiste klasse- (bag)naam in");
					}
					break;
				}
			}
			System.out.println("Geef systeempad van het volgende bestand dat geklassificeerd moet worden");
		}*/
		user_input.close();
		listOfFileNames.clear();
	}
	
	public void fillBagWithFile(String file) throws IOException{
		for(int i = 0; i < bags.size(); i++){
			if (bags.get(i).getName().equals(choice.get(file))){
				bags.get(i).addWords(il.getWords());
			}
			bags.get(i).writeToFile();
		}
		il.clearWords();
		System.out.println("Het bestand is toegevoegd aan " + choice.get(file));
	}
	
	public void fillBagWithFile(String file, String bagName) throws IOException{
		for(int i = 0; i < bags.size(); i++){
			if (bags.get(i).getName().equals(bagName)){
				bags.get(i).addWords(il.getWords());
			} else {
				for (String woord : il.getWords()){
					if (bags.get(i).getBagWords().containsKey(woord)) {
						bags.get(i).removeWord(woord);
					}
				}
			}
			bags.get(i).writeToFile();
		}
		il.clearWords();
		System.out.println("Het bestand is toegevoegd aan " + bagName);
	}
	
	public void fillBag(String path, String bagName) throws IOException{
		this.getFileNames(path);
		for(String iets : listOfFileNames){
			il.tokenize(this.getFile(Path.path + path + iets));
			il.removeWordsOnAccurance();
			for(int i = 0; i < bags.size(); i++){
				if (bags.get(i).getName().equals(bagName)){
					bags.get(i).addWords(il.getWords());
					bags.get(i).writeToFile();
				}
			}
			il.clearWords();
		}
		listOfFileNames.clear();
		System.out.println("done for " + bagName);
	}
	
	public void getFileNames(String trainTest){
	File folder = new File(Path.path + trainTest);
	File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				listOfFileNames.add(listOfFiles[i].getName());
			}
	 	}
	}
	
	public String getFile(String fileName) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line = "";
		String lines = "";
		while((line = in.readLine()) != null) {
		    lines = lines + line;
		}
		in.close();
		return lines;
	}
	
	public List<BagOfWords> getBags() {
		return bags;
	}
	
	public void classifyAll(String path) throws IOException{
		listOfFileNames.clear();
		choice.clear();
		this.getFileNames(path);
		for(String iets : listOfFileNames){
			this.classifyFile(Path.path + path + iets);
			il.clearWords();
		}
	}
		
	public void classifyFile (String fileName) throws IOException {
		try {
		il.tokenize(this.getFile(fileName));
		} catch(FileNotFoundException i) {
			System.out.println("Het bestand kon niet worden gevonden");
			Scanner user_input = new Scanner(System.in);
			while(user_input.hasNextInt()) {
				this.classifyFile(user_input.next());
			break;
			}
			user_input.close();
		}
		il.removeWordsOnAccurance();
		this.classify(il.getWords(), fileName);
	}
	
	public void classify(List<String> list, String fileName){
		Classifier cl = new Classifier(list, bags);
		cl.classify(list);
		BigDecimal a = cl.getProbabilities().get(bags.get(0));
		String highest = bags.get(0).getName();
		for(int i = 1; i < bags.size(); i++) {
			BigDecimal b = cl.getProbabilities().get(bags.get(i));
			if(a.compareTo(b) > 0){
				highest = bags.get(i).getName();
				a = b;
			}
		}
		choice.put(fileName, highest);
		System.out.println("Deze file is geklassificeerd als: " + highest);
	}
	
	public void getChoices(){
		int a = 0;
		int b = 0;
		for(String uit : choice.values()){
			if(uit.equals("spam")){
				a++;
			} else if (uit.equals("ham")) {
				b++;
			}
		}
		System.out.println("Spam: " + a + "Ham: " + b);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Model ml = new Model();
		//ml.fillBag(menTrainPath, "man");
		//ml.fillBag(womenTrainPath, "vrouw");
		//ml.fillBags();
		ml.classifyAll(spamTestPath);
		ml.getChoices();
		ml.classifyAll(mailTestPath);
		ml.getChoices();
	}
}