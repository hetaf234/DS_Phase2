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
    
    
    static void fillProductsByReviewCount(AVLNode<Product>p,AVLMap<Integer,Product>map) {
    	if(p==null) return;
    	fillProductsByReviewCount(p.left,map);
    	int k= p.data.getReviewCount()*1000+p.data.getProductId();
    	map.insert(k, p.data);
    	fillProductsByReviewCount(p.right,map);

    }//fillProductsByReviewCount
    
    public static void printTop3MostReviewed(AVLTree<Product> tree) {
      if(tree.empty()) {
    	  System.out.println("No products found");
    	  return;
      }//if
      AVLMap<Integer,Product> map=new AVLMap<>();
      fillProductsByReviewCount(tree.root,map);
      
      System.out.println("Top 3 Most Reviewed Products");
      int [] c= {0};
      printTop3FromRight(map.getRoot(),c);
      
      
        }//end printTop3MostReviewed()
   static void printTop3FromRight(AVLM_Node<Integer,Product> n,int []c){
    	if(n==null || c[0]>=3)return;
    	printTop3FromRight(n.right,c);
    	
    	if(c[0]<3) {
    		Product p=n.data;
    		System.out.println((c[0]+1)+")"+p);
    		c[0]++;
    				
    	}//if
    	printTop3FromRight(n.left,c);
    }//printTop3FromRight
   
    
   
   static void fillProductsByRating(AVLNode<Product>p,AVLMap<Integer,Product>map) {
   	if(p==null) return;
   	fillProductsByRating(p.left,map);
   	int r=(int)(p.data.getAverageRating()*1000)+p.data.getProductId();
   	map.insert(r, p.data);
   	fillProductsByRating(p.right,map);

   }//fillProductsByRating
    
    
    public static void printTopNByAverageRating(AVLTree<Product> tree) {
   
    	if(tree.empty()) {
      	  System.out.println("No products found");
      	  return;
        }//if
        AVLMap<Integer,Product> map=new AVLMap<>();
        fillProductsByRating(tree.root,map);
        
        System.out.println("Top 3 Average Rated  Products");
        int [] c= {0};
        printTop3FromRight(map.getRoot(),c);
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
    	printCommonReviewedAboveRec(p.root, c1,  c2);
    }//printCommonReviewedAbove
        
    private static void printCommonReviewedAboveRec(AVLNode<Product> p, int c1, int c2) {
    	
    	if (p==null) return;
    	//left
    	printCommonReviewedAboveRec(p.left,c1,c2);
    	
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
    			if (!(aReviewed && bReviewed)) {
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
    		printCommonReviewedAboveRec(p.right,c1,c2);
    	 
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
		return "productId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock+"Average rating"+ getAverageRating()
				+ ", reviews="+ reviews.toString() ;
	}
   
    
   
    
} // end Product class
