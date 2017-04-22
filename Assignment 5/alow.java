/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 */

import java.lang.Integer;
import java.util.*;
import java.util.LinkedList;
import java.io.*;





public class alow{
	public static void main(String[] args) {
		try{
			try(BufferedReader FileR=new BufferedReader(new FileReader("input.txt")))
			{
				String commands=FileR.readLine();
				int NoOFVertex=Integer.valueOf(commands);
                                //this Grp is a graph that has NoOfVertex vertices.
				graph Grp=new graph(NoOFVertex);
                                int y=0;
				int v=0;
				graph reverseGrp=new graph(NoOFVertex);
				int [] Ftime=new int[2*NoOFVertex];
                                
                                // making Adjacency List 
				while(((commands=FileR.readLine())!=null)){
					String[] vertexStored=commands.split(",");
					if(vertexStored[0].equals("-"))
						{v++;
						continue;	}
                                        
                                        
				for (String vertexStored1 : vertexStored){
					Grp.makeList(v,(Integer.valueOf(vertexStored1.trim()))-1);
				}	v++;		
				}
                                
                                
				for(int i=0;i<(2*NoOFVertex);i++){ 
					Ftime[i]=-1;}
				Grp.DFS(Ftime);
                                
                                
				for(int i=0;i<NoOFVertex;i++){
                                      //  for(Iterator<Integer> r=Grp.adjList[r].listIterator();r.hasNext();){
                                      //     reverseGrp.adjList[r.next()].add(r); 
                                      //  }
                                      
                                       // Recur for all the vertices adjacent to this vertex
					Iterator<Integer> itr=Grp.adjList[i].listIterator();
					while (itr.hasNext())
						reverseGrp.adjList[itr.next()].add(i);
				}	
				
			//	for(int i=0;i<NoOFVertex;i++){
			//		Iterator<Integer> itr=reverseGrp.adjList[i].listIterator();	
			//	}
				int[] bikash=new int[NoOFVertex];
				boolean visited[]=new boolean[NoOFVertex];
                                boolean notvisited[]=new boolean[NoOFVertex];
				int[] bikash1=new int[1];
				bikash1[0]=-1;
                                
                                // Step 1: Mark all the vertices as not visited
				for (int i=2*NoOFVertex-1;i>=0;i--){
					if(Ftime[i]!=-1){
						++bikash1[0];
						reverseGrp.DFS_scc(Ftime[i],visited,bikash1,bikash);
					}
				}
				int vertex_scc=bikash1[0]+1;
				graph Grp_Scc=new graph(vertex_scc);
				int[] indegree=new int[vertex_scc];
				ArrayList<Integer> start_vertex=new ArrayList<>();
				for(int i=0;i<NoOFVertex;i++){
					Iterator<Integer> itr=Grp.adjList[i].listIterator();
					while(itr.hasNext()){
						int p=itr.next();
                                                  if(bikash[i]==bikash[p]){ }
                                                     else
                                                        if(!Grp_Scc.adjList[bikash[i]].contains(bikash[p])){
								Grp_Scc.adjList[bikash[i]].add(bikash[p]);
								indegree[bikash[p]]++;
							}
						
					}
				}
				for(int i=0;i<vertex_scc;i++){
					Iterator<Integer> itr=Grp_Scc.adjList[i].listIterator();
				}
				for(int i=0;i<vertex_scc;i++){
					if(indegree[i]==0){
						start_vertex.add(i);
					}
				}
				for (int i=0;i<vertex_scc;i++){
                                     //   if(start_vertex.isEmpty()){
                                           // System.out.println("0");
                                     //       break ;
                                     //   }
					if(start_vertex.size()>1){
						System.out.println("0");
						return;
					}
                                       // else if(start_vertex.isEmpty()){
                                       //     System.out.println("0");
                                       //     return ;
                                      //  }
                                        else if(!start_vertex.isEmpty()){
						int j=start_vertex.remove(0);
						Iterator<Integer> itr=Grp_Scc.adjList[j].listIterator();
						while (itr.hasNext()){
							int k=itr.next();
							indegree[k]--;
							if(indegree[k]==0){
								start_vertex.add(k);
							}
						}
					}
				}
				System.out.println("1");

			}
		}
		catch(FileNotFoundException k) {

		}
		catch (IOException k){}
               
	}  // main class ends here

}   // alow class ends here

class graph{
	int NoOFVertex;
	LinkedList<Integer> adjList[];
	graph(int p){
            NoOFVertex=p;
		adjList=new LinkedList[p];
		for(int i=0;i<p;i++){
			adjList[i]=new LinkedList();
		}
	}

	public void makeList(int vert,int edge){
	adjList[vert].add(edge);
	}

	public void DFS_time(int vert,boolean visited[],int[] strt_time,int[] ftime){
		visited[vert]=true;
		++strt_time[0];
		Iterator<Integer> i=adjList[vert].listIterator();
		while(i.hasNext()){
			int k=i.next();
			if(!visited[k]){
				DFS_time(k,visited,strt_time,ftime);
			}
		}
		ftime[++strt_time[0]-1]=vert;
	}
        
        //Function to add an edge into the graph
   public  void addEdge(int v,int w) { adjList[v].add(w); }

	public void DFS(int[] ftime){
		int[] strt_time=new int[1];
		strt_time[0]=0;
		boolean	visited[]=new boolean[NoOFVertex];

		for(int i=0;i<NoOFVertex;i++){
			if(visited[i]==false){
				DFS_time(i,visited,strt_time,ftime);
			}
		}
	}
        
         // A recursive function to print DFS starting from v
    void DFSUtil(int v,Boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
 
        int n;
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adjList[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited);
        }
    }
        
     // The main function that returns true if graph is strongly
    // connected
    Boolean isSC()
    {
        // Step 1: Mark all the vertices as not visited
        // (For first DFS)
        Boolean visited[] = new Boolean[NoOFVertex];
        for (int i = 0; i < NoOFVertex; i++)
            visited[i] = false;
 
        // Step 2: Do DFS traversal starting from first vertex.
        DFSUtil(0, visited);
 
        // If DFS traversal doesn't visit all vertices, then
        // return false.
        for (int i = 0; i < NoOFVertex; i++)
            if (visited[i] == false)
                return false;
         // Step 3: Create a reversed graph
      //  graph gr = getTranspose();
 
        // Step 4: Mark all the vertices as not visited (For
        // second DFS)
        for (int i = 0; i < NoOFVertex; i++)
            visited[i] = false;
 
        // Step 5: Do DFS for reversed graph starting from
        // first vertex. Staring Vertex must be same starting
        // point of first DFS
     //   gr.DFSUtil(0, visited);
 
        // If all vertices are not visited in second DFS, then
        // return false
        for (int i = 0; i <NoOFVertex ; i++)
            if (visited[i] == false)
                return false;
 
        return true;
    }
    
// this method DFS_scc runs on the strongly connected component on the graph
	public void DFS_scc(int vert,boolean[] visited,int[] bikash1,int[] bikash){
		visited[vert]=true;
		bikash[vert]=bikash1[0];
		Iterator<Integer> i=adjList[vert].listIterator();
	while(i.hasNext()){
		int k=i.next();
		if(!visited[k]){
			DFS_scc(k,visited,bikash1,bikash);
		}
	}	
	}

}

