/**
 *
 * @author Bikash Gpt
 */


class Node<V>{
	public int key;
	public V value;
	public Node<V> leftChild, rightChild, parent;

	public Node(){
		parent = leftChild = rightChild = null;
	}
}

//This is a template heap for both heap of an event and heap of heaps
public class Heap<V>{
	public Node<V> root;
	public int size;
	public Node<V> lastNode;

	public Heap(){
		size = 0;
		root = lastNode = null;
	}

	public void swapVals(Node<V> p, Node<V> q){
        V temp1 = p.value;
        int temp2 = p.key;
        p.key = q.key;
        p.value = q.value;
        q.key = temp2;
        q.value = temp1;
    }

//Returns the maximum key in the heap
    public int max(){
        return(root.key);
    }

//Shifts the pointer so that new addition can happen at appropriate location
    public void incrementLastNode(){
        if(lastNode == null){lastNode = root; return;}
        
        if(lastNode.rightChild == null)return;
        
        Node<V> p = lastNode;
        while(p.parent!=null && p.parent.rightChild == p)p = p.parent;
        if(p.parent != null)p = p.parent.rightChild;
        while(p.leftChild != null)p = p.leftChild;
        lastNode = p;
    }

//Shifts the pointer back to the last inserted node if it is pointing to two empty childs
    public void decrementLastNode(){
        if(lastNode.leftChild != null || lastNode.rightChild != null)return;
        
        Node<V> p = lastNode;
        while(p.parent!=null && p.parent.leftChild == p)p = p.parent;
        if(p.parent != null)p = p.parent.leftChild;
        while(p.rightChild != null)p = p.rightChild;
        lastNode = p.parent;
    }

    public boolean isEmpty(){
        if(size == 0)return(true);
        else return(false);
    }

//Bubbles the node up in the heap till it is lower than its parent
    public void upHeapBubble(Node<V> v){
        while(v.parent != null && v.parent.key < v.key){
            swapVals(v, v.parent);
            v = v.parent;
        }
    }

    public boolean isLeaf(Node<V> v){
        if(v.leftChild == null && v.rightChild == null){
            return true;
        }
        else
            return false;
    }

//Bubbles the node down in the heap till it is greater than its child
    public void downHeapBubble(Node<V> v){
        while(!isLeaf(v)){
            if(v.key >= v.leftChild.key && (v.rightChild == null || v.key >= v.rightChild.key))
                return;
            else if(v.key < v.leftChild.key && (v.rightChild == null || v.leftChild.key >= v.rightChild.key)){
                swapVals(v, v.leftChild);
                v = v.leftChild;
            }
            else{
                swapVals(v, v.rightChild);
                v = v.rightChild;
            }
        }
    }

//Inserts a node with value v
    public void insert(int k, V v){
        Node<V> N = new Node<>();
        N.key = k;
        N.value = v;
        
        //If this is the very first element of the heap
        if(size == 0){
            root = N;
            lastNode = N;
            size++;
            return;
        }
        if(lastNode.leftChild == null)lastNode.leftChild = N;
        else lastNode.rightChild = N;
        N.parent = lastNode;
        size++;
        upHeapBubble(N);
        incrementLastNode();
    }

//Returns the value of node with maximum value
    public V removeMax(){
        V result = root.value;
        if(size == 1){root = lastNode = null; size--; return(result);}
        
        decrementLastNode();
        Node<V> N;
        if(lastNode.rightChild != null){N = lastNode.rightChild; lastNode.rightChild = null;}
        else {N = lastNode.leftChild; lastNode.leftChild = null;}
        
        //Copy the entry in N to the root
        root.key = N.key; root.value = N.value;
        size--;
        downHeapBubble(root);
        return(result);
    }

//Traverse the heap in preorder fashion: node-->leftchild-->rightchild
    public void preOrderTraversal(Node<V> v){
        System.out.println("(" + v.key + ", " + v.value.toString() + ")");
        if(v.leftChild != null)preOrderTraversal(v.leftChild);
        if(v.rightChild != null)preOrderTraversal(v.rightChild);
    }

//Delete a specific node
    public void deleteNode(Node<V> N){
        if(size == 1){
            root = lastNode = null; 
            size--;
            return;
        }

        decrementLastNode();
        Node<V> K;
        if(lastNode.rightChild != null){K = lastNode.rightChild; lastNode.rightChild = null;}
        else {K = lastNode.leftChild; lastNode.leftChild = null;}
        size--;
        N.key = K.key; N.value = K.value;
        downHeapBubble(N);
    }

//Changes the key to k of a node with value v
    public void modifyKeyOfNode(V v, int k) throws NullPointerException{
        Node<V> N = findNode(root, v);
        if(N == null)
        	throw new NullPointerException();//exception throw
        V val = N.value;
        deleteNode(N);
        insert(k, val);
    }

//Finds a node with value v; Pass root of heap as x
    public Node<V> findNode(Node<V> x, V v){
        if(x == null)
            return x;
        else if(x.value.equals(v)){
            return x;
        }
        else if(isLeaf(x))
            return null;
        else if(x.rightChild != null){
            Node<V> y = findNode(x.rightChild, v);
            if(y == null)
                y = findNode(x.leftChild, v);
            return y;
        }
        else{
            if(x.leftChild.value.equals(v))
                return x.leftChild;
            return null;
        }
    }

//Returns the value of a node which is equal to v passed
    public V find(V v) throws NullPointerException{
        Node<V> x = findNode(root, v);
        if(x == null)
            throw new NullPointerException();
        return x.value;
    }

//Deletes the node with value v
    public boolean deleteValue(V v){
    	Node<V> N = findNode(root, v);
    	if(N == null)
    		return false;
    	deleteNode(N);
    	return true;
    }

//Prints the heap in preoder fashion
    public void print(){
        preOrderTraversal(root);
    }
}