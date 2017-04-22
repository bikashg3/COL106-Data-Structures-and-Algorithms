/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 * COL106 Assignment 6
 */


import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class matrix {
    int index=0; // index represents the no.(N) of rows and columns in N x N matrix
    String[] rSum = new String[index]; 
    String[] cSum = new String[index];
    int[][] reqMatrix;
    String[] copyRSum;  // duplicate row sum
    String[] copyCSum; // duplicate Column sum
    int[] CurrentSum;// this variable maintains sum for the current run
    
    public void readFile(String f1) throws IOException{
        String rVal;
        String cVal;
        try(Scanner s=new Scanner(new File(f1))){
            String nVal=s.next();
            index=Integer.parseInt(nVal);
            rVal=s.next();
            cVal=s.next();  
        }
        
        rSum=rVal.split(",");
        cSum=cVal.split(",");
        CurrentSum=new int[index];
        reqMatrix=new int[index][index];
        copyRSum=rVal.split(",");
        copyCSum=cVal.split(",");
    }
    
    // Main algorithm(Greedy) that sorts the values and then fills starting with max
    public void GreedyMatrix(){
        // we start with array[0] and then sort the values as per following equation
        //c[0][j]-a[0][j] and iterate over all i and j
        for(int i=0;i<index;i++){ 
            HashMap<Integer,Integer> mp=new HashMap<>();  
            for(int j=0;j<index;j++){
             //   System.out.println(CurrentSum[0]);
                mp.put(j,Integer.parseInt(cSum[j])-CurrentSum[j]);
            }
            int[] change=new int[index];   // change is the variable that takes/memorizes difference
            for(int m=0;m<index;m++){
                change[m]=Integer.parseInt(cSum[m])-CurrentSum[m];
            }
            
            //matrix sorting
            Arrays.sort(change);
            int tracker=index-1;
            while(Integer.parseInt(rSum[i])!=0){
                Iterator<Integer> it=mp.keySet().iterator();
                while(it.hasNext()){
                    int f=it.next();
                    if(mp.get(f)==change[tracker]){
                        reqMatrix[i][f]=1;
                        mp.remove(f);
                        CurrentSum[f]++;
                        break;
                        
                    }
                }
                tracker--;
                rSum[i]=Integer.toString(Integer.parseInt(rSum[i])-1);
                
            }
            
        }
    }

// This method checks whether with given set of c1,c2...cn and r1,r2,...rn values
// can a matrix be formed? if yes then continue and return one such matrix
// else return false and print 0;    
    public boolean matrixValidity(){
        boolean isValid=true;       // default value
        
        // Row values checking
        for(int i=0;i<index;i++){
            int sum=0;
            for(int j=0;j<index;j++){
                if(reqMatrix[i][j]==1){
                    sum++;
                }
            }
                ////checking if sum!=total Row Sum
                if(sum!=Integer.parseInt(copyRSum[i])){
                    isValid=false;
                    break;
                }
            
        }
        
        // Column values checking
        for(int j=0;j<index;j++){
            int sum=0;
            for(int i=0;i<index;i++){
                if(reqMatrix[i][j]==1){
                    sum++;
                }
            }
                //checking if sum!=total Column Sum
            if(sum!=Integer.parseInt(cSum[j])){
                isValid=false;
                break;
            }    
        }
        return isValid;
    }
    
    // Main method that runs the program
    public static void main(String args[]) throws IOException{
        matrix mat=new matrix();     // mat is the matrix we will put values in
        mat.readFile("input.txt");
        int totalcount=0;
        mat.GreedyMatrix();  // Greedy matrix is formed
        
        try{
            File f2=new File("output.txt");  // output filename
            // if file doesnot exisst then create a new file      
            if(!f2.exists()){
                f2.createNewFile();
            }
            // writing output in the file named "output.txt"
            FileWriter fw1=new FileWriter(f2.getAbsoluteFile());
                try(BufferedWriter bw=new BufferedWriter(fw1)){
      
        
        
                if(mat.matrixValidity()){
                bw.write("1\n");

                for(int i=0;i<mat.index;i++){
                    for(int j=0;j<mat.index;j++){
                        if(j<mat.index-1){
                        bw.write(Integer.toString(mat.reqMatrix[i][j])+",");
                        }
                        else{
                            bw.write(Integer.toString(mat.reqMatrix[i][j]));

                        }
                    }
               
                  bw.write("\n"); 
                  // totalcount++;
                  //System.out.println(totalcount);
                }
                
                } // if condition for matrixValidity ends here
            else{
                bw.write("0");    
            }
  
        
                
        } // buffered writer ends here
                
    }   // try ends here
        
         catch(IOException e){
         }
        
    } // main class ends here
    
}   // matrix class ends here 
    


   

