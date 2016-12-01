public class Node<E extends Comparable<E>> {
    private E data;
    private Node<E> leftChild;
    private Node<E> rightChild;
    private Node<E> parent;

    private int height;

    public Node(E d) {
        data = d;
        leftChild = null;
        rightChild = null;
        parent = null;
        height = 1;
    }

    public void setData(E d) {
        data = d;
    }

    public void setLeftChild(Node<E> lc) {
        leftChild = lc;
    }

    public void setRightChild(Node<E> rc) {
        rightChild = rc;
    }

    public void setParent(Node<E> p) {
        parent = p;
    }

    public void setHeight(int h){
        height = h;
    }

    public E getData() {
        return data;
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public Node<E> getParent() {
        return parent;
    }

    public int getHeight(){
        return height;
    }

    public boolean isUnbalanced(){
    
    	// Return true when this node's children's heights differ by more than 1
        // Note: When a child is null, its height is 0
    	int leftHeight = 0;
    	int rightHeight = 0;
    	
    	if (this.getLeftChild() == null){
    		leftHeight = 0;
    	} else { 
    		leftHeight += this.getLeftChild().getHeight();
    	}
    	
    	if (this.getRightChild() == null){
    		rightHeight = 0;
    	} else { 
    		rightHeight += this.getRightChild().getHeight();
    	}
    	
    	int delta = (leftHeight - rightHeight);
    	boolean res;
    	
    	if (delta > 1 || delta < -1){
    		res = true;
    	} else {
    		res = false;
    	}
    	
    	return res;
    	
    	
    	
    	
    }

}
