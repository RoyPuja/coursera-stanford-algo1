package programming_question_3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinimumCutAlgo {
	
	
	public static void main(String[] args){
	HashMap<Integer,ArrayList<Integer>> originalGraph=getDataFromFile();
	
	int minimumcut=0;
	for(int i=0;i<1000;i++){
		HashMap<Integer,ArrayList<Integer>> copyData=copyData(originalGraph);
		int result=processMinCutAlgo(copyData);
		
		if(minimumcut==0){
			minimumcut=result;
		}
		else{
			if(result<minimumcut)
				minimumcut=result;
		}
		System.out.println("partial result:"+result);
	}
	System.out.println("Minimum cut:"+minimumcut);
		
	}
	
	private static int processMinCutAlgo(HashMap<Integer,ArrayList<Integer>> graph)
	{
		while (graph.size() >2)
		{
		processMinCutAlgoStep(graph);
		}
		return graph.get((Integer)graph.keySet().toArray()[0]).size();
	
	}
	 
	private static void processMinCutAlgoStep(HashMap<Integer, ArrayList<Integer>> graph)
    {
        //Choose randome items
        List<Integer> randomItems = chooseRandomItems(graph);
        
        Integer firstItem = randomItems.get(0);
        Integer secondItem = randomItems.get(1);
        
        ArrayList<Integer> firstItemList = graph.get(firstItem);
        ArrayList<Integer> secondItemList = graph.get(secondItem);
        
        //Add second list items to first list
        firstItemList.addAll(secondItemList);
        
        //Remove second list items
        graph.remove(randomItems.get(1));
        
        //Replace second item appeareances by first item
        Iterator it = graph.keySet().iterator();
        
        while(it.hasNext())
        {
            Integer currentKey = (Integer)it.next();
            
            ArrayList<Integer> currentItemList = graph.get(currentKey);
            
            for(Integer i : currentItemList)
            {
                if(i.intValue() == secondItem.intValue()){
                    currentItemList.set(currentItemList.indexOf(i), firstItem);
                }
            }
        }
        
        //Remove loops
        ArrayList<Integer> itemsToRemove = new ArrayList<Integer>();
        
        for(Integer i : firstItemList)
        {
            if(i.intValue() == firstItem.intValue()){
                itemsToRemove.add(i);
            }
        }
        
        firstItemList.removeAll(itemsToRemove);
    }
    
    
    private static List<Integer> chooseRandomItems(HashMap<Integer, ArrayList<Integer>> graph)
    {
        ArrayList<Integer> randomItems = new ArrayList<Integer>();
        
        int nodeIndex = (int)(Math.random() * graph.keySet().size());
        Integer randomNode = (Integer)(graph.keySet().toArray()[nodeIndex]);
        
        int edgeIndex = (int)(Math.random() * graph.get(randomNode).size());
        Integer randomEdge = graph.get(randomNode).get(edgeIndex);
        
        randomItems.add(randomNode);
        randomItems.add(randomEdge);
        
        return randomItems;
    }
    
private static HashMap<Integer,ArrayList<Integer>> copyData(HashMap<Integer,ArrayList<Integer>> graph){
	HashMap<Integer, ArrayList<Integer>> graphCopy = new HashMap<Integer, ArrayList<Integer>>();
    
    Iterator it = graph.keySet().iterator();
    
    while(it.hasNext())
    {
        Integer currentKey = (Integer)it.next();
        ArrayList<Integer> currentItemList = graph.get(currentKey);
        
        graphCopy.put(currentKey, new ArrayList<Integer>(currentItemList));
    }
    
    return graphCopy;
}

	private static HashMap<Integer, ArrayList<Integer>> getDataFromFile()
    {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
            
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream("/Users/pnroy/Documents/workspace/coursera-stanford-algo1-java/src/programming_question_3/kargerMinCut.txt");
            
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String line;
            while ((line = br.readLine()) != null)
            {
                // process the line
                StringTokenizer tokens = new StringTokenizer(line);
                ArrayList<Integer> edges = new ArrayList<Integer>();
                
                // first item is the token
                Integer node = new Integer(tokens.nextToken());
                
                while(tokens.hasMoreTokens()){
                    edges.add(new Integer(tokens.nextToken()));
                }
                
                graph.put(node, edges);
            }
            
            br.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(MinimumCutAlgo.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(MinimumCutAlgo.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(MinimumCutAlgo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return graph;
    }
}
