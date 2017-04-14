package pizzaQueue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;


import javax.swing.*;





public class OrderForm extends JFrame implements ActionListener
{
	// Declare components and Variables
	private JComboBox cmbSize;
	private JLabel lblheading, lblPreview, lblSize, lblSize2, lblBase, lblTopping, lblPrice, lblnext, lblStats, fill1, fill2, pic, 
	buffer, buffer1, buffer2, buffer3, buffer4, buffer5;
	private JButton btnAdd, btnReset, btnExit, btnComp, btnQ, btnStat ;
	private JRadioButton thin, Deep;
	private ButtonGroup Crust;
	private JCheckBox Bacon, Chicken, Pepperoni, Pineapple, Mushrooms, Olives;
	private Container Main; 
	private JPanel Order, pSize, Banner, banLeft, banMid, banRight, btn, UI, Queue, pOpt;
	private Box CrustType, TopBox, box1, box2;
	private int NoTop = 0, orderNo = 10001, MuNo = 0, BaNo = 0, ChNo = 0, PeNo = 0, PiNo = 0, OlNo = 0, DeepNo = 0, ThinNo = 0, sNo= 0, mNo= 0, lNo= 0, xlNo= 0, totalOrders = 0;
	private double Sum = 0, Temp = 0, totalIncome= 0;
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	private JTextArea queue, next, stats;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
	private Queue Orders = new Queue();
	private boolean visable = false;
	private File out;
	private RandomAccessFile rd;
	private ImageIcon icon = new ImageIcon("Resource/PizzaHouse.jpg");
	private JScrollPane jsp, jsp2, jsp3;



	// add to a lable before adding to the object. datetime.Now()format(formatter). to string()

	Font all = new Font("Calibri", Font.BOLD,14); // Create a font to use as default


	// Method used to add component to the container and declare constraints
	private void addComp(Container Con, Component c, int x, int y, int width, int height, int Weightx, int weighty)
	{
		// Create instance of constraints
		GridBagConstraints gc = new GridBagConstraints();
		//set constraints
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,5,5,5);// sets the space between components 
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.weightx = Weightx;
		gc.weighty = weighty;

		// set the font using the default created above
		c.setFont(all); 
		c.setBackground(Color.WHITE);

		//add the component to the container
		Con.add(c, gc);

	} 

	public OrderForm() 
	{
		Main = getContentPane ();
		Main.setLayout(new GridBagLayout());
		Main.setBackground(Color.WHITE);

		Order = new JPanel();
		Order.setLayout(new GridBagLayout());
		//Order.setBackground(Color.WHITE);
		Order.setOpaque(true);
		Order.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		UI = new JPanel(new GridBagLayout());
		//UI.setLayout(new GridBagLayout());

		Queue = new JPanel(new GridBagLayout());
		Queue.setBackground(Color.WHITE);

		btn = new JPanel();
		btn.setLayout(new BoxLayout(btn, BoxLayout.Y_AXIS));
		//btn.setBackground(Color.WHITE);
		btn.setOpaque(true);
		btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		pSize= new JPanel();
		pSize.setLayout(new GridBagLayout());
		//pSize.setBackground(Color.getColor("#FFFFFF"));
		//pSize.setOpaque(true);

		pOpt= new JPanel();
		pOpt.setLayout(new GridBagLayout());

		Banner = new JPanel(new GridLayout(1,0));
		Banner.setBackground(Color.WHITE);

		btnAdd = new JButton ("Add Order");
		btnAdd.addActionListener(this);

		btnComp = new JButton ("Order Complete");
		btnComp.addActionListener(this);

		btnQ = new JButton ("Show Queue");
		btnQ.addActionListener(this);

		btnExit= new JButton ("Exit");
		btnExit.addActionListener(this);

		btnStat = new JButton ("Show Statistics");
		btnStat.addActionListener(this);

		Chicken = new JCheckBox("Chicken");
		Chicken.setBackground(Color.WHITE);
		Chicken.addActionListener(this);

		Pepperoni = new JCheckBox("Pepperoni");
		Pepperoni.setBackground(Color.WHITE);
		Pepperoni.addActionListener(this);

		Pineapple = new JCheckBox("Pineapple");
		Pineapple.setBackground(Color.WHITE);
		Pineapple.addActionListener(this);

		Mushrooms = new JCheckBox("Mushrooms");
		Mushrooms.setBackground(Color.WHITE);
		Mushrooms.addActionListener(this);

		Olives = new JCheckBox("Olives");
		Olives.setBackground(Color.WHITE);
		Olives.addActionListener(this);

		Bacon = new JCheckBox("Bacon");
		Bacon.setBackground(Color.WHITE);
		Bacon.addActionListener(this);

		TopBox = Box.createHorizontalBox();
		box1 = Box.createVerticalBox();
		box2 = Box.createVerticalBox();
		box1.add(Chicken);
		box1.add(Bacon);
		box1.add(Pepperoni);
		box2.add(Pineapple);
		box2.add(Mushrooms);
		box2.add(Olives);
		TopBox.add(box1);
		TopBox.add(box2);
		TopBox.setBorder(BorderFactory.createTitledBorder("Toppings"));

		// Create the labels
		banLeft = new JPanel();
		Color green = Color.decode("#006600");
		banLeft.setBackground(green);

		banMid = new JPanel();
		banMid.setLayout(new GridLayout(3,1));
		banMid.setBackground(Color.WHITE);

		banRight = new JPanel();
		Color red = Color.decode("#CC0000");
		banRight.setBackground(red);

		lblheading = new JLabel("<html><u>--Pizza Orders--</u></html>",JLabel.CENTER);
		lblheading.setFont(new Font("Calibri", Font.BOLD,20));
		fill1 = new JLabel("",JLabel.CENTER);
		fill2 = new JLabel("",JLabel.CENTER);

		buffer = new JLabel("",JLabel.CENTER);
		buffer1 = new JLabel("",JLabel.CENTER);
		buffer2 = new JLabel("",JLabel.CENTER);
		buffer3 = new JLabel("",JLabel.CENTER);
		buffer4 = new JLabel("",JLabel.CENTER);
		buffer5 = new JLabel("",JLabel.CENTER);


		//lblheading.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblPreview = new JLabel("<html><u>--Order Preview--</u></html>",JLabel.CENTER);
		//lblPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblSize = new JLabel("Size ",JLabel.LEFT); 
		//	lblSize.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblSize2 = new JLabel("Size: ",JLabel.LEFT); 

		//lblSize2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblBase = new JLabel("Base: ",JLabel.LEFT);
		//lblBase.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblTopping = new JLabel("Toppings: "+ NoTop,JLabel.LEFT);

		lblPrice = new JLabel("Price: £" +df2.format(Sum),JLabel.LEFT);
		//lblTopping.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		lblnext = new JLabel("<html><u>--Next Pizza--</u></html>",JLabel.CENTER);

		lblStats = new JLabel("<html><u>--Statistcs--</u></html>",JLabel.CENTER);

		//lblheading.setFont(new Font("Calibri", Font.BOLD,15));

		pic = new JLabel(icon);

		// array to hold the combo box options
		String [] Size = {"---Select---","Small", "Medium", "Large", "Extra Large"};

		// create combo box
		cmbSize = new JComboBox(Size); 
		cmbSize.setBackground(Color.WHITE);
		cmbSize.addActionListener(this);

		// create radio buttons
		thin = new JRadioButton("Thin Crust");
		thin.setBackground(Color.WHITE);
		thin.addActionListener(this);

		Deep = new JRadioButton("Deep Pan");
		Deep.setBackground(Color.WHITE);
		Deep.addActionListener(this);

		// create button group
		Crust = new ButtonGroup();
		Crust.add(thin);
		Crust.add(Deep);

		//add radio buttons to box
		CrustType = Box.createVerticalBox();
		CrustType.add(thin);
		CrustType.add(Deep);
		CrustType.setBorder(BorderFactory.createTitledBorder("Crust"));

		// Create Text Fields and Scroll Panes
		queue = new JTextArea();
		queue.setEditable(false);

		jsp = new JScrollPane(queue);
		jsp.setBackground(Color.WHITE);
		jsp.setVisible(false);

		next = new JTextArea();
		next.setEditable(false);
		next.setBackground(Color.WHITE);
		next.setText("");

		jsp2 = new JScrollPane(next);
		jsp2.setBackground(Color.WHITE);

		stats = new JTextArea();
		stats.setEditable(false);
		stats.setText("");

		jsp3 = new JScrollPane(stats);
		jsp3.setBackground(Color.WHITE);


		//Create banner
		banMid.add(fill1);
		banMid.add(lblheading);
		banMid.add(fill2);
		Banner.add(banLeft);
		Banner.add(banMid);
		Banner.add(banRight);



		// Add banner UI and Queue panel to Main container
		addComp(Main,Banner,0,0,8,1,1,0);
		addComp(Main,UI,0,1,4,1,1,0);
		addComp(Main,Queue,4,1,4,1,2,1);

		// Add components to queue panel
		addComp(Queue,pic,0,0,3,1,1,1);
		addComp(Queue,jsp,0,0,3,1,1,1);

		// Add components to UI panel
		addComp(UI,pSize,0,0,3,1,0,0);
		addComp(UI,Order,3,0,1,2,0,0);
		addComp(UI,pOpt,0,1,3,1,0,0);
		addComp(UI,jsp2,0,3,3,3,1,0);
		addComp(UI,lblnext,0,2,3,1,0,0);
		addComp(UI,btnAdd,3,2,1,1,0,0);
		addComp(UI,btnComp,3,3,1,1,0,0);
		addComp(UI,btnQ,3,4,1,1,0,0);
		addComp(UI,btnStat,3,5,1,1,0,0);
		addComp(UI,btnExit,3,6,1,1,0,0);
		addComp(UI,lblStats,0,6,3,1,0,0);
		addComp(UI,jsp3,0,7,3,5,1,1);
		addComp(UI,buffer5,0,11,4,1,1,1);


		addComp(pOpt,buffer2,0,0,1,1,1,0);
		addComp(pOpt,CrustType,1,0,1,1,0,0);
		addComp(pOpt,buffer3,2,0,1,1,1,0);
		addComp(pOpt,TopBox,3,0,2,1,0,0);
		addComp(pOpt,buffer4,5,0,1,1,1,0);

		addComp(Order,lblPreview,0,0,1,1,0,0);
		addComp(Order,lblSize2,0,1,1,1,0,0);
		addComp(Order,lblBase,0,2,1,1,0,0);
		addComp(Order,lblTopping,0,3,1,1,0,0);
		addComp(Order,lblPrice,0,4,1,1,0,0);

		addComp(pSize,buffer,0,0,1,1,1,0);
		addComp(pSize,lblSize,1,0,1,1,0,0);
		addComp(pSize,cmbSize,2,0,2,1,2,0);
		addComp(pSize,buffer1,4,0,1,1,1,0);

		Read();

		if (Orders.empty()== false)
		{
			next();
		}

	}




	public void actionPerformed(ActionEvent e) 
	{		
		// Collect Dropdown Box seletion
		if(e.getSource()== cmbSize)
		{
			Combo();
		}

		// Collect Radio Button Selection

		if(e.getSource()== Deep)
		{
			lblBase.setText ("Base: " + Deep.getText().toString());
		}

		if(e.getSource()== thin)
		{
			lblBase.setText ("Base: " + thin.getText().toString());
		}

		// Collect CheckBox Selection
		if(e.getSource() == Bacon)
		{
			if(Bacon.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		if(e.getSource() == Chicken)
		{
			if(Chicken.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		if(e.getSource() == Pepperoni)
		{
			if(Pepperoni.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		if(e.getSource() == Pineapple)
		{
			if(Pineapple.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		if(e.getSource() == Mushrooms)
		{
			if(Mushrooms.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		if(e.getSource() == Olives)
		{
			if(Olives.isSelected())
			{
				NoTop ++;
				Sum += 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
			else
			{
				NoTop --;
				Sum -= 0.5;
				lblTopping.setText("Toppings: "+ NoTop);
				lblPrice.setText("Price: £" +df2.format(Sum));
			}
		}

		// Add new order
		if(e.getSource()== btnAdd)
		{
			AddOrder();
			Reset();
			if (Orders.empty()== false)
			{
				next();
			}
		}
		//Show & Hide Queue
		if(e.getSource() == btnQ)
		{
			ShowQueue();
		}

		// Complete and remove order at front of queue
		if(e.getSource() == btnComp)
		{
			if (Orders.empty()== true)
			{
				JOptionPane.showMessageDialog(null, "There are no orders in the Queue");
			}
			else
			{
				Orders.leave();
			}

			if (Orders.empty()== false)
			{
				next();
			}
			else
			{
				next.setText("");
				String Header; 
				Header="Order           |Date/Time            |Size                          |Crust                  |Toppings   \t\t\t\t |Price\n";
				next.append(Header);
				Header="-----------------------------------------------------------------------------------------------"
						+ "------------------------------------------------------------------------------------------\n";
				next.append(Header);
			}

			DisplayQueue();
		}

		//Hide & Display Statistics
		if(e.getSource() ==btnStat)
		{

			Stats();
		}

		// Exit application
		if(e.getSource() == btnExit)
		{
			Write();
			System.exit(0);
		}

	}

	public void AddOrder()
	{
		PizzaOrder newOrder = new PizzaOrder();
		String pTop = "", base = null, DateTime;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy, HH:mm");

		if(Deep.isSelected())
		{
			base = "Deep Pan";
			DeepNo++; 			// Collect stats for Deep Pan
		}
		if(thin.isSelected())
		{
			base = "Thin Crust";
			ThinNo++;			// Collect stats for Thin Crust
		}
		if(Mushrooms.isSelected())
		{
			pTop += " Mushrooms*";
			MuNo++;				// Collect stats for Mushrooms
		}
		if(Bacon.isSelected())
		{
			pTop += " Bacon*";
			BaNo++;				// Collect stats for Bacon
		}
		if(Chicken.isSelected())
		{
			pTop += " Chicken*";
			ChNo++;				// Collect stats for Chicken
		}
		if(Pepperoni.isSelected())
		{
			pTop += " Pepperoni*";
			PeNo++;				// Collect stats for Pepperoni
		}
		if(Pineapple.isSelected())
		{
			pTop += " Pineapple*";
			PiNo++;				// Collect stats for Pineapple
		}
		if(Olives.isSelected())
		{
			pTop += " Olives*";
			OlNo++;				// Collect stats for Olives
		}

		// Collect stats for Pizza Size
		if((cmbSize.getSelectedItem().toString()=="Small"))
		{
			sNo++;
		}
		if((cmbSize.getSelectedItem().toString()=="Medium"))
		{
			mNo++;	
		}
		if((cmbSize.getSelectedItem().toString()=="Large"))
		{
			lNo++;
		}
		if((cmbSize.getSelectedItem().toString()=="Extra Large"))
		{
			xlNo++;
		}

		// Validate to make sure there is a selection
		if(cmbSize.getSelectedItem().toString()=="---Select---" || base == null)
		{
			JOptionPane.showMessageDialog(null, "Please be sure to select the Pizza Size and Base");
		}
		else
		{
			newOrder.setOrderNo(orderNo);								//Create new Pizza Order
			newOrder.setpSize(cmbSize.getSelectedItem().toString());
			newOrder.setpBase(base);
			newOrder.setpTop(pTop);
			newOrder.setPrice(Sum);
			newOrder.setDateTime(DateTime = now.format(formatter));	
			Orders.join(newOrder);
			queue.append(newOrder.toString());

			orderNo++; // increment Order number
			totalOrders++; //count of total orders
			totalIncome += Sum;  // Collect total income
		}
	}

	public void Stats()
	{ 
		int temp;
		String Hold;
		boolean change = false;
		String[] Toppings = {"Mushrooms", "Bacon", "Chicken", "Pepperoni", "Pineapple", "Olives"};
		int [] topStat = {MuNo,BaNo,ChNo,PeNo,PiNo,OlNo};

		do
		{
			change = false;
			for(int x = 0; x<5; x++)
			{
				if(topStat[x]>topStat[x+1])
				{
					change = true;
					temp = topStat[x];
					Hold = Toppings [x];
					topStat[x] = topStat[x+1];
					Toppings[x] = Toppings[x+1];
					topStat[x+1] = temp;
					Toppings[x+1] = Hold;
				}
			}



		}
		while (change == true);

		stats.setText("");
		
		stats.append("Total Orders\t|Total Income|\n"
				+    "------------------------------------------\n   "
				+ totalOrders+"\t| £"+df2.format(totalIncome)+"            |\n"
				+    "------------------------------------------\n\n");
		
		stats.append("--Base Type--\n"
				+    "--------------------\n"
				+ "Deep Pan\t|Thin Crust|\n"
				+    "------------------------------------------\n   "
				+ DeepNo+"\t|"+ThinNo+"            |\n"
				+    "------------------------------------------\n\n");

		stats.append("--Popular Toppings--\n"
				+    "--------------------\n"
				+ "  Topping\tTimes Selected\n");

		for(int x = 5; x>=0; x--)
		{
			stats.append(6-x+". "+ Toppings[x]+"\t  "+ topStat[x]+"\n");
		}
	}



	public void Reset()
	{
		cmbSize.setSelectedItem("---Select---");
		Crust.clearSelection();
		Bacon.setSelected(false);
		Chicken.setSelected(false); 
		Pepperoni.setSelected(false); 
		Pineapple.setSelected(false); 
		Mushrooms.setSelected(false); 
		Olives.setSelected(false);
		Sum = 0;
		NoTop = 0;
		lblTopping.setText("Toppings: "+ NoTop);
		lblPrice.setText("Price: £" +df2.format(Sum));
		lblBase.setText ("Base: ");

	} 

	public void Combo()
	{
		if(cmbSize.getSelectedItem().toString()=="---Select---")
		{
			lblSize2.setText("Size:               ");
			Sum -= Temp;
			Temp = 0;
			lblPrice.setText("Price: £" +df2.format(Sum));
		}
		if((cmbSize.getSelectedItem().toString()=="Small"))
		{

			lblSize2.setText("Size: " + cmbSize.getSelectedItem().toString());
			Sum -= Temp;
			Sum += 3;
			Temp = 3;
			lblPrice.setText("Price: £" +df2.format(Sum));

		}
		if((cmbSize.getSelectedItem().toString()=="Medium"))
		{

			lblSize2.setText("Size: " + cmbSize.getSelectedItem().toString());
			Sum -= Temp;
			Sum += 4;
			Temp = 4;
			lblPrice.setText("Price: £" +df2.format(Sum));
		}
		if((cmbSize.getSelectedItem().toString()=="Large"))
		{

			lblSize2.setText("Size: " + cmbSize.getSelectedItem().toString());
			Sum -= Temp;
			Sum += 5;
			Temp = 5;
			lblPrice.setText("Price: £" +df2.format(Sum));
		}
		if((cmbSize.getSelectedItem().toString()=="Extra Large"))
		{

			lblSize2.setText("Size: " + cmbSize.getSelectedItem().toString());
			Sum -= Temp;
			Sum += 6;
			Temp = 6;
			lblPrice.setText("Price: £" +df2.format(Sum));
		}
	}

	public void DisplayQueue()
	{
		String Header;
		queue.setText("");
		// Add a header
		Header="Order          |Date/Time             |Size                         |Crust                   |Toppings   \t\t\t\t |Price\n";
		queue.append(Header);
		Header="--------------------------------------------------------------------------------------------------"
				+ "---------------------------------------------------------------------------------------------\n";
		queue.append(Header);
		queue.append(Orders.display());
	}


	public void ShowQueue()
	{
		String Header;

		if(visable == false)
		{
			visable = true;
			pic.setVisible(false);
			jsp.setVisible(true);
			queue.setText("");
			DisplayQueue();
			btnQ.setText("Hide Queue");
		}
		else
		{
			visable = false;

			jsp.setVisible(false);
			pic.setVisible(true);

			queue.setText("");

			btnQ.setText("Show Queue");
		}


	}

	public void next()
	{
		String Header;

		next.setText("");

		jsp2.setVisible(true);

		// Add a header
		Header="Order           |Date/Time            |Size                          |Crust                  |Toppings   \t\t\t\t |Price\n";
		next.append(Header);
		Header="-----------------------------------------------------------------------------------------------"
				+ "------------------------------------------------------------------------------------------\n";
		next.append(Header);
		next.append(Orders.displayFront());

	}



	public void Read()
	{
		try{
			out = new File ("orders.dat");
			rd = new RandomAccessFile(out, "rw");

			if(rd.length()>0)
			{
				boolean eof = false;
				do
				{
					try
					{
						PizzaOrder newOrder = new PizzaOrder();
						newOrder.setpBase(rd.readUTF());
						newOrder.setPrice(Double.parseDouble(rd.readUTF()));
						newOrder.setpSize(rd.readUTF());
						newOrder.setpTop(rd.readUTF());
						newOrder.setDateTime(rd.readUTF());	
						newOrder.setOrderNo(orderNo);
						orderNo++;

						Orders.join(newOrder);
					}
					catch(EOFException e1)
					{
						eof =true;
					}



				}while (!eof);
				rd.close();
			}
			else
				JOptionPane.showMessageDialog(null, "No Records Loaded");
		}
		catch(IOException ioEx)
		{
			JOptionPane.showMessageDialog(null, "Could not read from file");
		}
	}

	public void Write()
	{
		try{

			out = new File ("orders.dat");
			rd = new RandomAccessFile(out, "rw");

			while(Orders.empty()== false)
			{
				PizzaOrder newOrder = new PizzaOrder();

				newOrder = Orders.nextPizza();
				rd.writeUTF(newOrder.getpBase());
				rd.writeUTF(newOrder.getPrice().toString());
				rd.writeUTF(newOrder.getpSize());
				rd.writeUTF(newOrder.getpTop());
				rd.writeUTF(newOrder.getDateTime());

				Orders.leave();
			}

			rd.close();

		}


		catch(IOException ioEx)
		{
			JOptionPane.showMessageDialog(null, "Could not read from file");
		}
	}


}
