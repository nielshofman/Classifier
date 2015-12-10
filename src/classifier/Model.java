package classifier;

import java.io.*;
import java.util.*;

import sun.misc.IOUtils;

public class Model {

	public static final String menTestLink = "Mannentest/";
	public static final String menTraingLink = "Mannentrain/";
	public static final String womenTestLink = "Vrouwentest/";
	public static final String womenTrainingLink = "Womentrain/";
	public List<BagOfWords> bags;
	public InteractiveLearner il;
	public List<String> listOfFileNames;
	public List<String> files;
	public BagOfWords bag1;
	public BagOfWords bag2;
	
	
	public Model(int aantal) {
		il = new InteractiveLearner();
		listOfFileNames = new LinkedList<String>();
		files = new LinkedList<String>();
		for(int i = 0; i < aantal; i++) {
			
		}
	}
	
	public List<String> getString(String trainTest, String bagName) throws IOException{
		this.getFileNames(trainTest);
		for(String iets : listOfFileNames){
			il.tokenize(this.getFile(Path.path + trainTest + iets));
			if ()
		}
		return files;
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
		Model ml = new Model(3);
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
