//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the simple O(n^2) Divide and Conquer algorithm discussed
//in class.

public class Mult2 extends binaryOperations{
	
	public myBinaryNumber shiftAdd(myBinaryNumber ans, myBinaryNumber N, int k){
		/*System.out.println("Shift add to ");
		  ans.printNumber();
		  System.out.println("Shifting ");
		  N.printNumber();
		  System.out.println("For " + k);*/
		
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
		  
		  //System.out.print("Gives Ans ");
		  //toReturn.printNumber();
		  return toReturn;
	  }
	
	public boolean isOne(myBinaryNumber A){
		for (int i = A.getSize()-1 ; i >= 1; i--){
			if (A.getBit(i) == 1){
				return false;
			}
		}
		if (A.getBit(0) == 0){
			return false;
		}
		return true;
	}
	
	public boolean isZero(myBinaryNumber A){
		for (int i = A.getSize()-1 ; i >= 0; i--){
			if (A.getBit(i) == 1){
				return false;
			}
		}
		return true;
	}
	
	public myBinaryNumber[] equalize(myBinaryNumber A, myBinaryNumber B){
		myBinaryNumber[] arr = new myBinaryNumber[2];
		if (A.getSize() >= B.getSize()){
			myBinaryNumber tempA = new myBinaryNumber(A.getSize()+(A.getSize()%2));
			myBinaryNumber tempB = new myBinaryNumber(A.getSize()+(A.getSize()%2));
			for (int i = 0 ; i < B.getSize();i++){
				try {
					tempB.setBit(i, B.getBit(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0 ; i < tempA.getSize() ; i++){
				try {
					tempA.setBit(i, A.getBit(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			arr[0] = tempA;
		    arr[1] = tempB;
		} else {
			myBinaryNumber tempB = new myBinaryNumber(B.getSize() + (B.getSize()%2));
			myBinaryNumber tempA = new myBinaryNumber(B.getSize() + (B.getSize()%2));
			for (int i = 0 ; i < A.getSize();i++){
				try {
					tempA.setBit(i, A.getBit(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			for (int i = 0 ; i < tempB.getSize() ; i++){
				try {
					tempB.setBit(i, B.getBit(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			arr[0] = tempA;
		    arr[1] = tempB;
		}
		return arr;
	}
	
	public myBinaryNumber[] divide(myBinaryNumber A, myBinaryNumber B){
		
		myBinaryNumber[] arr = new myBinaryNumber[4];
		int size1 = A.getSize();
		int half1 = size1/2;
		int size2 = B.getSize();
		int half2 = size2/2;
		
		myBinaryNumber Al = new myBinaryNumber(half1); 
		myBinaryNumber Ar = new myBinaryNumber(size1 - half1); 
		myBinaryNumber Bl = new myBinaryNumber(half2); 
		myBinaryNumber Br = new myBinaryNumber(size2 - half2); 
		
		for (int position = 0 ; position < Al.getSize(); position++){
			try {
				Al.setBit(position, A.getBit(position+size1-half1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int position = 0 ; position < Ar.getSize() ; position++){
			try {
				Ar.setBit(position, A.getBit(position));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int position = 0 ; position < Bl.getSize(); position++){
			try {
				Bl.setBit(position, B.getBit(position+size2-half2));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		for (int position = 0 ; position < Br.getSize() ; position++){
			try {
				Br.setBit(position, B.getBit(position));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		arr[0] = Al;
		arr[1] = Ar;
		arr[2] = Bl;
		arr[3] = Br;
		
		return arr;
	  }
  
  public myBinaryNumber multiplication(myBinaryNumber A, myBinaryNumber B){
	  
	  
	  myBinaryNumber[] equalize = equalize(A,B);
	  myBinaryNumber N1 = equalize[0];
	  myBinaryNumber N2 = equalize[1];
	  /*System.out.println("Multiplying -");
	  N1.printNumber();
	  N2.printNumber();
	  */
	  
	  
	  myBinaryNumber ans = new myBinaryNumber(2*Math.max(N1.getSize(),N2.getSize()));
	  
	  if (isOne(N1)){
			  ans = addition(ans,N2);
			  //System.out.print("Giving answer");
			  //ans.printNumber();
	  } else if (isOne(N2)){
			  ans = addition(ans,N1);
			  //System.out.print("Giving answer");
			  //ans.printNumber();
	  } else if(!(isZero(N1) || isZero(N2))){
		  myBinaryNumber[] division = divide(N1,N2);
		  
		  
		  
		  myBinaryNumber ans1 = multiplication(division[0],division[2]);
		  myBinaryNumber ans2 = multiplication(division[0],division[3]);
		  myBinaryNumber ans3 = multiplication(division[1],division[2]);
		  myBinaryNumber ans4 = multiplication(division[1],division[3]);
		  
		 /* System.out.println("Shift add to ");
		  ans.printNumber();
		  System.out.println("Shifting ");
		  ans1.printNumber();
		  System.out.println("For " + N1.getSize());*/
		  
          ans = shiftAdd(ans,ans1,N1.getSize())  ;
          ans = shiftAdd(ans,ans2,N1.getSize()/2)  ;
          ans = shiftAdd(ans,ans3,N1.getSize()/2)  ;
          ans = shiftAdd(ans,ans4,0)  ;
	  } 
		 return ans; 
  }
  
}