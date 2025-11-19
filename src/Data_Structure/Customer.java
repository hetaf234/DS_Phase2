
package Data_Structure;

public class Customer{
private int customerId;
private String name;
private String email;
private AVLTree <Order> myOrders=new AVLTree<>();
private LinkedList<Review> myReviews=new LinkedList<>();
  
    
    
    public Customer(int customerId, String name, String email) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
	}//end of constructor

    
    
    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public AVLTree<Order> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(AVLTree<Order> myOrders) {
		this.myOrders = myOrders;
	}

	public LinkedList<Review> getMyReviews() {
		return myReviews;
	}

	public void setMyReviews(LinkedList<Review> myReviews) {
		this.myReviews = myReviews;
	}

	public void placeOrder(Order order) {
		if(order!=null)
			myOrders.insert(order.getOrderId(), order);
	}//placeOrder
	
	
	public void addReview(Review review) {
		if(review!=null)
			myReviews.insert(review);
	}//addReview
	
	
	public void viewOrderHistory() {
		if(myOrders.empty()) {
			System.out.println("No orders found for customer: "+name);
		return;
		}//end if
		System.out.println("Order history for :"+ name);
		myOrders.inorder();
	}//viewOrderHistory


public void printMyReviews() {
	if(myReviews.empty()) {
		System.out.println("No reviews found for customer: "+name);
	return;
	}//end if
	System.out.println("Reviews by :"+ name);
	myReviews.findFirst();
	while (!myReviews.last()) {
		Review r=myReviews.retrieve();
		System.out.println(r);
		myReviews.findNext();
	}//end while
	//print the last Review 
	Review r=myReviews.retrieve();
	System.out.println(r);
}//printMyReviews

		 public static Customer searchById(AVLTree<Customer> tree, int id)  {
			    if(tree.findkey(id))
			    	return tree.retrieve() ; 
			   
			    	return null;
			    }//end searchById()
		
		
		
		
	
    
    
   public static void printReviewsByCustomerId(AVLTree<Customer>tree, int cid) {
    	Customer c= searchById(tree,cid);
    	if (c==null) {
    		System.out.println("Customer Not Found");
    		return;
    	}// end if 
    		c.printMyReviews();
    }//printReviewsByCustomerId



	@Override
	public String toString() {
		return "Customer customerId=" + customerId + "\n name=" + name + "\n email=" + email ;
	}

    
    

   

    

   

    
   
} // end Customer class
