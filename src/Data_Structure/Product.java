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
   
   public Review[] getReviewsArray() {
	  int size= getReviewCount();
	  Review[] rArray=new Review[size];
	  
	  if(size==0)return rArray;
	  
	  int i =0 ;
	  reviews.findFirst();
	  while (!reviews.last()) {
		  rArray[i++]=reviews.retrieve();
		  reviews.findNext();
	  }//while
	  rArray[i]=reviews.retrieve();// last item
	  
	  return rArray;
   }//getReviewsArray
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
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
   public int getReviewCount() {
	   if(reviews.empty()) return 0;
	   
	   int count=0;
	   reviews.findFirst();
	   while(!reviews.last()) {
		   count++;
		   reviews.findNext();
	   }//while
	   //last item 
	   count++;
	   return count;
		   
   }//getReviewCount
    
    
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
    
    
    static void fillProductsInOrder(AVLNode<Product>p,Product []pArray,int[]idx) {
    	if(p==null) return;
    	fillProductsInOrder(p.left,pArray,idx);
    	pArray[idx[0]++]=p.data;
    	fillProductsInOrder(p.right,pArray,idx);

    }//fillProductsInOrder
    
    public static void printTop3MostReviewed(AVLTree<Product> tree) {
        int size=Main.countAVL(tree);
        if(size==0) {System.out.println("no product found" ); return;}
        Product[] pArray= new Product[size];
        int [] idx= {0};
        fillProductsInOrder(tree.root, pArray,idx);
        
        
        
            Product a = null, b = null, c = null;
            double ra = -1, rb = -1, rc = -1;

           for(int i=0;i<size;i++) {
        	   Product p=pArray[i];
            	int r=p.getReviewCount();
            	
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
            	
            	
            }//for
            
        	System.out.println("Top 3 Most Reviewed Products:");
        	
        	if (a!=null )
        		System.out.println("1) " + a.getName() + " (" + ra + ")");
        		
        	
        	
            if (b!=null)
            		System.out.println("2) " + b.getName() + " (" + rb + ")");
            		
            
            if (c!=null) 
                	System.out.println("3) " + c.getName() + " (" + rc + ")");
                	
                	
            
        }//end printTopNByAverageRating()
    	
        
    
    
    
    
    public static void printTopNByAverageRating(AVLTree<Product> tree) {
    int size=Main.countAVL(tree);
    if(size==0) {System.out.println("no product found" ); return;}
    Product[] pArray= new Product[size];
    int [] idx= {0};
    fillProductsInOrder(tree.root, pArray,idx);
    
    
    
        Product a = null, b = null, c = null;
        double ra = -1, rb = -1, rc = -1;

       for(int i=0;i<size;i++) {
    	   Product p=pArray[i];
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
        	
        	
        }//for
        
    	System.out.println("Top 3 Avg Products:");
    	
    	if (a!=null )
    		System.out.println("1) " + a.getName() + " (" + ra + ")");
    		
    	
    	
        if (b!=null)
        		System.out.println("2) " + b.getName() + " (" + rb + ")");
        		
        
        if (c!=null) 
            	System.out.println("3) " + c.getName() + " (" + rc + ")");
            	
            	
        
    }//end printTopNByAverageRating()
	
    
    
    
    
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
    public static void printCommonReviewedAbove(AVLTree<Product> p, int c1, int c2) {
    	printCommonReviewedAbove(p.root, c1,  c2);
    }//printCommonReviewedAbove
        
    private static void printCommonReviewedAbove(AVLNode<Product> p, int c1, int c2) {
    	
    	if (p==null) return;
    	//left
    	printCommonReviewedAbove(p.left,c1,c2);
    	
    	LinkedList<Review> rList=p.data.getReviews();
    	
    	
    		boolean aReviewed=false, bReviewed=false;
    		
    		
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
    			//last review
    			if (!aReviewed || !bReviewed) {
    				Review r=rList.retrieve();
        			int cid=r.getCustomerId();
        			if (cid==c1)
        				aReviewed=true;
        			if (cid==c2)
        				bReviewed=true;  
    			}
    		    
    		}//end if
    		
    		if (aReviewed && bReviewed && p.data.getAverageRating() > 4) {
    			System.out.println(p.data.getName() + " (avg=" + p.data.getAverageRating() + ")");
    			
    		}//end if
    		//right
    		printCommonReviewedAbove(p.right,c1,c2);
    	 
    	}
    
    
    
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
		return "productId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ ", reviews="+ reviews.toString() ;
	}
   
    
   
    
} // end Product class
