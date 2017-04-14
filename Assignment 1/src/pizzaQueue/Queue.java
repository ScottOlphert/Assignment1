package pizzaQueue;


public class Queue {
	ListNode front=null;
	ListNode rear=null;
	
	Queue()
	{
		front=null;
		rear=null;
	}
	
	public void join (PizzaOrder  newOrder)
	{
		ListNode current = new ListNode(newOrder);
		if(front==null)
		{
			rear=current;
			front=current;
		}
		else
		{
			rear.setLink(current);
			rear = current;
		}
	}
	
	public void leave ()
	{
		ListNode current=front;
		front = front.getLink();
		current=null;
	}
	public PizzaOrder nextPizza()
	{
		return front.getOrder();
	}
	
	
	public String display()
	{
		ListNode current = front;
		String Display = "";
		while(current != null)
		{
			Display += current.getOrder().toString();
			current=current.getLink();
		}
		return Display;
	}
	
	public String displayFront()
	{
		ListNode current = front;
		String Display = "";
		
			Display += current.getOrder().toString();
		
		return Display;
	}
	
	public boolean empty()
	{
		if(front==null)
			return true;
		else
			return false;
	}
	
	
/*public PizzaOrder search (PizzaOrder order)
	{
		int pos=0;
		ListNode current = front;
		while(current != null && current.getOrder() != order)
		{
			current=current.getLink();
			pos++;
		}
		if(current != null)
			return pos;
		else
			return -1;
	}*/
}
