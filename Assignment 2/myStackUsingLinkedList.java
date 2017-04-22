

class myStackUsingLinkedList<E>{

    private SinglyLinkedList<E> L;
    private Node top;
    private int size;
    //Other variables to be defined by student
    
    public myStackUsingLinkedList(){
        L = new SinglyLinkedList<>();
        top=null;
        size=0;
        
        //Other initializations to be done by student
    }
    
    //This method should return the size of the stack
    public int getSize(){
        return size;
        //To be written by student
    }
    public boolean isEmpty(){
        if (top==null)
                return true;
        return false;
              
    }
    
    //This should implement the push operation of stack
    public void push(E value){
        Node v=new Node(value,top);
        top=v;
        size++;
        //To be written by student
    }
    
    public E top() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        return (E) top.getElement();
    }
    
    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public E pop() throws EmptyStackException{
        if (isEmpty())
            throw new EmptyStackException("Stack is Empty");
        E temp=(E)top.getElement();
        top=top.getNext();
        size--;
        return temp;
        //To be written by student
    }

    private static class SinglyLinkedList<E> {

        public SinglyLinkedList() {
        }
    }

    private static class EmptyStackException extends Exception {

        public EmptyStackException() {
        }

        private EmptyStackException(String stack_is_Empty) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}