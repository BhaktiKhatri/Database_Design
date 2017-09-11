import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class ContactManager extends JFrame implements ItemListener
{
	JLabel lbl1,lbl2;
	Checkbox rview,radd,redit,rdelete;
	CheckboxGroup chg;
	ContactManagerView cmv;
	
	ContactManager()
	{
		super();
		
		lbl1 = new JLabel("Select the action you want to perform:  ");
		this.add(lbl1);
		
		chg = new CheckboxGroup();
		rview = new Checkbox("View",chg,false);
		radd = new Checkbox("Add",chg,false);
		redit = new Checkbox("Edit",chg,false);
		rdelete = new Checkbox("Delete",chg,false);
		
		rview.addItemListener(this);
		radd.addItemListener(this);
		redit.addItemListener(this);
		rdelete.addItemListener(this);
		
		this.add(rview);
		this.add(radd);
		this.add(redit);
		this.add(rdelete);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100,100,400,250);
		this.setLayout(new FlowLayout());
		this.setTitle("Contact Manager");
		this.setVisible(true);
		this.setSize(500, 300);
		
	}

	public static void main(String[] args)
	{
		ContactManager cm = new ContactManager();
	}

	
	public void itemStateChanged(ItemEvent e) 
	{
		Checkbox cb = (Checkbox)e.getSource();
		System.out.println("in cb: "+cb);
		
		if(cb==rview)
		{
			lbl2 = new JLabel("View option is selected");
			this.add(lbl2);
			//this.setBounds(100,100,400,250);
			this.setVisible(true);
			System.out.println("in view");
			ContactManagerView cmv = new ContactManagerView();
			//cmv.ContactManagerView();
		}
		else if(cb==radd)
		{
			lbl2 = new JLabel("Add option is selected");
			this.add(lbl2);
			//this.setBounds(100,100,400,250);
			this.setVisible(true);
			ContactManagerAdd cma = new ContactManagerAdd();
			//this.setLayout(new FlowLayout());
			System.out.println("in add");
		}
		else if(cb==redit)
		{
			lbl2 = new JLabel("Edit option is selected");
			this.add(lbl2);
			//this.setBounds(100,100,400,250);
			this.setVisible(true);
			ContactManagerUpdate cmu = new ContactManagerUpdate();
			System.out.println("in edit");
		}
		else if(cb==rdelete)
		{
			lbl2 = new JLabel("Delete option is selected");
			this.add(lbl2);
			//this.setBounds(100,100,400,250);
			this.setVisible(true);
			ContactManagerDelete cma = new ContactManagerDelete();
			System.out.println("in delete");
		}
	}
	
	
	
	
}
