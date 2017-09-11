import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;

public class ContactManagerView extends JFrame implements ItemListener
{
	static Connection conn = null;
	static ResultSet rs = null,rs1=null,rs2=null,rs3=null,rs4=null;
	static Statement stmt = null,stmt1=null,stmt2=null,stmt3=null,stmt4=null;
	JLabel lbl1,lbl2;
	Choice c;
	static int count=0;
	String msg="";
	int index=0;
	int cnt=0;
	String passportno="";
	String contactid="";
	String groupid="";
	String firstname="";
	String middlename="";
	String lastname="";
	String gender="";
	String birthdate="";
	String faxno="";
	String methodofcontact="";
	
	JLabel lblpassport1,lblpassport2;
	JLabel lblcontactid1,lblcontactid2;
	JLabel lblgroupid1,lblgroupid2;
	JLabel lblfirstname1,lblfirstname2;
	JLabel lblmiddlename1,lblmiddlename2;
	JLabel lbllastname1,lbllastname2;
	JLabel lblgender1,lblgender2;
	JLabel lblbirthdate1,lblbirthdate2;
	JLabel lblfaxno1,lblfaxno2;
	JLabel lblmethodofcontact1,lblmethodofcontact2;
	JPanel panel;
	Font font;
	Border b;
	static Vector Vcontactid = new Vector();
	JButton btn;
	int ct=0;
	
	String addresstype="";
	String county="";
	String city="";
	String state="";
	String zipcode="";
	String streetname="";
	String aptno="";
	
	JLabel lbladdresstype1,lbladdresstype2;
	JLabel lblcounty1,lblcounty2;
	JLabel lblcity1,lblcity2;
	JLabel lblstate1,lblstate2;
	JLabel lblzipcode1,lblzipcode2;
	JLabel lblstreetname1,lblstreetname2;
	JLabel lblaptno1,lblaptno2;
	
	String meetingsubject="";
	String meetingtype="";
	String date="";
	String place="";
	String timezone="";
	String birthday="";
	String lastmeetingdate="";
	
	JLabel lblmeetingsubject1,lblmeetingsubject2;
	JLabel lblmeetingtype1,lblmeetingtype2;
	JLabel lbldate1,lbldate2;
	JLabel lblplace1,lblplace2;
	JLabel lbltimezone1,lbltimezone2;
	JLabel lblbirthday1,lblbirthday2;
	JLabel lbllastmeetingdate1,lbllastmeetingdate2;
	
	String emailid="";
	String emailtype="";
	String phoneno="";
	String phonetype="";
	String extention="";
	String status="";
	int ect=0;
	int pct=0;
	
	JLabel lblemailid1,lblemailid2;
	JLabel lblemailtype1,lblemailtype2;
	JLabel lblphoneno1,lblphoneno2;
	JLabel lblphonetype1,lblphonetype2;
	JLabel lblextention1,lblextention2;
	JLabel lblstatus1,lblstatus2;
	
	ContactManagerView()
	{
		super();
		lbl1 = new JLabel("Select the Contact ID from below to view their data: ");
		this.add(lbl1);
		
		c = new Choice();
		String a = "Select";
		c.addItem(a);
		
		System.out.println("Vcontactid.size(): "+Vcontactid.size());
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
		
		for(int i=0;i<Vcontactid.size();i++)
		{
			String ii=Vcontactid.elementAt(i).toString();
			c.addItem(ii);
		}
		
		c.addItemListener(this);
		this.add(c);
		
		//btn = new JButton("Go To Home Window");
		//this.add(btn);
		
		this.setBounds(10,10,1000,800);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Contact Manager View Details");
		this.setVisible(true);
		
	}
	
	
	public static void main(String[] args) 
	{
		try
		{
			  System.out.println("in main");
			  Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection("jdbc:mysql://localhost/contactmanagerdatabase1?user=root&password=KrishnaNisu2425!");
              stmt = conn.createStatement();
              stmt1 = conn.createStatement();
              stmt2 = conn.createStatement();
              stmt3 = conn.createStatement();
              
              rs = stmt.executeQuery("SELECT P_Contact_ID FROM contactmanagerdatabase1.contact_person;");
			  while (rs.next())
			  {
				  count=rs.getInt(1);
				  Vcontactid.add(count);
			  }
			  System.out.println("Vcontactid: "+Vcontactid);
			  ContactManagerView cm = new ContactManagerView();
			  rs.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
	}

//	public void actionPerformed(ActionEvent e) 
//	{
//		//JButton src = (JButton)e.getSource();
//		//src.setText("Pressed");	
//		ContactManager cm = new ContactManager();
//	}
	
	public void itemStateChanged(ItemEvent arg0) 
	{
		
		msg=c.getSelectedItem();
		index=c.getSelectedIndex();
		String d="-";
		
		if(cnt>0)
		{
			this.remove(panel);
		}

		try 
		{
			cnt++;
			
			////////////////CONTACT PERSON DETAILS///////////////
			
			String query="SELECT * FROM contactmanagerdatabase1.contact_person where p_contact_id='"+msg+"'";
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
			  passportno=(rs.getString(1)==null?d:rs.getString(1).toString());
			  contactid=(rs.getString(2)==null?d:rs.getString(2).toString());
			  groupid=(rs.getString(3)==null?d:rs.getString(3).toString());
			  firstname=(rs.getString(4)==null?d:rs.getString(4).toString());
			  middlename=(rs.getString(5)==null?d:rs.getString(5).toString());
			  lastname=(rs.getString(6)==null?d:rs.getString(6).toString());
			  gender=(rs.getString(7)==null?d:rs.getString(7).toString());
			  birthdate=(rs.getString(8)==null?d:rs.getString(8).toString());
			  faxno=(rs.getString(9)==null?d:rs.getString(9).toString());
			  methodofcontact=(rs.getString(10)==null?d:rs.getString(10).toString());
			}
			rs.close();
			
			lblpassport1 = new JLabel("Passport No : ");
			lblpassport2 = new JLabel(" "+passportno);
			this.add(lblpassport1);
			this.add(lblpassport2);
			
			lblcontactid1 = new JLabel("Contact ID : ");
			lblcontactid2 = new JLabel(" "+contactid);
			this.add(lblcontactid1);
			this.add(lblcontactid2);
			
			lblgroupid1 = new JLabel("Group ID : ");
			lblgroupid2 = new JLabel(" "+groupid);
			this.add(lblgroupid1);
			this.add(lblgroupid2);
			
			lblfirstname1 = new JLabel("First Name : ");
			lblfirstname2 = new JLabel(" "+firstname);
			this.add(lblfirstname1);
			this.add(lblfirstname2);
			
			lblmiddlename1 = new JLabel("Middle Name : ");
			lblmiddlename2 = new JLabel(" "+middlename);
			this.add(lblmiddlename1);
			this.add(lblmiddlename2);
			
			lbllastname1 = new JLabel("Last Name : ");
			lbllastname2 = new JLabel(" "+lastname);
			this.add(lbllastname1);
			this.add(lbllastname2);
			
			lblgender1 = new JLabel("Gender : ");
			lblgender2 = new JLabel(" "+gender);
			this.add(lblgender1);
			this.add(lblgender2);
			
			lblbirthdate1 = new JLabel("Birth Date : ");
			lblbirthdate2 = new JLabel(" "+birthdate);
			this.add(lblbirthdate1);
			this.add(lblbirthdate2);
			
			lblfaxno1 = new JLabel("Fax No : ");
			lblfaxno2 = new JLabel(" "+faxno);
			this.add(lblfaxno1);
			this.add(lblfaxno2);
			
			lblmethodofcontact1 = new JLabel("Preferred Method of Contact : ");
			lblmethodofcontact2 = new JLabel(" "+methodofcontact);
			this.add(lblmethodofcontact1);
			this.add(lblmethodofcontact2);
			
			panel = new JPanel();
			panel.setLayout(new GridLayout(30,2));
			this.add(panel);
			panel.setSize(1000,1000);
			//panel.setBackground(Color.lightGray);
			
			font = new Font("Courier New", Font.ITALIC, 15);
			b = BorderFactory.createLineBorder(Color.lightGray, 1,true);
			
			panel.add(lblpassport1);
			panel.add(lblpassport2);
			
			lblpassport1.setBorder(b);
			lblpassport1.setFont(font);
			lblpassport1.setForeground(Color.blue);
			
			lblpassport2.setBorder(b);
			//lblpassport2.setFont(font);
			//lblpassport2.setForeground(Color.blue);
			
			panel.add(lblcontactid1);
			panel.add(lblcontactid2);
			
			lblcontactid1.setBorder(b);
			lblcontactid1.setFont(font);
			lblcontactid1.setForeground(Color.blue);
			
			lblcontactid2.setBorder(b);
			//lblcontactid2.setFont(font);
			//lblcontactid2.setForeground(Color.blue);
			
			panel.add(lblgroupid1);
			panel.add(lblgroupid2);
			
			lblgroupid1.setBorder(b);
			lblgroupid1.setFont(font);
			lblgroupid1.setForeground(Color.blue);
			
			lblgroupid2.setBorder(b);
			//lblgroupid2.setFont(font);
			//lblgroupid2.setForeground(Color.blue);
			
			panel.add(lblfirstname1);
			panel.add(lblfirstname2);
			
			lblfirstname1.setBorder(b);
			lblfirstname1.setFont(font);
			lblfirstname1.setForeground(Color.blue);
			
			lblfirstname2.setBorder(b);
			//lblfirstname2.setFont(font);
			//lblfirstname2.setForeground(Color.blue);
			
			panel.add(lblmiddlename1);
			panel.add(lblmiddlename2);
			
			lblmiddlename1.setBorder(b);
			lblmiddlename1.setFont(font);
			lblmiddlename1.setForeground(Color.blue);
			
			lblmiddlename2.setBorder(b);
			//lblmiddlename2.setFont(font);
			//lblmiddlename2.setForeground(Color.blue);
			
			panel.add(lbllastname1);
			panel.add(lbllastname2);
			
			lbllastname1.setBorder(b);
			lbllastname1.setFont(font);
			lbllastname1.setForeground(Color.blue);
			
			lbllastname2.setBorder(b);
			//lbllastname2.setFont(font);
			//lbllastname2.setForeground(Color.blue);
			
			panel.add(lblgender1);
			panel.add(lblgender2);
			
			lblgender1.setBorder(b);
			lblgender1.setFont(font);
			lblgender1.setForeground(Color.blue);
			
			lblgender2.setBorder(b);
			//lblgender2.setFont(font);
			//lblgender2.setForeground(Color.blue);
			
			panel.add(lblbirthdate1);
			panel.add(lblbirthdate2);
			
			lblbirthdate1.setBorder(b);
			lblbirthdate1.setFont(font);
			lblbirthdate1.setForeground(Color.blue);
			
			lblbirthdate2.setBorder(b);
			//lblbirthdate2.setFont(font);
			//lblbirthdate2.setForeground(Color.blue);
			
			panel.add(lblfaxno1);
			panel.add(lblfaxno2);
			
			lblfaxno1.setBorder(b);
			lblfaxno1.setFont(font);
			lblfaxno1.setForeground(Color.blue);
			
			lblfaxno2.setBorder(b);
			//lblfaxno2.setFont(font);
			//lblfaxno2.setForeground(Color.blue);
			
			panel.add(lblmethodofcontact1);
			panel.add(lblmethodofcontact2);
			
			lblmethodofcontact1.setBorder(b);
			lblmethodofcontact1.setFont(font);
			lblmethodofcontact1.setForeground(Color.blue);
			
			lblmethodofcontact2.setBorder(b);
			//lblmethodofcontact2.setFont(font);
			//lblmethodofcontact2.setForeground(Color.blue);
			
			panel.setBorder(b);
			
			//////////////////////ADDRESS DETAILS/////////////
			String a="";
			String query1="select * from contactmanagerdatabase1.address_details where ad_contact_person_id='"+msg+"'";
			System.out.println("query1: "+query1);
			rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				System.out.println("ct here: "+ct);
				if(msg.equalsIgnoreCase(a))
				{
					if(ct>0)
					{
						addresstype=addresstype+", "+rs1.getString(2);
						county=county+", "+rs1.getString(3);
						city=city+", "+rs1.getString(4);
						state=state+", "+rs1.getString(5);
						zipcode=zipcode+", "+rs1.getString(6);
						streetname=streetname+", "+rs1.getString(7);
						aptno=aptno+", "+rs1.getString(8);
					}
					else
					{
						addresstype=(rs1.getString(2)==null?d:rs1.getString(2).toString());
						county=(rs1.getString(3)==null?d:rs1.getString(3).toString());
						city=(rs1.getString(4)==null?d:rs1.getString(4).toString());
						state=(rs1.getString(5)==null?d:rs1.getString(5).toString());
						zipcode=(rs1.getString(6)==null?d:rs1.getString(6).toString());
						streetname=(rs1.getString(7)==null?d:rs1.getString(7).toString());
						aptno=(rs1.getString(8)==null?d:rs1.getString(8).toString());
						
					}
				}
				else
				{
					addresstype="";
					county="";
					city="";
					state="";
					zipcode="";
					streetname="";
					aptno="";
					
					if(ct>0)
					{
						if(!addresstype.equalsIgnoreCase(""))
						{
							addresstype=addresstype+", "+rs1.getString(2);
							county=county+", "+rs1.getString(3);
							city=city+", "+rs1.getString(4);
							state=state+", "+rs1.getString(5);
							zipcode=zipcode+", "+rs1.getString(6);
							streetname=streetname+", "+rs1.getString(7);
							aptno=aptno+", "+rs1.getString(8);
						}
						else
						{
							addresstype=rs1.getString(2);
							county=rs1.getString(3);
							city=rs1.getString(4);
							state=rs1.getString(5);
							zipcode=rs1.getString(6);
							streetname=rs1.getString(7);
							aptno=rs1.getString(8);
						}
					}
					else
					{
						addresstype=(rs1.getString(2)==null?d:rs1.getString(2).toString());
						county=(rs1.getString(3)==null?d:rs1.getString(3).toString());
						city=(rs1.getString(4)==null?d:rs1.getString(4).toString());
						state=(rs1.getString(5)==null?d:rs1.getString(5).toString());
						zipcode=(rs1.getString(6)==null?d:rs1.getString(6).toString());
						streetname=(rs1.getString(7)==null?d:rs1.getString(7).toString());
						aptno=(rs1.getString(8)==null?d:rs1.getString(8).toString());
						
					}
				}
				
				ct++;
				a=msg;
			}
			rs1.close();
			
			System.out.println("addresstype: "+addresstype);
			lbladdresstype1 = new JLabel("Address Type: ");
			lbladdresstype2 = new JLabel(" "+addresstype);
			this.add(lbladdresstype1);
			this.add(lbladdresstype2);
			
			panel.add(lbladdresstype1);
			panel.add(lbladdresstype2);
			
			lbladdresstype1.setBorder(b);
			lbladdresstype1.setFont(font);
			lbladdresstype1.setForeground(Color.blue);
			
			lbladdresstype2.setBorder(b);
			
			lblcounty1 = new JLabel("County: ");
			lblcounty2 = new JLabel(" "+county);
			this.add(lblcounty1);
			this.add(lblcounty2);
			
			panel.add(lblcounty1);
			panel.add(lblcounty2);
			
			lblcounty1.setBorder(b);
			lblcounty1.setFont(font);
			lblcounty1.setForeground(Color.blue);
			
			lblcounty2.setBorder(b);
			
			lblcity1 = new JLabel("City: ");
			lblcity2 = new JLabel(" "+city);
			this.add(lblcity1);
			this.add(lblcity2);
			
			panel.add(lblcity1);
			panel.add(lblcity2);
			
			lblcity1.setBorder(b);
			lblcity1.setFont(font);
			lblcity1.setForeground(Color.blue);
			
			lblcity2.setBorder(b);
			
			lblstate1 = new JLabel("State: ");
			lblstate2 = new JLabel(" "+state);
			this.add(lblstate1);
			this.add(lblstate2);
			
			panel.add(lblstate1);
			panel.add(lblstate2);
			
			lblstate1.setBorder(b);
			lblstate1.setFont(font);
			lblstate1.setForeground(Color.blue);
			
			lblstate2.setBorder(b);
			
			lblzipcode1 = new JLabel("Zip Code: ");
			lblzipcode2 = new JLabel(" "+zipcode);
			this.add(lblzipcode1);
			this.add(lblzipcode2);
			
			panel.add(lblzipcode1);
			panel.add(lblzipcode2);
			
			lblzipcode1.setBorder(b);
			lblzipcode1.setFont(font);
			lblzipcode1.setForeground(Color.blue);
			
			lblzipcode2.setBorder(b);
			
			lblstreetname1 = new JLabel("Street Name: ");
			lblstreetname2 = new JLabel(" "+streetname);
			this.add(lblstreetname1);
			this.add(lblstreetname2);
			
			panel.add(lblstreetname1);
			panel.add(lblstreetname2);
			
			lblstreetname1.setBorder(b);
			lblstreetname1.setFont(font);
			lblstreetname1.setForeground(Color.blue);
			
			lblstreetname2.setBorder(b);
			
			lblaptno1 = new JLabel("Apt/House No.: ");
			lblaptno2 = new JLabel(" "+aptno);
			this.add(lblaptno1);
			this.add(lblaptno2);
			
			panel.add(lblaptno1);
			panel.add(lblaptno2);
			
			lblaptno1.setBorder(b);
			lblaptno1.setFont(font);
			lblaptno1.setForeground(Color.blue);
			
			lblaptno2.setBorder(b);
			
			////////////////////////////Appointment Details////////////////////////////
			String query2="select * from contactmanagerdatabase1.appointment_details where ap_contact_person_id='"+msg+"'";
			rs2=stmt2.executeQuery(query2);
			while(rs2.next())
			{
				meetingsubject=(rs2.getString(2)==null?d:rs2.getString(2));
				meetingtype=(rs2.getString(3)==null?d:rs2.getString(3));
				date=(rs2.getString(4)==null?d:rs2.getString(4));
				place=(rs2.getString(5)==null?d:rs2.getString(5));
				timezone=(rs2.getString(6)==null?d:rs2.getString(6));
				birthday=(rs2.getString(7)==null?d:rs2.getString(7));
				lastmeetingdate=(rs2.getString(8)==null?d:rs2.getString(8));
			}
			rs2.close();
			
			lblmeetingsubject1 = new JLabel("Meeting Subject: ");
			lblmeetingsubject2 = new JLabel(" "+meetingsubject);
			this.add(lblmeetingsubject1);
			this.add(lblmeetingsubject2);
			
			panel.add(lblmeetingsubject1);
			panel.add(lblmeetingsubject2);
			
			lblmeetingsubject1.setBorder(b);
			lblmeetingsubject1.setFont(font);
			lblmeetingsubject1.setForeground(Color.blue);
			
			lblmeetingsubject2.setBorder(b);
			
			lblmeetingtype1 = new JLabel("Meeting Type: ");
			lblmeetingtype2 = new JLabel(" "+meetingtype);
			this.add(lblmeetingtype1);
			this.add(lblmeetingtype2);
			
			panel.add(lblmeetingtype1);
			panel.add(lblmeetingtype2);
			
			lblmeetingtype1.setBorder(b);
			lblmeetingtype1.setFont(font);
			lblmeetingtype1.setForeground(Color.blue);
			
			lblmeetingtype2.setBorder(b);
			
			lbldate1 = new JLabel("Date: ");
			lbldate2 = new JLabel(" "+date);
			this.add(lbldate1);
			this.add(lbldate2);
			
			panel.add(lbldate1);
			panel.add(lbldate2);
			
			lbldate1.setBorder(b);
			lbldate1.setFont(font);
			lbldate1.setForeground(Color.blue);
			
			lbldate2.setBorder(b);
			
			lblplace1 = new JLabel("Place: ");
			lblplace2 = new JLabel(" "+place);
			this.add(lblplace1);
			this.add(lblplace2);
			
			panel.add(lblplace1);
			panel.add(lblplace2);
			
			lblplace1.setBorder(b);
			lblplace1.setFont(font);
			lblplace1.setForeground(Color.blue);
			
			lblplace2.setBorder(b);
			
			lbltimezone1 = new JLabel("Timezone: ");
			lbltimezone2 = new JLabel(" "+timezone);
			this.add(lbltimezone1);
			this.add(lbltimezone2);
			
			panel.add(lbltimezone1);
			panel.add(lbltimezone2);
			
			lbltimezone1.setBorder(b);
			lbltimezone1.setFont(font);
			lbltimezone1.setForeground(Color.blue);
			
			lbltimezone2.setBorder(b);
			
			lblbirthday1 = new JLabel("Birthday: ");
			lblbirthday2 = new JLabel(" "+birthday);
			this.add(lblbirthday1);
			this.add(lblbirthday2);
			
			panel.add(lblbirthday1);
			panel.add(lblbirthday2);
			
			lblbirthday1.setBorder(b);
			lblbirthday1.setFont(font);
			lblbirthday1.setForeground(Color.blue);
			
			lblbirthday2.setBorder(b);
			
			lbllastmeetingdate1 = new JLabel("Last Meeting Date: ");
			lbllastmeetingdate2 = new JLabel(" "+lastmeetingdate);
			this.add(lbllastmeetingdate1);
			this.add(lbllastmeetingdate2);
			
			panel.add(lbllastmeetingdate1);
			panel.add(lbllastmeetingdate2);
			
			lbllastmeetingdate1.setBorder(b);
			lbllastmeetingdate1.setFont(font);
			lbllastmeetingdate1.setForeground(Color.blue);
			
			lbllastmeetingdate2.setBorder(b);
			
			///////////////////Email Details////////////////
			String e="";
			String query3="select * from contactmanagerdatabase1.email_details where e_contact_person_id='"+msg+"'";
			//System.out.println("query3: "+query3);
			rs3=stmt3.executeQuery(query3);
			while(rs3.next())
			{
				//System.out.println("ect here: "+ect);
				if(msg.equalsIgnoreCase(e))
				{
					if(ect>0)
					{
						emailid=emailid+", "+rs3.getString(2);
						emailtype=emailtype+", "+rs3.getString(4);
					}
					else
					{
						emailid=(rs3.getString(2)==null?d:rs3.getString(2));
						emailtype=(rs3.getString(4)==null?d:rs3.getString(4));
					}
				}
				else
				{
					emailid="";
					emailtype="";
					
					if(ect>0)
					{
						if(!emailid.equalsIgnoreCase(""))
						{
							emailid=emailid+", "+rs3.getString(2);
							emailtype=emailtype+", "+rs3.getString(4);
						}
						else
						{
							emailid=(rs3.getString(2)==null?d:rs3.getString(2));
							emailtype=(rs3.getString(4)==null?d:rs3.getString(4));
						}
					}
					else
					{
						emailid=(rs3.getString(2)==null?d:rs3.getString(2));
						emailtype=(rs3.getString(4)==null?d:rs3.getString(4));
					}
				}
				ect++;
				e=msg;
			}
			rs3.close();
			
			lblemailid1 = new JLabel("Email ID: ");
			lblemailid2 = new JLabel(" "+emailid);
			this.add(lblemailid1);
			this.add(lblemailid2);
			
			panel.add(lblemailid1);
			panel.add(lblemailid2);
			
			lblemailid1.setBorder(b);
			lblemailid1.setFont(font);
			lblemailid1.setForeground(Color.blue);
			
			lblemailid2.setBorder(b);
			
			lblemailtype1 = new JLabel("Email Type: ");
			lblemailtype2 = new JLabel(" "+emailtype);
			this.add(lblemailtype1);
			this.add(lblemailtype2);
			
			panel.add(lblemailtype1);
			panel.add(lblemailtype2);
			
			lblemailtype1.setBorder(b);
			lblemailtype1.setFont(font);
			lblemailtype1.setForeground(Color.blue);
			
			lblemailtype2.setBorder(b);
			
			///////////////////Phone Details//////////////////
			String m="";
			String query4="select * from contactmanagerdatabase1.phone_details where p_contact_person_id='"+msg+"'";
			rs4=stmt3.executeQuery(query4);
			while(rs4.next())
			{
				System.out.println("pct here: "+pct);
				if(msg.equalsIgnoreCase(m))
				{
					if(pct>0)
					{
						phoneno=phoneno+", "+rs4.getString(2);
						phonetype=phonetype+", "+rs4.getString(3);
						extention=extention+", "+rs4.getString(4);
						status=status+", "+rs4.getString(5);
					}
					else
					{
						phoneno=(rs4.getString(2)==null?d:rs4.getString(2));
						phonetype=(rs4.getString(3)==null?d:rs4.getString(3));
						extention=(rs4.getString(4)==null?d:rs4.getString(4));
						status=(rs4.getString(5)==null?d:rs4.getString(5));
					}
				}
				else
				{
					phoneno="";
					phonetype="";
					extention="";
					status="";
					
					if(pct>0)
					{
						if(!phoneno.equalsIgnoreCase(""))
						{
							phoneno=phoneno+", "+rs4.getString(2);
							phonetype=phonetype+", "+rs4.getString(3);
							extention=extention+", "+rs4.getString(4);
							status=status+", "+rs4.getString(5);
						}
						else
						{
							phoneno=rs4.getString(2);
							phonetype=rs4.getString(3);
							extention=rs4.getString(4);
							status=rs4.getString(5);
						}
					}
					else
					{
						phoneno=(rs4.getString(2)==null?d:rs4.getString(2));
						phonetype=(rs4.getString(3)==null?d:rs4.getString(3));
						extention=(rs4.getString(4)==null?d:rs4.getString(4));
						status=(rs4.getString(5)==null?d:rs4.getString(5));
					}
				}
				pct++;
				m=msg;
			}
			rs4.close();
			
			lblphoneno1 = new JLabel("Phone No.: ");
			lblphoneno2 = new JLabel(" "+phoneno);
			this.add(lblphoneno1);
			this.add(lblphoneno2);
			
			panel.add(lblphoneno1);
			panel.add(lblphoneno2);
			
			lblphoneno1.setBorder(b);
			lblphoneno1.setFont(font);
			lblphoneno1.setForeground(Color.blue);
			
			lblphoneno2.setBorder(b);
			
			lblphonetype1 = new JLabel("Phone Type: ");
			lblphonetype2 = new JLabel(" "+phonetype);
			this.add(lblphonetype1);
			this.add(lblphonetype2);
			
			panel.add(lblphonetype1);
			panel.add(lblphonetype2);
			
			lblphonetype1.setBorder(b);
			lblphonetype1.setFont(font);
			lblphonetype1.setForeground(Color.blue);
			
			lblphonetype2.setBorder(b);
			
			lblextention1 = new JLabel("Extention: ");
			lblextention2 = new JLabel(" "+extention);
			this.add(lblextention1);
			this.add(lblextention2);
			
			panel.add(lblextention1);
			panel.add(lblextention2);
			
			lblextention1.setBorder(b);
			lblextention1.setFont(font);
			lblextention1.setForeground(Color.blue);
			
			lblextention2.setBorder(b);
			
			lblstatus1 = new JLabel("Status: ");
			lblstatus2 = new JLabel(" "+status);
			this.add(lblstatus1);
			this.add(lblstatus2);
			
			panel.add(lblstatus1);
			panel.add(lblstatus2);
			
			lblstatus1.setBorder(b);
			lblstatus1.setFont(font);
			lblstatus1.setForeground(Color.blue);
			
			lblstatus2.setBorder(b);
			
			this.setVisible(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
}
