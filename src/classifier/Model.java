package classifier;

import java.io.*;
import java.util.*;

public class Model {

	public static final String menTestPath = "Mannentest/";
	public static final String menTrainPath = "Mannentrain/";
	public static final String womenTestPath = "Vrouwentest/";
	public static final String womenTrainPath = "Vrouwentrain/";
	private List<BagOfWords> bags;
	private InteractiveLearner il;
	private List<String> listOfFileNames;
	
	public Model() {
		int aantal = 0;
		il = new InteractiveLearner();
		listOfFileNames = new LinkedList<String>();
		bags = new LinkedList<BagOfWords>();
		Scanner user_input = new Scanner(System.in);
		System.out.println("Geef het aantal bags");
		while(user_input.hasNextInt()) {
			aantal = user_input.nextInt();
			break;
		}
		for(int i = 0; i < aantal; i++) {
			BagOfWords tmp = null;
			System.out.println("Geef een naam voor bag nummer " + (i+1));
			while(user_input.hasNext()) {
				tmp = new BagOfWords((i+1), user_input.next());
				bags.add(tmp);
				break;
			}
		}
		user_input.close();
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
			}
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
	
	public static void main(String[] args) throws IOException {
		Model ml = new Model();
		ml.fillBag(menTrainPath, "man");
		ml.fillBag(womenTrainPath, "vrouw");
		System.out.println("Geef pad van bestand(en) in");
	}
}