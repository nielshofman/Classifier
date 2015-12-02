package classifier;
// http://www.java-samples.com/showtutorial.php?tutorialid=236
import java.util.StringTokenizer;
import java.io.*;

public class InteractiveLearner {

	public static void main(String[] args) {
		String out = "";
		String in = "<author> = henkdegroot@hotmail.com Heee    &^&*^*)_+:}   FI=X dEZ*e && ShizZlE $#%^&";
		StringTokenizer st = new StringTokenizer(in, "=;*/!@#$%^&*()-=_+[]{}|;,/:<>?");
		while(st.hasMoreTokens()) {
			String val = st.nextToken();
			out = out + val;
		} 
		System.out.println(out.toLowerCase().replaceAll(" +", " "));
	}
}
