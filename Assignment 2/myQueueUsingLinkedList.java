class myQueueUsingLinkedList<E>{

    private SinglyLinkedList<E> L;
    private Node head;
    private Node tail;
    private int size;
    //Other variables to be defined by student
        
    
        public myQueueUsingLinkedList(){
        L = new SinglyLinkedList<>();
        head=null;
        tail=null;
        size=0;
        

        //Other initializations to be done by student
    }
    
    //This method should return the size of the stack
    public int getSize(){
        return size;
        //To be written by student
    }
    
    //This should implement the enqueue operation of stack
    public void enqueue(E value){
        Node node=new Node();
        node.setElement(value);
        node.setNext(null);
        if(size==0)
            head=node;
        else
            tail.setNext(node);
        tail=node;
        size++;
        
        //To be written by student
    }
    
    //This should implement the dequeue operation of stack.
    //This method should throw an exception in case the queue is empty.
    public E dequeue() throws EmptyQueueException{
        if (size==0)
            throw new EmptyQueueException("Queue is empty");
        E value=(E)head.getElement();
        head=head.getNext();
        size--;
        if(size==0)
            tail=null;
        return value;
        //To be written by student
    }

    private static class EmptyQueueException extends Exception {

        public EmptyQueueException(String queue_is_empty) {
        }
    }

    private static class SinglyLinkedList<E> {

        public SinglyLinkedList() {
        }
    }


    
}