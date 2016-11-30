
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = new Node<E>(null);
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
    	binaryInsert(data, root);
    }
    
    private void binaryInsert(E data, Node<E> node){	//recursive insert
    	
    	if (node.getData() == null){
    		node.setData(data);
    	}
    	else if (node.getData().compareTo(data) > 0){
    		if (node.getLeftChild() == null){
    			node.setLeftChild(new Node<E>(data));
    			node.getLeftChild().setParent(node);

    		} else {
    			binaryInsert(data, node.getLeftChild());
    		}	
    	}
    	else if (node.getData().compareTo(data) <= 0){
    		if (node.getRightChild() == null){
    			node.setRightChild(new Node<E>(data));
    			node.getRightChild().setParent(node);
    		} else {
    			binaryInsert(data, node.getRightChild());
    		}
    	}
    }

    public Node<E> find(E data){
    	Node<E> n = null;
    	Node<E> base = root;
    	
    	while (base != null){
    		if (base.getData() == data){
    			n = base;
    			base = null;
    		}
    		else if (base.getData().compareTo(data) > 0){
    			if (base.getLeftChild() != null){
    				base = base.getLeftChild();
    			} else{
    				base = null;
    			}
    					
    		}
    		else if (base.getData().compareTo(data) <= 0){
    			if (base.getRightChild() != null) {
    				base = base.getRightChild();
    			} else {
    				base = null;
    			}
    		}
    		
    	}
    	
    	return n;
    }
    

    public void delete(E data){
    	Node<E> delNode = find(data);
    	if (delNode != null) { 
	    	if (delNode.getRightChild() == null && delNode.getLeftChild() == null){		//if node has no children
	    		 if ( delNode == delNode.getParent().getLeftChild()){
	    			 delNode.getParent().setLeftChild(null);
	    		 } else{
	    			 delNode.getParent().setRightChild(null);
	    		 }
	    	}
	    	
	    	if (delNode.getRightChild() != null && delNode.getLeftChild() == null){		//if node has one right child
		    	if ( delNode == delNode.getParent().getLeftChild()){
		    		delNode.getParent().setLeftChild(delNode.getRightChild());
				} else {
					delNode.getParent().setRightChild(delNode.getRightChild());
				}
	    	}
	    	
	    	if (delNode.getRightChild() == null && delNode.getLeftChild() != null){		//if node has one left child
		    	if ( delNode == delNode.getParent().getLeftChild()){
		    		delNode.getParent().setLeftChild(delNode.getLeftChild());
				} else {
					delNode.getParent().setRightChild(delNode.getLeftChild());
				}
	    	}
	    	
	    	if (delNode.getRightChild() != null && delNode.getLeftChild() != null){		//if node has 2 children
		    	Node<E> n = delNode.getRightChild();	
		    	while (n.getLeftChild() != null){				//finds the left-most node
		    		n = n.getLeftChild();  		
		    	}
		    	delNode.setData(n.getData());									//sets the left-most node to the deleted node
		    	n.setData(null);
	    	}
    	}

    }
    

    public void traverse(String order, Node<E> top) {
        if (top != null){
        	if (order.equals("preorder")){		//converted switch ops to if-else for 1.6 parity
                preorderSearch(top);
                System.out.println("");
        	}
        	if (order.equals("inorder")){
                inorderSearch(top);
                System.out.println("");
        	}
        	if (order.equals("postorder")){
                postorderSearch(top);
                System.out.println("");
        	}
            }
        }
    
    private void preorderSearch(Node<E> top){		//recursive preorder
    	if (top.getData() != null){
    		System.out.print(top.getData() + " ");
    		if (top.getLeftChild() != null){
    			preorderSearch(top.getLeftChild());
    		}
    		if (top.getRightChild() != null) {
    			preorderSearch(top.getRightChild());
    		}
    	}
    }
    
    
    private void inorderSearch(Node<E> top){		//recursive inorder
    	if (top.getData() != null){
    		if (top.getLeftChild() != null){
    			inorderSearch(top.getLeftChild());
    		}    		
    		System.out.print(top.getData() + " ");
    		if (top.getRightChild() != null) {
    			inorderSearch(top.getRightChild());
    		}
    	}
    }
    
    
    private void postorderSearch(Node<E> top){		  //recursive postorder
    	if (top.getData() != null){
    		if (top.getLeftChild() != null){
    			postorderSearch(top.getLeftChild());
    		}    		
    		if (top.getRightChild() != null) {
    			postorderSearch(top.getRightChild());
    		}
    		System.out.print(top.getData() + " ");
    	}
    }
}
