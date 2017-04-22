import java.io.*;

public class Test{
    public static void main(String args[]){
        
        myBinaryNumber[] Nums = new myBinaryNumber[100];
        //The file binary_numbers.txt contains 100 small binary numbers.
        //We read all the binary numbers from this file and initialize
        //an array containing references to 100 elements of the type
        //myBinaryNumber. You can use these binary numbers to check
        //your code.
        try{
            BufferedReader BR = new BufferedReader(new FileReader("binary_numbers.txt"));
            for(int i=0;i<100;++i){
                String S = BR.readLine();
                Nums[i] = new myBinaryNumber(S);
            }
            BR.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        //Now you can use binary numbers in Nums array to check your code.
        //You will also need to experiment with the running time of the your methods.
        //The following piece of code will help you in this.
        long startTime = System.currentTimeMillis();
        Nums[0].printNumber();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}