package Data_Structure;

public class AVLM_Node<K extends Comparable<K>,T> {
K key;
T data;
AVLM_Node<K,T> left,right;
public AVLM_Node(K key, T data) {
	
	this.key = key;
	this.data = data;
	left=right=null;
}




}//class 
