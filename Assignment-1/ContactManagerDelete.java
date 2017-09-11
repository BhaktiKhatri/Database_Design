import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ContactManagerDelete extends JFrame implements ItemListener
{

	static Connection conn = null;
	static ResultSet rs = null,rs1=null,rs2=null,rs3=null,rs4=null;
	static Statement stmt = null,stmt1=null,stmt2=null,stmt3=null,stmt4=null;

	JButton delete;
	JPanel panel;
	
	Choice c;
	static int count=0;
	String msg="";
	int index=0;
	int cnt=0;
	static Vector Vcontactid = new Vector();
	JLabel lbl1;
	int ct=0;
	
		ContactManagerDelete()
		{
			super();
			lbl1 = new JLabel("Select the Contact ID from below to update its data: ");
			this.add(lbl1);
			
			c = new Choice();
			String a = "Select";
			c.addItem(a);
			
			System.out.println("Vcontactid.size()===1: "+Vcontactid.size());
			if(Vcontactid.size()==0)
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
		            conn = DriverManager.getConnection("jdbc:mysql://localhost/contactmanagerdatabase1?user=root&password=KrishnaNisu2425!");
		            stmt = conn.createStatement();
		            stmt1 = conn.createStatement();
		            stmt2 = conn.createStatement();
		            stmt3 = conn.createStatement();
		            stmt4 = conn.createStatement();
		            
		            rs = stmt.executeQuery("SELECT P_Contact_ID FROM contactmanagerdatabase1.contact_person;");
					  while (rs.next())
					  {
						  count=rs.getInt(1);
						  Vcontactid.add(count);
					  }
					  rs.close();
				}
				catch(Exception e)
				{
					System.out.println("Exception: "+e);
				}
			}
			System.out.println("Vcontactid.size()===2: "+Vcontactid.size());
			for(int i=0;i<Vcontactid.size();i++)
			{
				String ii=Vcontactid.elementAt(i).toString();
				c.addItem(ii);
			}
			
			c.addItemListener(this);
			this.add(c);
			
			delete = new JButton("Delete Data");
			delete.addActionListener(new DeleteData());

			this.add(delete,BorderLayout.CENTER);
			this.setBounds(10,10,1000,900);
			this.setLayout(new FlowLayout());
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Contact Manager Delete Details");
			this.setVisible(true);
		}

	public static void main(String[] args) 
	{
		ContactManagerDelete cmu = new ContactManagerDelete();
	}
	
	public void itemStateChanged(ItemEvent e) 
	{
		msg=c.getSelectedItem();
		index=c.getSelectedIndex();
	}
	
	private class DeleteData implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JButton src = (JButton)e.getSource();
			src.setText("Record Deleted!");	
			
			try
			{
				String q="SET FOREIGN_KEY_CHECKS = 0";
				rs=stmt.executeQuery(q);
				
				String query="delete from contactmanagerdatabase1.contact_person where p_contact_id='"+msg+"'";
				System.out.println("query: "+query);
				stmt.executeUpdate(query);
				
				String query1="delete from contactmanagerdatabase1.address_details where ad_contact_person_id='"+msg+"'";
				System.out.println("query1: "+query1);
				stmt.executeUpdate(query1);
				
				String query2="delete from contactmanagerdatabase1.appointment_details where ap_contact_person_id='"+msg+"'";
				System.out.println("query2: "+query2);
				stmt.executeUpdate(query2);
				
				String query3="delete from contactmanagerdatabase1.email_details where e_contact_person_id='"+msg+"'";
				System.out.println("query3: "+query3);
				stmt.executeUpdate(query3);

				String query4="delete from contactmanagerdatabase1.phone_details where p_contact_person_id='"+msg+"'";
				System.out.println("query4: "+query4);
				stmt.executeUpdate(query4);
				
				String query5="delete from contactmanagerdatabase1.contact_manager where M_Contact_Person_ID='"+msg+"'";
				System.out.println("query5: "+query5);
				stmt.executeUpdate(query5);
				
				//conn.commit();
				//conn.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception: "+ex);
			}
			
		}
		
	}


}
