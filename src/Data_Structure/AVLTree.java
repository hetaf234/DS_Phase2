package Data_Structure;



public class AVLTree<T> {
AVLNode<T> root, current;
public AVLTree() {
	root=null;
}
public boolean empty() {
	return root==null; 
}

public boolean full() {
	return false;
}

public T retrieve() {
	return current.data;
}

public boolean findkey(int tkey) {
	AVLNode<T> p=root , q= root;
	if (empty())
		return false;
	while(p!=null) {
		q=p;
		if (p.key==tkey) {
			current=p;
			return true;
		}//if 
		else if(tkey<p.key)
			p=p.left;
		else 
			p=p.right;
	
					
	}//while p!=null 
	// not found 
	current =q ;
	return false;
}// findkey

public boolean insert(int k, T val) {
	AVLNode<T> p , q=current ;
	if (findkey(k)) {
		current=q;
		return false; // key found , can not add it 
	}//if
	p= new AVLNode<T>(k, val);
	if(empty()) {
		root=current=p;
		return true;
	}//if empty
	else {
		if(k< current.key) 
			current.left=p;
		else
			current.right=p;
		
		current=p;
		
		root=balanceAfterInsert(root, k);
		return true;
			
		
	}//else
	
	
}//insert

private AVLNode<T> balanceAfterInsert(AVLNode<T> p, int k){
	if (p==null)
		return null;
	
	if(k<p.key) {
		p.left=balanceAfterInsert(p.left,k);
		p.bal--;
	}//if(k<p.key)
	
	else if(k>p.key) {
		p.right=balanceAfterInsert(p.right,k);
		p.bal++;
	}//else if(k>p.key)
	
	else {
		return p;// key exists  
	}//else
	
	
// unbalance cases 
	if (p.bal== -2) {// left 
		if (p.left.bal<=0) 
			p=rotateRight(p); //	LL
		else 
			p=rotateLeftRight(p); //	LR
	}//if (p.bal==-2)
	
	else if (p.bal==2) {// right 
		if (p.right.bal>=0) 
			p=rotateLeft(p); //	RR
		else 
			p=rotateRightLeft(p); //	RL
	}//else if (p.bal==2)
	return p;
	
	
	
	
	
}//balanceAfterInsert

private AVLNode<T> rotateLeft(AVLNode<T> p){
	AVLNode<T> q =p.right;
	
	p.right=q.left;
	q.left=p;
	
	if(q.bal==0) {
		p.bal=1;
		q.bal=-1;
		
	}else {
		p.bal=0;
		q.bal=0;
	}
	return q;
}//rotateLeft

private AVLNode<T> rotateRight(AVLNode<T>p){
AVLNode<T> q =p.left;
	
	p.left=q.right;
	q.right=p;
	
	if(q.bal==0) {
		p.bal=-1;
		q.bal=1;
		
	}else {
		p.bal=0;
		q.bal=0;
	}
	return q;
}//rotateRight
 
private AVLNode<T> rotateLeftRight(AVLNode<T>p){
	p.left=rotateLeft(p.left);
	return rotateRight(p);
}//rotateLeftRight

private AVLNode<T> rotateRightLeft(AVLNode<T>p){
	p.right=rotateRight(p.right);
	return rotateLeft(p);
}//rotateRightLeft

public boolean removeKey(int k) {
	if(!findkey(k))
		return false;
	
	root=deleteAVL(root,k);
	current=root;
	return true;
}//removeKey

private AVLNode<T> deleteAVL(AVLNode<T> p, int k){
	if(p==null)
		return null ;
	if(k<p.key) {
		p.left=deleteAVL(p.left,k);
		p.bal++;
	}//if(k<p.key)
	else if(k>p.key) {
		p.right=deleteAVL(p.right,k);
		p.bal--;
	}//if(k>p.key)
	else {// key found 
		// case 1 and 2
		if(p.left==null)
			return p.right;
		if(p.right==null)
			return p.left;
		
		// case 3 
		AVLNode<T> max = getMax(p.left);
		p.key=max.key;
		p.data=max.data;
		p.left= deleteAVL(p.left, max.key);
		p.bal++;
		
		// unbalance cases 
		if (p.bal==-2) {// left 
			if (p.left.bal<=0) 
				p=rotateRight(p); //	LL
			else 
				p=rotateLeftRight(p); //	LR
		}//if (p.bal==-2)
		
		else if (p.bal==2) {// right 
			if (p.right.bal>=0) 
				p=rotateLeft(p); //	RR
			else 
				p=rotateRightLeft(p); //	RL
		}//else if (p.bal==2)
		
	
	}//else 
	return p;
}//deleteAVL

private AVLNode<T> getMax(AVLNode<T> p ){
	while (p.right!=null)
		p=p.right;
	
	return p;
		
}//getMax

public void inorder() {
	inOrder(root);
}// inorder
private void inOrder(AVLNode <T> p){
	if(p==null)
		return;
	inOrder(p.left);
	System.out.println(p.data);
	inOrder(p.right);
}//inOrder

}//class 
