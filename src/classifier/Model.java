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
	
	private String getFile(String fileName){
		
		  String result = "";
			
		  ClassLoader classLoader = getClass().getClassLoader();
		  try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		  } catch (IOException e) {
			e.printStackTrace();
		  }
			
		  return result;
			
	  }
	
	
	public static void main(String[] args) throws IOException {
		Model ml = new Model(3);
		
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
