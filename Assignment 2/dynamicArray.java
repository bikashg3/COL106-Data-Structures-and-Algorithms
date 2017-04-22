/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 */
class dynamicArray{
    private int[] A;//Reference to the array that is maintained.
    private int arraySize;//This stores the size of the current array A
    
    public dynamicArray(){
        //Start with an array of size 1
        A = new int[1];
        arraySize = 1;
    }
    
    //This method should return the current size of the dynamic array
    public int getSize(){
        return(arraySize);
    }
    
    //This method should double the size of the array A and copy all
    //the previous elements in the first half of the array (of double size)
    public void doubleSize(){
      int[] temp = new int[arraySize*2];
    for (int i = 0; i < arraySize; i++)
    {
        temp[i]= A[i];
    }
    arraySize= arraySize*2;
    A=temp;
        
    }
    
    ///////////////
    public void increaseCapacity(){
         
        //create new array with double size as the current one.
        int newCapacity = arraySize*2;
        int[] newArr = new int[newCapacity];
        //copy elements to new array, copy from rear to front
        int front=0;
        int tmpFront = front;
        int index = -1;
        int currentSize=0;
        int rear=-1;
        while(true){
            newArr[++index] = getElement(tmpFront);
            tmpFront++;
            if(tmpFront == getSize()){
                tmpFront = 0;
            }
            if(currentSize == index+1){
                break;
            }
        }
        //make new array as queue
        A = newArr;
        System.out.println("New array capacity: "+getSize());
        //reset front & rear values
        front = 0;
        rear = index;
    }
    
    
    
    
    
    
    
    
    
    //This method should halve the size of the array by copying the first-half
    //in an array of half the size and ignoring the second-half
    public void halveSize(){
        int[] temp=new int[arraySize/2];
        for(int i=0; i<(arraySize/2);i++)
        {
            temp[i]=A[i];
        }
        arraySize=arraySize/2;
        A=temp;
        //To be written by the student.
    }
    
    //This method should return the integer at array index "index"
    //If index exceeds the size of the array, then this
    //method should throw an exception
    public int getElement(int index){
        if (index>arraySize)
        {
            throw new ArrayIndexOutOfBoundsException();
         }
       else
                return(A[index]);
        //To be written by the student.
    }
    
    //This method should write integer "value" in array location "index"
    //In case, "index" exceeds the size of the array, then this method should
    //throw an exception.
    public void modifyElement(int value, int index){
        int i;
            if (index>arraySize){
            throw new ArrayIndexOutOfBoundsException();
         }
         else{
            
        
            for (i=0;i<=arraySize;i++){
            if(i==index){
                A[index]=value;
                break;
            }
        }    
        }
        //To be written by the student.
    }

   
}