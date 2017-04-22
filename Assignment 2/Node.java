/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bikash Gpt
 */
public class Node {
    private Object element;
    private Node next;
    public Node(){
        this(null,null);
    }
    
    public Node(Object e,Node n){
        element=e;
        next=n;
    }
    
    public Object getElement(){
        return element;
        
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setElement(Object newElem){
        element=newElem;
    }
    
    public void setNext(Node newNext){
        next=newNext;
    }
    
}
