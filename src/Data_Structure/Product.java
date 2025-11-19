package Data_Structure;

public class Product {


private int productId;
private String name;
private double price;
private int stock;
private LinkedList<Review> reviews = new LinkedList<>(); // the list of product reviews

public Product(int productid, String name, double price, int stock) {
this.productId=productid;
this.name=name;
this.price=price;
this.stock=stock;
} //end constructor

    public int getProductId() {
    	return productId; 
    }
    
    public String getName() {
    	return name;
    }
    
   public double getPrice() {
	   return price;
   }
   
   public int getStock() {
	   return stock;
   }
   
   public LinkedList<Review> getReviews() { 
	   return reviews;
   }
   
   public void setPrice (double newPrice) {
	   this.price=newPrice;
   } 

    public void setStock(int newStock) { 
    	this.stock=newStock;
   }
    
    
    
    
    
    public void addReview (Review r) {
 	   if (r!=null && r.getProductId()==this.productId)
 		   reviews.insert(r);
    } // end addReview

   

    public boolean editReviewById(int reviewId, Integer newRating, String newComment) {
    	if (reviews.empty())
    		return false;
    	reviews.findFirst();
    	
    	while (!reviews.last()) {
    		Review r=reviews.retrieve();
    		if (r.getReviewId() == reviewId) {
    			if (newRating!=null)
    				r.setRating(newRating);
    			if (newComment != null && newComment.length() > 0)
    				r.setComment(newComment);
    			return true;
    		}
    		reviews.findNext();
    	}//end while
    	
    	Review r=reviews.retrieve();
		if (r.getReviewId() == reviewId) {
			if (newRating!=null)
				r.setRating(newRating);
			if (newComment != null && newComment.length() > 0)
				r.setComment(newComment);
			return true; }
    	return false;
    }
   
    
    public double getAverageRating() {
    	if (reviews.empty()) 
    		return 0;
    	
    	double sum=0;
    	int count=0;
    	
    	reviews.findFirst();
    	
    	while(!reviews.last()) {
    		Review r=reviews.retrieve();
    		sum+=r.getRating();
    		count++;
    		reviews.findNext();
    	}
    	
		Review r=reviews.retrieve();
		sum+=r.getRating();
		count++;
		
		return count == 0 ? 0 : sum/count;
    } //end getAverageRating()
    

    
    public boolean isOutOfStock() {
    	return stock <=0;
    }//end isOutOfStock()

    
    public void printDetails() {
    	System.out.println("Product: " + name + ", price=" + price + ", stock=" + stock);
    	System.out.println("Average rating: " + getAverageRating());
    	
    	if (reviews.empty()) {
    		System.out.println("No reviews for this product.");
    		return;
    	}//end if
    	reviews.findFirst();
    	while (!reviews.last()) {
    		Review rev=reviews.retrieve();
    		System.out.println("Review#" + rev.getReviewId() + " by C" + rev.getCustomerId() + " rating=" + rev.getRating() + " comment=" + rev.getComment());
    		reviews.findNext();
    	}
    	Review rev=reviews.retrieve();
		System.out.println("Review#" + rev.getReviewId() + " by C" + rev.getCustomerId() + " rating=" + rev.getRating() + " comment=" + rev.getComment());	
    }//end printDetails()
    
    
    
    public void updateProduct(double newPrice, int newStock) {
    	if (newPrice >= 0)
    		this.price=newPrice;
    	if (newStock >= 0)
    		this.stock=newStock;
    }//end updateProduct()
 
    
    
    public static Product searchById(AVLTree<Product> tree, int id)  {
    if(tree.findkey(id))
    	return tree.retrieve() ; 
   
    	return null;
    }//end searchById()

    
    
    public static Product searchByName(AVLTree<Product> tree, String name) {
    	return searchByNameRec(tree.root, name);
    }//end searchByName
    private static Product searchByNameRec(AVLNode<Product> p,String name) {
    	if(p==null) return null; 
    	
    	Product l=searchByNameRec(p.left,name);
    	if(l!=null) return l;
    	
    	if(p.data.getName().equalsIgnoreCase(name))
    		return p.data;
    	
    	return searchByNameRec(p.right , name);
    }//searchByNameRec
    
    
    
    
    
    
    
    
    /*
    public static void printTopNByAverageRating(AVLTree<Product> tree, int n) {
        Product a = null, b = null, c = null;
        double ra = -1, rb = -1, rc = -1;

        list.findFirst();
        while (!list.last()) {
        	Product p=list.retrieve();
        	double r=p.getAverageRating();
        	
        	if (r>ra) {
        		c=b; rc=rb;
        		b=a; rb=ra;
        		a=p; ra=r;
        	}//end if
        	
        	else if (r>rb) {
        		c=b; rc=rb;
        		b=p; rb=r;
        	}// end else if
        	
        	else if (r>rc) {
        		c=p; rc=r;
        	}// end else if
        	
        	list.findNext();
        }
        
    	Product p=list.retrieve();
    	double r=p.getAverageRating();
    	
    	if (r>ra) {
    		c=b; rc=rb;
    		b=a; rb=ra;
    		a=p; ra=r;
    	}//end if
    	
    	else if (r>rb) {
    		c=b; rc=rb;
    		b=p; rb=r;
    	}// end else if
    	
    	else if (r>rc) {
    		c=p; rc=r;
    	}// end else if
    	
    	System.out.println("Top " + n + " Products:");
    	int count=0;
    	if (a!=null && count<n ) {
    		System.out.println("1) " + a.getName() + " (" + ra + ")");
    		count++;
    	}
    	
        if (b!=null && count<n ) {
        		System.out.println("2) " + b.getName() + " (" + rb + ")");
        		count++;
        }
        
        if (c!=null && count<n ) {
            	System.out.println("3) " + c.getName() + " (" + rc + ")");
            	count++; 
            	}
        
    }//end printTopNByAverageRating()
	
    */
    
    
    
    public static void printProductsInPriceRange(AVLTree <Product> tree, double min , double max) {
    	printProductsInPriceRangeRec(tree.root,  min ,  max);
    }//printProductsInPriceRange
    private static void printProductsInPriceRangeRec(AVLNode<Product> node, double min , double max) {
    	if(node==null)return;
         printProductsInPriceRangeRec(node.left,min,max); 

         Product p=node.data;
         if(p.getPrice()>=min && p.getPrice()<=max) 
        	 	System.out.println(p);
         
         
         printProductsInPriceRangeRec(node.right,min,max); 

    }//printProductsInPriceRangeRec
    
    
    
        /*
    public static void printCommonReviewedAbove(LinkedList<Product> list, int c1, int c2, double minAvg) {
    	boolean found=false;
    	if (list.empty()) {
    		System.out.println("No products available.");
    	return;
    	}//end if
    	
    	list.findFirst();
    	while(!list.last()) {
    		Product p=list.retrieve();
    		boolean aReviewed=false, bReviewed=false;
    		LinkedList<Review> rList=p.getReviews();
    		
    		if (!rList.empty()) {
    			rList.findFirst();
    			
    			while (!rList.last()) {
    			Review r=rList.retrieve();
    			int cid=r.getCustomerId();
    			
    			if (cid==c1)
    				aReviewed=true;
    			if (cid==c2)
    				bReviewed=true;  
    			if (aReviewed && bReviewed)
    				break;
    			
    			rList.findNext();
    			}//end while
    			
    			if (!aReviewed || !bReviewed) {
    				Review r=rList.retrieve();
        			int cid=r.getCustomerId();
        			if (cid==c1)
        				aReviewed=true;
        			if (cid==c2)
        				bReviewed=true;  
    			}
    		    
    		}//end if
    		
    		if (aReviewed && bReviewed && p.getAverageRating() > minAvg) {
    			System.out.println(p.getName() + " (avg=" + p.getAverageRating() + ")");
    			found=true;
    		}//end if
    		list.findNext();
    	}//end while !list.last()
    	 //last product
    	Product p=list.retrieve();
		boolean aReviewed=false, bReviewed=false;
		LinkedList<Review> rList=p.getReviews();
		
		if (!rList.empty()) {
			rList.findFirst();
			
			while (!rList.last()) {
			Review r=rList.retrieve();
			int cid=r.getCustomerId();
			
			if (cid==c1)
				aReviewed=true;
			if (cid==c2)
				bReviewed=true;  
			if (aReviewed && bReviewed)
				break;
			
			rList.findNext();
			}//end while
			
			if (!aReviewed || !bReviewed) {
				Review r=rList.retrieve();
    			int cid=r.getCustomerId();
    			if (cid==c1)
    				aReviewed=true;
    			if (cid==c2)
    				bReviewed=true;  
			}
		    
		}//end if

		if (aReviewed && bReviewed && p.getAverageRating() > minAvg) {
			System.out.println(p.getName() + " (avg=" + p.getAverageRating() + ")");
			found=true;
		}//end if
		
    		if (!found)
    			System.out.println("No common reviewed products above " + minAvg);
    	}
    
    */
    
    public static boolean removeById(AVLTree<Product> tree, int pid) {
    	Product target = searchById(tree, pid);
    	if (target == null)
    		return false;
    	LinkedList<Review> revs=target.getReviews();
    	while (!revs.empty()) {
    		revs.findFirst();
    		revs.remove();
    	}//end while
    	return tree.removeKey(pid);
    }//removeById
   
    
    @Override
	public String toString() {
		return "Product " + productId + ", name=" + name + ", price=" + price + ", stock=" + stock;
	}
    
} // end Product class
