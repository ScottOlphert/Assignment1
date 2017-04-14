package pizzaQueue;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) 
	{    
       
		OrderForm frm = new OrderForm();     
		frm.setSize(1800, 750);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

}
