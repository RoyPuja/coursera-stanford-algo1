package programming_question1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InversionsBruteForce {

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
	
	static long getInversionCount(ArrayList<Integer> data){
		long cnt=0;
		for(int i=0;i<data.size()-1;i++){
			for(int j=i+1;j<data.size();j++){
				if (data.get(i)>data.get(j))
						cnt++;
			}
		}
		return cnt;
						
		
	}
	

	public static void main(String[] args) {
		ArrayList<Integer> data=getArrayData();
		System.out.println(getInversionCount(data));

	}
}
