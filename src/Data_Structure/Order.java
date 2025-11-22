
package Data_Structure;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Order{
    
    private int orderId;
    private Customer customer;
    private AVLTree<Product>products=new AVLTree<>();
    private Date orderDate;
    private double totalPrice;
    private String status;
    
    public Order(int orderId, Customer customer, Date orderDate) {
		
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		status="Pending"; //default value when creating an order
		totalPrice=0;
	}//constructor

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AVLTree<Product> getProducts() {
		return products;
	}

	public void setProducts(AVLTree<Product> products) {
		this.products = products;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public void addProduct(Product p) {
		if(p==null) return;
		products.insert(p.getProductId(),p);
		totalPrice+=p.getPrice();
	}//addProduct
	
    public void cancel() {
    	if(status.equals("Canceled")) 
    		System.out.println("This orderd "+orderId+"  is already cancelled.");
    	else {
    		status="Canceled";
    		System.out.println("This order "+orderId+" has been canceled successfully.");
    	}//else
    		
    }//cancel
    
    public void updateStatus(String newStatus) {
    	if(newStatus==null) return;
    	status=newStatus;
    	System.out.println("this order "+orderId+" status has been updated to:"+status);
    }//updateStatus
    
    
    
    public static Order searchById(AVLTree<Order> tree, int id)  {
        if(tree.findkey(id))
        	return tree.retrieve() ; 
       
        	return null;
        }//end searchById()
    
    
    
 
  public static boolean addProductToOrderById(AVLTree<Order> orders, AVLTree<Product>products,int orderId,int productId) {
	  Order o=searchById(orders,orderId);
	  if (o==null) return false;
	  
	  Product p=Product.searchById(products,productId);
	  if(p==null) return false;
	  
	  o.addProduct(p);
	  return true;
  }//addProductToOrderById
  
  public static void printBetweenDates(AVLTree<Order> o, Date start , Date end) {
	  printBetweenDatesRec(o.root,start, end);
  }//printBetweenDates
  
    
   private static void printBetweenDatesRec(AVLNode<Order>p, Date start , Date end) { 
	   if (p==null) return ;
	   printBetweenDatesRec(p.left, start , end);
	   Order o = p.data ;
	   Date d= o.getOrderDate();
	   
	   if(!d.before(start)&& !d.after(end)) 
		   System.out.println(o);
		   
	   printBetweenDatesRec(p.right,start,end);
	   
	  
	   
	   
	   
   }//printBetweenDatesRec
   public String DisplayProduct(AVLTree<Product>p) {
      	if(p.empty()) 
      		return "no products";
      	String s="" ;
      	return StringBuilder(p.root,s);
      }
    
      
      
      private String StringBuilder(AVLNode<Product> p,String s) {
   	   if(p==null)return s;
   	   s=StringBuilder(p.left,s);
   	   s+=p.data.toString()+"\n";
   	   s=StringBuilder(p.right,s);
   	  return s; 
      }//StringBuilder   
   
   
   
   
   
   
  @Override
  public String toString() {
	  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	  
	return "orderId=" + orderId + "\n customer=" + customer + "\n products=" + DisplayProduct(products) + "\n orderDate="
			+ df.format(orderDate) + "\n totalPrice=" + totalPrice + "\n status=" + status ;
  }//tostring

   

  
    
   

    
} // end Order class
