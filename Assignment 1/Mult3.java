
//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the O(n^{log 3}) Karatsuba algorithm discussed
//in class.

public class Mult3 extends binaryOperations{
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
  
	public myBinaryNumber twoComplement(myBinaryNumber N1){
	      for (int i = 0 ; i < N1.getSize();i++){
	    	  try {
				N1.setBit(i, (N1.getBit(i)+1)%2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      myBinaryNumber one = new myBinaryNumber("1");
	      N1 = addition(N1,one);
	      return N1;
	}
	
	public myBinaryNumber[] signExtend (myBinaryNumber N1, myBinaryNumber N2){
		myBinaryNumber[] equalize = equalize(N1,N2);
		myBinaryNumber temp1 = new myBinaryNumber(equalize[0].getSize()+1);
		myBinaryNumber temp2 = new myBinaryNumber(equalize[1].getSize()+1);
		for (int i = 0 ; i < temp1.getSize()-1; i++){
			try {
				temp1.setBit(i, equalize[0].getBit(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0 ; i < temp2.getSize()-1; i++){
			try {
				temp2.setBit(i, equalize[1].getBit(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			temp1.setBit(temp1.getSize()-1, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			temp2.setBit(temp2.getSize()-1, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		equalize[0] = temp1;
		equalize[1] = temp2;
		return equalize;
	}
	public myBinaryNumber subtract(myBinaryNumber N1, myBinaryNumber N2){
		
		
		myBinaryNumber[] signExtend = signExtend(N1,N2);
		myBinaryNumber A = signExtend[0];
	      myBinaryNumber B = twoComplement(signExtend[1]);
	      
	      myBinaryNumber ans1 = new myBinaryNumber(A.getSize()+1);
	      ans1 = addition(A,B);
	      if (ans1.getBit(ans1.getSize()-1) == 1){
	    	  try {
				ans1.setBit(ans1.getSize()-1, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	     return ans1;
	}
	public myBinaryNumber removeAllZero(myBinaryNumber A){
		int count = A.getSize()-1 ; 
		while ( count >= 0&& A.getBit(count) == 0){
			count--;
		}
		myBinaryNumber ans = new myBinaryNumber(count+1);
		for (int position = 0 ; position < ans.getSize(); position++){
			try {
				ans.setBit(position, A.getBit(position));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ans;
	}
  public myBinaryNumber multiplication(myBinaryNumber A, myBinaryNumber B){
	  myBinaryNumber[] equalize = equalize(removeAllZero(A),removeAllZero(B));
	  myBinaryNumber N1 = equalize[0];
	  myBinaryNumber N2 = equalize[1];
	  
      myBinaryNumber ans = new myBinaryNumber(N1.getSize()+N2.getSize());
      
      if (isOne(N1)){
		  ans = addition(ans,N2);
		
      } else if (isOne(N2)){
		  ans = addition(ans,N1);
		
      } else if(!(isZero(N1) || isZero(N2))){
		  myBinaryNumber[] division = divide(N1,N2);
		  
		  myBinaryNumber ans1 = multiplication(division[0],division[2]);
		  myBinaryNumber ans4 = multiplication(division[1],division[3]);
		  
		  myBinaryNumber part1 = multiplication(addition(division[0],division[2]),addition(division[1],division[3]));
		  myBinaryNumber part2 = addition(ans1,ans4);
		  
		  myBinaryNumber part6 = subtract(part1,part2);
		  ans = shiftAdd(ans,ans1,N1.getSize());
		  ans = shiftAdd(ans,part6,N1.getSize()/2);
		  ans = shiftAdd(ans,ans4,0);
      }
      return ans;
  }
}