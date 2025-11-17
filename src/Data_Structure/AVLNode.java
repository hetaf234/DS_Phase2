package Data_Structure;

public class AVLNode<T> {
public int key;
public T data;
public balance bal;
public AVLNode<T> left,right;
public AVLNode(int key, T data) {
	this.key=key;
	this.data=data;
	bal=balance.zero;
	left=right=null;
}
}

