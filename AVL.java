public class AVL<E extends Comparable<E>> {
    private BST<E> tree;

    public AVL(){
        tree = new BST<E>();
    }

    public Node<E> getRoot(){
        return tree.getRoot();
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an AVL tree
    	tree.insert(data);
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
    	tree.delete(data);
    	//check for balance, fix if not
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
    	tree.traverse("preorder", top);
    }

    private void unbalanceChecker(Node<E> start){   //from start's parent, check if the tree is unbalanced
    	Node<E> currNode = start.getParent();
    	while (currNode != null){
    		currNode = currNode.getParent();
    		if (currNode.isUnbalanced() == false){
    			unbalanceFixer(currNode);
    		}
    	}
    }
    
    private void unbalanceFixer(Node<E> node){
    	Node<E> z = node;
    	Node<E> y;
    	Node<E> x;
    	
    	Node<E> yLeft = z.getLeftChild();
    	Node<E> yRight = z.getRightChild();
    	if (yLeft.getHeight() >= yRight.getHeight()){   //Todo: set flags if it's the left or right to discern the shape of the unbalance and the future shape of Z-Y-X
    		y = yLeft;
    	} else {
    		y = yRight;
    	}
    	
    	Node<E> xLeft = y.getLeftChild();
    	Node<E> xRight = y.getRightChild();
    	if (xLeft.getHeight() >= xRight.getHeight()){
    		x = xLeft;
    	} else {
    		x = xRight;
    	}
    	
    	
    	
    	
    	
    	Node<E> xDeltaLeftNode = x.getLeftChild();     //Todo: check the same flags as the comparison to exclude children of z/y/x that are themselves part of z/y/x
    	Node<E> xDeltaRightNode = x.getRightChild();
    	
    	Node<E> yDeltaLeftNode = y.getLeftChild();
    	Node<E> yDeltaRightNode = y.getRightChild();
    	
    	Node<E> zDeltaLeftNode = z.getLeftChild();
    	Node<E> zDeltaRightNode = z.getRightChild();
    	
    	
    	
    	//main rotation logic w/ the deltas; grab shape from the flags, then set them (and their children) accordingly.
    	//issue: the method for unbalancing
    	
    	
    }
    

    public void rightRotate(Node<E> y){
    	
    	/*
    	If x is the root of the tree to rotate with left child subtree T1 and right child y, 
    	where T2 and T3 are the left and right children of y:
			x becomes left child of y and T3 as its right child of y
			T1 becomes left child of x and T2 becomes right child of x
		*/
    }

    public void leftRotate(Node<E> x){
    
    	/*
    	If y is the root of the tree to rotate with right child subtree T3 and left child x, 
    	where T1 and T2 are the left and right children of x:
			y becomes right child of x and T1 as its left child of x
			T2 becomes left child of y and T3 becomes right child of y
		*/
		
    }
    
    // HINT: You may want to create extra methods such as trinode reorder, or update heights, etc
}
