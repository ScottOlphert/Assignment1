package pizzaQueue;

import java.util.Date;

public class PizzaOrder 
{
	private int orderNo;
	private String pSize, pBase, pTop, dateTime;
	private Double price;
	//private static int count = 10000;
	
	public PizzaOrder() {
		//count++;
		this.orderNo = orderNo;
		this.pSize = pSize;
		this.pBase = pBase;
		this.pTop = pTop;
		this.dateTime = dateTime;
		this.price = price;
	}

	public int getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public String getpSize() {
		return pSize;
	}

	public void setpSize(String pSize) {
		this.pSize = pSize;
	}

	public String getpBase() {
		return pBase;
	}

	public void setpBase(String pBase) {
		this.pBase = pBase;
	}

	public String getpTop() {
		return pTop;
	}

	public void setpTop(String pTop) {
		this.pTop = pTop;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString()
	{
		String str="";
		str = String.format("#%-10s  |%-15s  |%-11s\t|%-15s\t|%-100s |£%-10.2f\n", 
				this.orderNo, this.dateTime, this.pSize, this.pBase, this.pTop, this.price);
		
		return str;
	}
	/*this.orderNo = count;
	this.pSize = pSize;
	this.pBase = pBase;
	this.pTop = pTop;
	this.dateTime = dateTime;
	this.price = price;*/

}
