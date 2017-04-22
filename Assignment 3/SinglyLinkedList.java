/**
 *
 * @author Bikash Gpt
 */


//This class maintains the name of participants
public class SinglyLinkedList<T> {

	private static class Node<T> {
		private final T element;
		private Node<T> next;
		public Node(T e, Node<T> n){
			element = e;
			next = n;
		}
		public T getElement(){
			return element;
		}
		public Node<T> getNext(){
			return next;
		}
		public void setNext(Node<T> n){
			next = n;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public SinglyLinkedList(){
		head = tail = null;
		size = 0;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public T first(){
		if(isEmpty())
			return null;
		return head.getElement();
	}

	public T last(){
		if(isEmpty())
			return null;
		return tail.getElement();
	}

	public void addFirst(T e){
		head = new Node<>(e, head);
		if(size == 0)
			tail = head;
		size++;
	}

	public void addLast(T e){
		Node<T> newest = new Node<>(e, null);
		if(isEmpty()){
			head = newest;
		}
		else
			tail.setNext(newest);
		tail = newest;
		size++;
	}

	public T removeFirst(){
		if(isEmpty())
			return null;
		T answer = head.getElement();
		head = head.getNext();
		size--;
		if(size == 0){
			tail = null;
		}
		return answer;
	}

	public T find(String ID){
		if(isEmpty())
			return null;
		Node<T> p = head;
		while(p != null){
			if(p.element.equals(ID))
				return p.element;
			p = p.next;
		}
		return null;
	}

	public void print(){
		Node<T> p = head;
		while(p != null){
			System.out.println(p.element.toString());
			p = p.next;
		}
	}
}