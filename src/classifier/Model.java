package classifier;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Model {

	public static final String menTestPath = "Mannentest/";
	public static final String menTrainPath = "Mannentrain/";
	public static final String womenTestPath = "Vrouwentest/";
	public static final String womenTrainPath = "Vrouwentrain/";
	private List<BagOfWords> bags;
	private List<String> bagNames;
	private InteractiveLearner il;
	private List<String> listOfFileNames;
	private Map<String, String> choice;
	private List<String> notFilledBags;

	public Model() throws IOException, ClassNotFoundException {
		int aantal = 0;
		il = new InteractiveLearner();
		listOfFileNames = new LinkedList<String>();
		bags = new LinkedList<BagOfWords>();
		choice = new HashMap<String, String>();
		bagNames = new LinkedList<String>();
		notFilledBags = new LinkedList<String>();
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
							bags.get(i+1).readFile();
						}else{
							notFilledBags.add(bagName);
						}
						p = 1;
					}
					else{
						System.out.println("Deze bagnaam bestaat al");
					}
					break;
				}
			}
		}
		/*for(int i = 0; i < notFilledBags.size(); i++){
			System.out.println("Geef pad van map van traindata van " + notFilledBags.get(i));
			while(user_input.hasNext()) {
				String path = user_input.nextLine();
				fillBag(path, notFilledBags.get(i));
				path = "";
				break;
			}
		}*/
		user_input.close();
		listOfFileNames.clear();
	}
	
	public void fillBag(String trainTestPath, String bagName) throws IOException{
		this.getFileNames(trainTestPath);
		for(String iets : listOfFileNames){
			il.tokenize(this.getFile(Path.path + trainTestPath + iets));
			il.filterWords();
			for(int i = 0; i < bags.size(); i++){
				if (bags.get(i).getName().equals(bagName)){
					bags.get(i).addWords(il.getWords());
				}
				FileOutputStream fileOut = new FileOutputStream(Path.path + bagName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(bags.get(i).getBagWords());
				out.close();
				fileOut.close();
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
			il.tokenize(this.getFile(Path.path + path + iets));
			il.filterWords();
			this.classify(il.getWords(), iets);
		}
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
	}
	
	public Map<String, String> getChoices(){
		return choice;
	}
	
	//To be deleted
	public void fillBags() throws IOException{
		Scanner user_input = new Scanner(System.in);
		for(int i = 0; i < notFilledBags.size(); i++){
			System.out.println("Geef pad van map van traindata van " + notFilledBags.get(i));
			while(user_input.hasNext()) {
				String path = user_input.nextLine();
				fillBag(path, notFilledBags.get(i));
				break;
			}
		}
		user_input.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Model ml = new Model();
		//ml.fillBag(menTrainPath, "man");
		//ml.fillBag(womenTrainPath, "vrouw");
		//ml.fillBags();
		//ml.classifyAll(menTestPath);
		//System.out.println(ml.getChoices());
		//ml.classifyAll(womenTestPath);
		//System.out.println(ml.getChoices());
	}
}