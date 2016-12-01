public class AVL<E extends Comparable<E>> {
    private BST<E> tree;

    public AVL(){
        tree = new BST<E>();
    }

    public Node<E> getRoot(){
        return tree.getRoot();
    }
    
    private void updateHeights(int height, Node<E> start){
    	
    	start.setHeight(height);
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an AVL tree
    	tree.insert(data);
    	Node<E> added = tree.find(data);
        Node<E> ancestor = added.getParent();
        
        while (ancestor != null){
        	ancestor.setHeight(ancestor.getHeight() + 1);
        	ancestor = ancestor.getParent();
        }
    	
        unbalanceChecker(added);
    	//Todo: increment the height of all of the new node's ancestors by 1, then
    	
    	//check for balance, fix if not
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
    	return tree.find(data);
    }

    public void delete(E data){
    	// Preform a regular delete
    	// Check to make sure the tree remains an AVL tree
    	
    	Node<E> removed = tree.find(data);
        Node<E> ancestor = removed.getParent();
        while (ancestor != null){
        	ancestor.setHeight(ancestor.getHeight() - 1);
        	ancestor = ancestor.getParent();
        }
        removed = removed.getParent();
    	unbalanceChecker(removed);
    	tree.delete(data);
    	//check for balance, fix if not
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	tree.traverse(order, top);
    }
    
    private Node<E> nodeSubtree(Node<E> node, String direction){		//method to get a node's left/right subtree without running into null pointer exceptions if it's null
    	Node<E> res = null;
    	if (node == null){
    		res = null;
    	}
    	
    	else if (direction.equals("left")){
    		res = node.getLeftChild();
    	}
    	
    	else if (direction.equals("right")){
    		res = node.getRightChild();
    	}
    	
    	return res;
    }

    private void unbalanceChecker(Node<E> start){   //from start, check if the tree is unbalanced
    	while (start != null){
    		if (start.isUnbalanced() == true){
    			unbalanceFixer(start);
    			break;
    		}
    		start = start.getParent();
    	}
    }
    
    private void unbalanceFixer(Node<E> node){
    	Node<E> z = node;
    	Node<E> y;
    	Node<E> x;
    	
    	Node<E> T0 = null;
    	Node<E> T1 = null;
    	Node<E> T2 = null;
    	Node<E> T3 = null;
       
    	int rotationType = 0; 
    	String child = null;
    	
    	if (node.getParent() != null){
    		if (node == node.getParent().getLeftChild()){
    			child = "Left";
    		} else {
    			child = "Right";
    		}
    	}

    	
    	Node<E> yLeft = z.getLeftChild();				//Determines what Y and X are
    	Node<E> yRight = z.getRightChild();
    	int leftH = 0;
    	int rightH = 0;
    	if (yLeft == null){ 
    		leftH = 0; 
    		} else {
    			leftH = yLeft.getHeight();
    	}
    	if (yRight == null){ 
    		rightH = 0; 
    		} else {
    			rightH = yRight.getHeight();
    	}
    	if (leftH >= rightH){
    		y = node.getLeftChild();
    	} else {
    		y = node.getRightChild();
    	}
    	
    	Node<E> xLeft = y.getLeftChild();
    	Node<E> xRight = y.getRightChild();
    	if (xLeft == null){ 
    		leftH = 0; 
    		} else {
    			leftH = xLeft.getHeight();
    	}
    	if (xRight == null){ 
    		rightH = 0; 
    		} else {
    			rightH = xRight.getHeight();
    	}
    	if (leftH >= rightH){
    		x = y.getLeftChild();
    	} else {
    		x = y.getRightChild();
    	}
    	 

    	
    	if (y == z.getRightChild()){				//Determines which shape the imbalance is in, based on page 156 of the book
    		if (x == y.getRightChild()){
    			T0 = nodeSubtree(z, "left");
    			T1 = nodeSubtree(y, "left");
    			T2 = nodeSubtree(x, "left");
    			T3 = nodeSubtree(x, "right");
    			rotationType = 1;
    		} else if (x == y.getLeftChild()){
    			T0 = nodeSubtree(z, "left");
    			T1 = nodeSubtree(x, "left");
    			T2 = nodeSubtree(x, "right");
    			T3 = nodeSubtree(y, "right");
    			rotationType = 3;
    		}
    	}
    	
    	else if (y == z.getLeftChild()){
    		if (x == y.getLeftChild()){
    			T0 = nodeSubtree(x, "left");
    			T1 = nodeSubtree(x, "right");
    			T2 = nodeSubtree(y, "right");
    			T3 = nodeSubtree(z, "right");
    			rotationType = 2;
    		} else if (x == y.getRightChild()){
    			T0 = nodeSubtree(y, "left");
    			T1 = nodeSubtree(x, "left");
    			T2 = nodeSubtree(x, "right");
    			T3 = nodeSubtree(z, "right");
    			rotationType = 4;
    		}
    	}
    	
    	
    	
    	if (rotationType == 1){						//Actual rotation logic, reconfigures parent/child links depending on shape
    		y.setParent(node.getParent());
    		if (child == null){

    		}
    		else if (child.equals("Left")){
    			y.getParent().setLeftChild(y);
    		} else if (child.equals("Right")){
    			y.getParent().setRightChild(y);
    		}
    		node.setLeftChild(T0);
    		node.setRightChild(T1);
    		node.setParent(y);
    		y.setLeftChild(node);
    		x.setLeftChild(T2);
    		x.setRightChild(T3);
    		x.setParent(y);
    		y.setRightChild(x);
    	}
    	
    	if (rotationType == 2){
    		y.setParent(node.getParent());
    		if (child == null){
    			
    		}
    		else if (child.equals("Left")){
    			y.getParent().setLeftChild(y);
    		} else if (child.equals("Right")){
    			y.getParent().setRightChild(y);
    		}
    		node.setLeftChild(T2);
    		node.setRightChild(T3);
    		node.setParent(y);
    		y.setRightChild(node);
    		x.setLeftChild(T0);
    		x.setRightChild(T1);
    		x.setParent(y);
    		y.setLeftChild(x);    		
    	}
    	
    	if (rotationType == 3){
    		x.setParent(node.getParent());
    		if (child == null){
    			
    		}
    		else if (child.equals("Left")){
    			x.getParent().setLeftChild(x);
    		} else if (child.equals("Right")){
    			x.getParent().setRightChild(x);
    		}
    		node.setLeftChild(T0);
    		node.setRightChild(T1);
    		node.setParent(x);
    		x.setLeftChild(node);
    		y.setLeftChild(T2);
    		y.setRightChild(T3);
    		y.setParent(x);
    		x.setRightChild(y);    		
    	}
    		
    	if (rotationType == 4){
    		x.setParent(node.getParent());
    		if (child == null){
    			tree.getRoot();
    		}
    		else if (child.equals("Left")){
    			x.getParent().setLeftChild(x);
    		} else if (child.equals("Right")){
    			x.getParent().setRightChild(x);
    		}
    		node.setLeftChild(T2);
    		node.setRightChild(T3);
    		node.setParent(x);
    		x.setRightChild(node);
    		y.setLeftChild(T0);
    		y.setRightChild(T1);
    		y.setParent(y);
    		x.setLeftChild(y);    		
    	}
    	
    	
    }
    
}
