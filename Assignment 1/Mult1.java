//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented is the simple O(n^2) Long Multiplication algorithm discussed
//in class.

public class Mult1 extends binaryOperations{
	public myBinaryNumber shiftAdd(myBinaryNumber ans, myBinaryNumber N, int k){
		  myBinaryNumber temp = new myBinaryNumber(N.getSize() +k);
		  for (int i = temp.getSize() - 1; i >= k ; i--){
			  try {
					temp.setBit(i,N.getBit(i - k));
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
		  for (int i = k-1 ; i >= 0 ; i--){
			  try {
					temp.setBit(i,0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		  }
		  myBinaryNumber toReturn = new myBinaryNumber(ans.getSize());
		  toReturn = addition(ans,temp);
		  return toReturn;
	  }
	  
	  public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){
	      //To be implemented by the student
			  
		 //Find the size of the numbers
	      int size1 = N1.getSize();
	      int size2 = N2.getSize();
	      myBinaryNumber ans = new myBinaryNumber(size1 + size2);
	      
	      for ( int position = 0 ; position < ans.getSize() - 1; position++){
	    	  try {
					ans.setBit(position, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
	      }
	      
	      for (int position = 0 ; position < N2.getSize(); position++){
	    	  if (N2.getBit(position) == 1){
	    		  ans = shiftAdd(ans,N1,position);
	    	  }
	      }
	      
	      return ans;
	  }
  
}     