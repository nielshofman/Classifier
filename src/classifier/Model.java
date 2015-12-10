package classifier;

import java.io.*;
import java.util.*;

import sun.misc.IOUtils;

public class Model {

	public static final String menTestPath = "Mannentest/";
	public static final String menTraingPath = "Mannentrain/";
	public static final String womenTestPath = "Vrouwentest/";
	public static final String womenTrainingPath = "Womentrain/";
	public List<BagOfWords> bags;
	public InteractiveLearner il;
	public List<String> listOfFileNames;
	public List<String> tokenizedList;
	public List<String> files;	

	public Model(int aantal) {
		il = new InteractiveLearner();
		listOfFileNames = new LinkedList<String>();
		files = new LinkedList<String>();
		bags = new LinkedList<BagOfWords>();
		Scanner user_input = new Scanner(System.in);
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
			for(int i = 0; i <= bags.size(); i++){
				if (bags.get(i).getName().equals(bagName)){
					bags.get(i).addWords(tokenizedList);
				}	
			}
		}
		
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
		String line;
		String lines = "";
		while((line = in.readLine()) != null)
		{
		    lines = lines + line;
		}
		in.close();
		return lines;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		Model ml = new Model(2);
		//ml.getFileNames();
		//il.tokenize(ml.getFile(Path.path + "/Mannentest/M-test11.txt"));
		//System.out.println(il.getWords());
		/*Scanner user_input = new Scanner(System.in);
		Model model = new Model(2);
		InteractiveLearner il = new InteractiveLearner();
		String input;
		while(user_input.hasNext()) {
			input = user_input.next();
			if(input.equals("break123")){
				break;
			} else {
				il.tokenize(input);
			}
		}
		user_input.close();
		il.getWords(); 
	}
	//http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
	public List<String> getWords() throws IOException{
		FileReader fr = new FileReader(menTestLink);
		BufferedReader textReader = new BufferedReader(fr);
		
		List<String> textFile = new LinkedList<String>();
		
		for (int i=0; i <= 1000000; i++){
			textFile.add(textReader.readLine());
		}
		textReader.close();
		return textFile;*/
	}
}
