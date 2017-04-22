/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 */
class myQueueUsingDynamicArray{

    private dynamicArray A;
    
    int front=0;
    int rear=-1;
    int currentSize=0;
    //Other variables to be defined by student
    
    public myQueueUsingDynamicArray(){
        A = new dynamicArray();
        
       
       
        
        //Othe initializations to be done by student
    }
    
    //This method should return the number of elements in the queue
    public int getSize(){
        return(A.getSize());
        //To be written by student
    }

    
 
    //This should implement the enqueue operation of Queue
    //This method add element at the end of the queue.
    public void enqueue(int value){
        if (isQueueFull()){
            System.out.println("Queue is full,increase capacity..");
            A.doubleSize(); 
            
        }
        rear++;
        if(rear>=A.getSize() && currentSize !=A.getSize()){
            rear=0;
            
        }
        A.modifyElement(value,rear);
        currentSize++;
       
        System.out.println("Adding: " + value);
        //To be written by student
    }
    
    //This should implement the dequeue operation of Queue
    //This method should throw an exception in case the queue is empty.
    public int dequeue(){
        if (isQueueEmpty()){
            System.out.print("Underflow ! Unable to remove element from Queue");    
        } else {
            front++;
            
            
            if(front>A.getSize()-1){

                System.out.println("removed: "+A.getElement(front-1));
                
                //System.out.println("size of array= "+A.getSize());
                
                
                
                return(A.getElement(front-1));
                
               
            }else {
            
                System.out.println("removed: "+A.getElement(front-1));
                
                
                
                rear--;
                currentSize--;
                
               
                System.out.println("size of new array= "+currentSize);
                
                return(A.getElement(front-1));
            }
            
            
        }
        System.out.println("currentSize is "+currentSize);
        currentSize--;
        rear--;
        
        
         
        return(A.getElement(front-1));
    }
    
    
    public boolean isQueueEmpty(){
        boolean status = false;
        if (currentSize == 0){
            status = true;
        }
        return status;
    }
    
    
    public boolean isQueueFull(){
        boolean status = false;
        if (currentSize == A.getSize()){
            status = true;
         }
        return status;
     }
    
    
    
    

 

    
    
    
    
    //
    public static void main(String a[]){
        myQueueUsingDynamicArray queue= new myQueueUsingDynamicArray();
        queue.enqueue(4);
        queue.dequeue();
        queue.enqueue(56);
        queue.enqueue(2);
        queue.enqueue(67);
        queue.dequeue();
        queue.enqueue(24);
        queue.enqueue(98);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(435);
        queue.dequeue();
        queue.dequeue();
        

        
    }
}
