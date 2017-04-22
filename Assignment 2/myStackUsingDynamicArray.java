
import java.util.EmptyStackException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 */
class myStackUsingDynamicArray{
    
   
    private dynamicArray A;
    private int topOfStack ;
    
    
    
   
    
    //Other variables to be defined by student
    
    public myStackUsingDynamicArray(){
        
        A = new dynamicArray();
        topOfStack=-1;
        
        
        //Othe initializations to be done by student
    }
    
    // This method returns true if the Stack is full
    public boolean isStackFull(){
        return (topOfStack==A.getSize()-1);
    }
    
    // This method returns true if the stack is empty
     public boolean isStackEmpty(){
         return (topOfStack==-1);
     }
    
    //This method should return the size of the stack
    public int getSize(){
        return (A.getSize());
        //To be written by student
    }
    
    
    //This should implement the push operation of stack
    public void push(int value){
        if (isStackFull())   
        {   
            System.out.println(("Stack is full.Increasing the capacity"));
            A.doubleSize();
        }
        System.out.println("Adding: "+ value);
        
        A.modifyElement(value,++topOfStack);
        
        //To be written by student
    }
    public boolean isEmpty(){
        return (A.getSize()==0);
    }
    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public int pop() throws Exception {
        if (isEmpty()) throw new Exception("EmptyStackException()");
        System.out.println("removed item: "+ A.getElement(topOfStack));
          if (topOfStack<(A.getSize()/2)){
            A.halveSize();
            System.out.println("ye naya size hai " + A.getSize());
            
            
           }
        
         return (A.getElement(topOfStack--));
         
           
        //To be written by student
    }
    
    
    
    public int peek(){
        return A.getElement(topOfStack);
    }
    
    public static void main(String[] args){
        myStackUsingDynamicArray stack=new myStackUsingDynamicArray();
        for(int i=1;i<20;i++){
            stack.push(i);
        }
       for(int i=1;i<25;i++){
           
          
            try {
                  
                  stack.pop();
                  System.out.println("pop hogya h");
                  
            } catch (Exception e){
                
            }
        }
    }
}
