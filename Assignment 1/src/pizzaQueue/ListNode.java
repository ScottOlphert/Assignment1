package pizzaQueue;

public class ListNode {
	PizzaOrder Order; // could be any variable type or object
	ListNode link;
	
	//constructors
	ListNode(PizzaOrder Order)
	{
		this.Order=Order;
		link=null;
	}
	//used to return the no. stored in the node
	public PizzaOrder getOrder() {
		return Order;
	}
	//used to set the no. stored in the node
	public void setOrder(PizzaOrder newOrder) {
		this.Order = newOrder;
	}
	//used to return the reference to the next node
	public ListNode getLink() {
		return link;
	}
	//used to set the reference to the next node
	public void setLink(ListNode link) {
		this.link = link;
	}
	
	
}

