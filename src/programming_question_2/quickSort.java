package programming_question_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class quickSort {
	static ArrayList<Integer> getArrayData() {
		BufferedReader br = null;
		ArrayList<Integer> dataArray=new ArrayList<Integer>();
		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader("/Users/pnroy/documents/workspace/coursera-stanford-algo1-java/src/programming_question1/IntegerArray.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				dataArray.add(Integer.parseInt(sCurrentLine));

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return dataArray;
	}

	public int countComparisons(int[] arr){
		int cnt=0
				return cnt;
	}
	
	public static void main(String[] agrs){
		
	}

}
