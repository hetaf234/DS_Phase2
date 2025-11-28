package Data_Structure;

public class AVLMap<K extends Comparable<K>,T> {
private AVLM_Node <K,T> root;
private AVLM_Node <K,T> current;

public AVLMap() {
	current = root= null;
}

public AVLM_Node<K, T> getRoot() {
	return root;
}

public void setRoot(AVLM_Node<K, T> root) {
	this.root = root;
}

public AVLM_Node<K, T> getCurrent() {
	return current;
}

public void setCurrent(AVLM_Node<K, T> current) {
	this.current = current;
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
public boolean findkey(K tkey) {
	AVLM_Node<K,T> p=root ;
	if (empty())
		return false;
	while(p!=null) {
		if (tkey.compareTo(p.key)==0) {
			current=p;
			return true;
		}//if 
		else if(tkey.compareTo(p.key)<0)
			p=p.left;
		else 
			p=p.right;
	
					
	}//while p!=null 
	// not found 
	
	return false;
}// findkey


public boolean insert(K k, T val) {
	
	if (findkey(k)) 
		return false; // key found , can not add it 
	
	root = insertRec(root, k , val);
	return true;}


private AVLM_Node<K,T> insertRec(AVLM_Node<K,T> node, K key, T val){

	if(node==null)
		return new AVLM_Node<>(key,val);
	
	int n=key.compareTo(node.key);
	
	if(n<0)
		node.left=insertRec(node.left,key,val);
	else 
		node.right=insertRec(node.right,key,val);

	
	return rebalance(node);
	
}//insertRec
private int height(AVLM_Node<K,T> node) {
	if (node == null) return 0;
	return 1+ Math.max(height(node.left),height(node.right));
}//height

private int balanceFactor(AVLM_Node<K,T> node) {
	if (node == null) return 0;
	return height(node.right)-height(node.left);
}//balanceFactor

//LL case 
private AVLM_Node<K,T> rotateRight(AVLM_Node<K,T> n){
	AVLM_Node<K,T> x=n.left;
	AVLM_Node<K,T> y=x.right;
	
	x.right=n;
	n.left=y;
	
	return x;
}//rotateRight

//RR case
private AVLM_Node<K,T> rotateLeft(AVLM_Node<K,T> n){
	AVLM_Node<K,T> x=n.right;
	AVLM_Node<K,T> y=x.left;
	
	x.left=n;
	n.right=y;
	
	return x;
}//rotateLeft
//LR case
private AVLM_Node<K,T> rotateLeftRight(AVLM_Node<K,T> n){
	n.left=rotateLeft(n.left);
	return rotateRight(n);
}//rotateLeftRight
//RL case
private AVLM_Node<K,T> rotateRightLeft(AVLM_Node<K,T> n){
	n.right=rotateRight(n.right);
	return rotateLeft(n);
}//rotateRightLeft










private AVLM_Node<K,T> rebalance(AVLM_Node<K,T> node){
	int bf = balanceFactor(node);
	
	//Left rotate
	if (bf>1) {
		//RR
		if(balanceFactor(node.right)>=0)
			return rotateLeft(node);
		
		//RL
		else
			return rotateRightLeft(node);
	
	}// if bf>1
	
	
	//right rotate
	if (bf<-1) {
		//LL
		if(balanceFactor(node.left)<=0)
			return rotateRight(node);
		
		//LR
		else
			return rotateLeftRight(node);
	
	}// if bf<-1
	
	return node;
	
	
}//rebalance









	
}//class 
