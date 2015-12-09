package classifier;

import java.io.*;
import java.util.*;

import sun.misc.IOUtils;

public class Model {

	public static final String menTestLink = "C:/Users/Niels/git/Classifier/src/train/Mannentrain/M-test11.txt";
	public static final String menTestName = "Mentest";
	public static final String womenTestLink = "https://github.com/nielshofman/Classifier/tree/master/src/train/Vrouwentrain";
	public static final String womenTestName = "WomenTest";
	public List<BagOfWords> bags;
	public InteractiveLearner il;
	
	
	public Model(int aantal) {
		il = new InteractiveLearner();
	}
	
	public void getFiles()
	
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
		InteractiveLearner il = new InteractiveLearner();
		il.tokenize(ml.getFile(Path.path + "/Mannentest/M-test11.txt"));
		System.out.println(il.getWords());
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
