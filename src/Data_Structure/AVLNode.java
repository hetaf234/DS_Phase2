package Data_Structure;

public class AVLNode<T> {
public int key;
public T data;
public int bal;
public AVLNode<T> left,right;
public AVLNode(int key, T data) {
	this.key=key;
	this.data=data;
	bal=0;
	left=right=null;
}


public AVLNode(int key, T data,AVLNode<T>left,AVLNode<T>right) {
	this.key=key;
	this.data=data;
	bal=0;
	this.left=left;
	this.right=right;
}



}

