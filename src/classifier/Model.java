package classifier;

import java.util.*;

public class Model {

	public List<BagOfWords> bags;
	public InteractiveLearner il;
	
	public Model(int aantal) {
		il = new InteractiveLearner();
		
	}
	
	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);
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
}