import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;

public class ContactManagerUpdate extends JFrame implements ItemListener
{
	static Connection conn = null;
	static ResultSet rs = null,rs1=null,rs2=null,rs3=null,rs4=null;
	static Statement stmt = null,stmt1=null,stmt2=null,stmt3=null,stmt4=null;

	JButton submit;
	JTextField txtpassport;
	JLabel lblpassport;
	String passport="";
	
	JTextField txtcontactid;
	JLabel lblcontactid;
	String contactid="";
	
	JTextField txtgroupid;
	JLabel lblgroupid;
	String groupid="";
	
	JTextField txtfirstname;
	JLabel lblfirstname;
	String firstname="";
	
	JTextField txtmiddlename;
	JLabel lblmiddlename;
	String middlename="";
	
	JTextField txtlastname;
	JLabel lbllastname;
	String lastname="";
	
	JTextField txtgender;
	JLabel lblgender;
	String gender="";
	
	JTextField txtbirthdate;
	JLabel lblbirthdate;
	String birthdate="";
	
	JTextField txtfaxno;
	JLabel lblfaxno;
	String faxno="";
	
	JTextField txtmethodofcontact;
	JLabel lblmethodofcontact;
	String methodofcontact="";
	
	JTextField txtaddresstype;
	JLabel lbladdresstype;
	String addresstype="";
	
	JTextField txtcounty;
	JLabel lblcounty;
	String county="";
	
	JTextField txtcity;
	JLabel lblcity;
	String city="";
	
	JTextField txtstate;
	JLabel lblstate;
	String state="";
	
	JTextField txtzipcode;
	JLabel lblzipcode;
	String zipcode="";
	
	JTextField txtstreetname;
	JLabel lblstreetname;
	String streetname="";
	
	JTextField txtaptno;
	JLabel lblaptno;
	String aptno="";
	
	JTextField txtmeetingsubject;
	JLabel lblmeetingsubject;
	String meetingsubject="";
	
	JTextField txtmeetingtype;
	JLabel lblmeetingtype;
	String meetingtype="";
	
	JTextField txtdate;
	JLabel lbldate;
	String date="";
	
	JTextField txtplace;
	JLabel lblplace;
	String place="";
	
	JTextField txttimezone;
	JLabel lbltimezone;
	String timezone="";
	
	JTextField txtbirthday;
	JLabel lblbirthday;
	String birthday="";
	
	JTextField txtlastmeetingdate;
	JLabel lbllastmeetingdate;
	String lastmeetingdate="";
	
	JTextField txtemailid;
	JLabel lblemailid;
	String emailid="";
	
	JTextField txtemailtype;
	JLabel lblemailtype;
	String emailtype="";
	
	JTextField txtphoneno;
	JLabel lblphoneno;
	String phoneno="";
	
	JTextField txtphonetype;
	JLabel lblphonetype;
	String phonetype="";
	
	JTextField txtextention;
	JLabel lblextention;
	String extention="";
	
	JTextField txtstatus;
	JLabel lblstatus;
	String status="";
	JPanel panel;
	
	Choice c;
	static int count=0;
	String msg="";
	int index=0;
	int cnt=0;
	static Vector Vcontactid = new Vector();
	JLabel lbl1;
	int ct=0;

	char ch;
	boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false, check7 = false, check8 = false, check9 = false, check10 = false;
	boolean check11 = false, check12 = false, check13 = false, check14 = false, check15 = false, check16 = false, check17 = false, check18 = false, check19 = false, check20 = false;
	boolean check21 = false, check22 = false, check23 = false, check24 = false, check25 = false, check26 = false, check27 = false, check28 = false, check29 = false, check30 = false;
	String message="";
	int countall=0;
	boolean chk=false;
	
	ContactManagerUpdate()
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

		
		lblpassport = new JLabel("Passport No: ");
		txtpassport = new JTextField(20);
		
		lblcontactid = new JLabel("Contact ID: ");
		txtcontactid = new JTextField(20);

		lblgroupid = new JLabel("Group ID: ");
		txtgroupid = new JTextField(20);
		
		lblfirstname = new JLabel("First Name: ");
		txtfirstname = new JTextField(20);
		
		lblmiddlename = new JLabel("Middle Name: ");
		txtmiddlename = new JTextField(20);
		
		lbllastname = new JLabel("Last Name: ");
		txtlastname = new JTextField(20);
		
		lblgender = new JLabel("Gender: ");
		txtgender = new JTextField(20);
		
		lblbirthdate = new JLabel("Birth Date: ");
		txtbirthdate = new JTextField(20);
		
		lblfaxno = new JLabel("Fax No: ");
		txtfaxno = new JTextField(20);
		
		lblmethodofcontact = new JLabel("Method of Contact: ");
		txtmethodofcontact = new JTextField(20);
		
		lbladdresstype = new JLabel("Address Type: ");
		txtaddresstype = new JTextField(20);
		
		lblcounty = new JLabel("County: ");
		txtcounty = new JTextField(20);
		
		lblcity = new JLabel("City: ");
		txtcity = new JTextField(20);
		
		lblstate = new JLabel("State: ");
		txtstate = new JTextField(20);
		
		lblzipcode = new JLabel("Zip Code: ");
		txtzipcode = new JTextField(20);
		
		lblstreetname = new JLabel("Street Name: ");
		txtstreetname = new JTextField(20);
		
		lblaptno = new JLabel("Apt/House No.: ");
		txtaptno = new JTextField(20);
		
		lblmeetingsubject = new JLabel("Meeting Subject: ");
		txtmeetingsubject = new JTextField(20);
		
		lblmeetingtype = new JLabel("Meeting Type: ");
		txtmeetingtype = new JTextField(20);
		
		lbldate = new JLabel("Date: ");
		txtdate = new JTextField(20);
		
		lblplace = new JLabel("Place: ");
		txtplace = new JTextField(20);
		
		lbltimezone = new JLabel("Timezone: ");
		txttimezone = new JTextField(20);
		
		lblbirthday = new JLabel("Birthday: ");
		txtbirthday = new JTextField(20);
		
		lbllastmeetingdate = new JLabel("Last Meeting Date: ");
		txtlastmeetingdate = new JTextField(20);
		
		lblemailid = new JLabel("Email ID: ");
		txtemailid = new JTextField(20);
		
		lblemailtype = new JLabel("Email Type: ");
		txtemailtype = new JTextField(20);
		
		lblphoneno = new JLabel("Phone No.: ");
		txtphoneno = new JTextField(20);
		
		lblphonetype = new JLabel("Phone Type: ");
		txtphonetype = new JTextField(20);
		
		lblextention = new JLabel("Extention: ");
		txtextention = new JTextField(20);
		
		lblstatus = new JLabel("Status: ");
		txtstatus = new JTextField(20);

		submit = new JButton("Submit");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(31,1));
		panel.setSize(800,800);

		this.add(panel);
		
		panel.add(lblpassport,BorderLayout.WEST);
		panel.add(txtpassport,BorderLayout.EAST);
		
		panel.add(lblcontactid,BorderLayout.WEST);
		panel.add(txtcontactid,BorderLayout.EAST);
		
		panel.add(lblgroupid,BorderLayout.WEST);
		panel.add(txtgroupid,BorderLayout.EAST);
		
		panel.add(lblfirstname,BorderLayout.WEST);
		panel.add(txtfirstname,BorderLayout.EAST);
		
		panel.add(lblmiddlename,BorderLayout.WEST);
		panel.add(txtmiddlename,BorderLayout.EAST);
		
		panel.add(lbllastname,BorderLayout.WEST);
		panel.add(txtlastname,BorderLayout.EAST);
		
		panel.add(lblgender,BorderLayout.WEST);
		panel.add(txtgender,BorderLayout.EAST);
		
		panel.add(lblbirthdate,BorderLayout.WEST);
		panel.add(txtbirthdate,BorderLayout.EAST);
		
		panel.add(lblfaxno,BorderLayout.WEST);
		panel.add(txtfaxno,BorderLayout.EAST);
		
		panel.add(lblmethodofcontact,BorderLayout.WEST);
		panel.add(txtmethodofcontact,BorderLayout.EAST);
		
		panel.add(lbladdresstype,BorderLayout.WEST);
		panel.add(txtaddresstype,BorderLayout.EAST);
		
		panel.add(lblcounty,BorderLayout.WEST);
		panel.add(txtcounty,BorderLayout.EAST);
		
		panel.add(lblcity,BorderLayout.WEST);
		panel.add(txtcity,BorderLayout.EAST);
		
		panel.add(lblstate,BorderLayout.WEST);
		panel.add(txtstate,BorderLayout.EAST);
		
		panel.add(lblzipcode,BorderLayout.WEST);
		panel.add(txtzipcode,BorderLayout.EAST);
		
		panel.add(lblstreetname,BorderLayout.WEST);
		panel.add(txtstreetname,BorderLayout.EAST);
		
		panel.add(lblaptno,BorderLayout.WEST);
		panel.add(txtaptno,BorderLayout.EAST);
		
		panel.add(lblmeetingsubject,BorderLayout.WEST);
		panel.add(txtmeetingsubject,BorderLayout.EAST);
		
		panel.add(lblmeetingtype,BorderLayout.WEST);
		panel.add(txtmeetingtype,BorderLayout.EAST);
		
		panel.add(lbldate,BorderLayout.WEST);
		panel.add(txtdate,BorderLayout.EAST);
		
		panel.add(lblplace,BorderLayout.WEST);
		panel.add(txtplace,BorderLayout.EAST);
		
		panel.add(lbltimezone,BorderLayout.WEST);
		panel.add(txttimezone,BorderLayout.EAST);
		
		panel.add(lblbirthday,BorderLayout.WEST);
		panel.add(txtbirthday,BorderLayout.EAST);
		
		panel.add(lbllastmeetingdate,BorderLayout.WEST);
		panel.add(txtlastmeetingdate,BorderLayout.EAST);
		
		panel.add(lblemailid,BorderLayout.WEST);
		panel.add(txtemailid,BorderLayout.EAST);
		
		panel.add(lblemailtype,BorderLayout.WEST);
		panel.add(txtemailtype,BorderLayout.EAST);
		
		panel.add(lblphoneno,BorderLayout.WEST);
		panel.add(txtphoneno,BorderLayout.EAST);
		
		panel.add(lblphonetype,BorderLayout.WEST);
		panel.add(txtphonetype,BorderLayout.EAST);
		
		panel.add(lblextention,BorderLayout.WEST);
		panel.add(txtextention,BorderLayout.EAST);
		
		panel.add(lblstatus,BorderLayout.WEST);
		panel.add(txtstatus,BorderLayout.EAST);
		
		Font font = new Font("Courier New", Font.ITALIC, 15);
		Border b = BorderFactory.createLineBorder(Color.lightGray, 1,true);

		submit = new JButton("Update Data");
		submit.addActionListener(new UpdateData());

		this.add(submit,BorderLayout.CENTER);
		this.setBounds(10,10,1000,900);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Contact Manager Update Details");
		this.setVisible(true);

	}
	
	public static void main(String[] args) 
	{
		ContactManagerUpdate cmu = new ContactManagerUpdate();
	}

	private class UpdateData implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JButton src = (JButton)e.getSource();
			//src.setText("Record Updated!");	
			passport = txtpassport.getText();
			contactid = txtcontactid.getText();
			groupid = txtgroupid.getText();
			firstname = txtfirstname.getText();
			middlename = txtmiddlename.getText();
			lastname= txtlastname.getText();
			gender= txtgender.getText();
			birthdate= txtbirthdate.getText();
			faxno= txtfaxno.getText();
			methodofcontact = txtmethodofcontact.getText();
			addresstype= txtaddresstype.getText();
			county= txtcounty.getText();
			city= txtcity.getText();
			state= txtstate.getText();
			zipcode= txtzipcode.getText();
			streetname = txtstreetname.getText();
			aptno = txtaptno.getText();
			meetingsubject= txtmeetingsubject.getText();
			meetingtype= txtmeetingtype.getText();
			date= txtdate.getText();
			place= txtplace.getText();
			timezone= txttimezone.getText();
			birthday= txtbirthday.getText();
			lastmeetingdate= txtlastmeetingdate.getText();
			emailid= txtemailid.getText();
			emailtype= txtemailtype.getText();
			phoneno= txtphoneno.getText();
			phonetype= txtphonetype.getText();
			extention= txtextention.getText();
			status= txtstatus.getText();
			
			for(int i=0;i<contactid.length();i++)
			{
				ch=contactid.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check1=true;
					message="Please enter numeric value for Contact ID!";
				}
			}
			System.out.println("check1: "+check1);

			for(int i=0;i<groupid.length();i++)
			{
				ch=groupid.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check2=true;
					if(message.length()==0)
					{
						message="Please enter numeric value for Group ID!";
					}
					else
					{
						message=message+"\n Please enter numeric value for Group ID!";
					}
				}
			}
			System.out.println("check2: "+check2);

			for(int i=0;i<firstname.length();i++)
			{
				ch=firstname.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check3=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for First Name!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for First Name!";
					}
				}
			}
			System.out.println("check3: "+check3);

			for(int i=0;i<middlename.length();i++)
			{
				ch=middlename.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check4=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Middle Name!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Middle Name!";
					}
				}
			}
			System.out.println("check4: "+check4);
			
			for(int i=0;i<lastname.length();i++)
			{
				ch=lastname.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check5=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Last Name!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Last Name!";
					}
				}
			}
			System.out.println("check5: "+check5);
			
			for(int i=0;i<gender.length();i++)
			{
				ch=gender.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check6=true;
					if(message.length()==0)
					{
						message="Please enter only an alphabetic character for Gender!";
					}
					else
					{
						message=message+"\n Please enter only an alphabetic character for Gender!";
					}
				}
			}
			System.out.println("check6: "+check6);
			
			for(int i=0;i<birthdate.length();i++)
			{
				ch=birthdate.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check7=true;
					if(message.length()==0)
					{
						message="Please enter date in proper format for Birthdate!";
					}
					else
					{
						message=message+"\n Please enter date in proper format  for Birthdate!";
					}
				}
			}
			System.out.println("check7: "+check7);
			
			for(int i=0;i<faxno.length();i++)
			{
				ch=faxno.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check8=true;
					if(message.length()==0)
					{
						message="Please enter only numeric values for Fax No!";
					}
					else
					{
						message=message+"\n Please enter only numeric values for Fax No!";
					}
				}
			}
			System.out.println("check8: "+check8);
			
			for(int i=0;i<methodofcontact.length();i++)
			{
				ch=methodofcontact.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check9=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Method of Contact!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Method of Contact!";
					}
				}
			}
			System.out.println("check9: "+check9);
			
			for(int i=0;i<addresstype.length();i++)
			{
				ch=addresstype.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check10=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Address Type!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Address Type!";
					}
				}
			}
			System.out.println("check10: "+check10);
			
			for(int i=0;i<county.length();i++)
			{
				ch=county.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check11=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for County!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for County!";
					}
				}
			}
			System.out.println("check11: "+check11);
			
			for(int i=0;i<city.length();i++)
			{
				ch=city.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check12=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for City!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for City!";
					}
				}
			}
			System.out.println("check12: "+check12);
			
			for(int i=0;i<state.length();i++)
			{
				ch=state.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check13=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for State!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for State!";
					}
				}
			}
			System.out.println("check13: "+check13);

			for(int i=0;i<zipcode.length();i++)
			{
				ch=zipcode.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check14=true;
					if(message.length()==0)
					{
						message="Please enter only numeric values for Zip Code!";
					}
					else
					{
						message=message+"\n Please enter only numeric values for Zip Code!";
					}
				}
			}
			System.out.println("check14: "+check14);

			for(int i=0;i<streetname.length();i++)
			{
				ch=streetname.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check15=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Street Name!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Street Name!";
					}
				}
			}
			System.out.println("check15: "+check15);

			for(int i=0;i<aptno.length();i++)
			{
				ch=aptno.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check16=true;
					if(message.length()==0)
					{
						message="Please enter only numeric values for Apt/House No!";
					}
					else
					{
						message=message+"\n Please enter only numeric values for Apt/House No!";
					}
				}
			}
			System.out.println("check16: "+check16);

			for(int i=0;i<meetingsubject.length();i++)
			{
				ch=meetingsubject.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check17=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Meeting Subject!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Meeting Subject!";
					}
				}
			}
			System.out.println("check17: "+check17);

			for(int i=0;i<date.length();i++)
			{
				ch=date.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check18=true;
					if(message.length()==0)
					{
						message="Please enter date in proper format for Date!";
					}
					else
					{
						message=message+"\n Please enter date in proper format for Date!";
					}
				}
			}
			System.out.println("check18: "+check18);
			
			for(int i=0;i<place.length();i++)
			{
				ch=place.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check19=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Place!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Place!";
					}
				}
			}
			System.out.println("check19: "+check19);

			for(int i=0;i<birthday.length();i++)
			{
				ch=birthday.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check20=true;
					if(message.length()==0)
					{
						message="Please enter proper details for Birthday!";
					}
					else
					{
						message=message+"\n Please enter proper details for Birthday!";
					}
				}
			}
			System.out.println("check20: "+check20);

			
			for(int i=0;i<lastmeetingdate.length();i++)
			{
				ch=lastmeetingdate.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check21=true;
					if(message.length()==0)
					{
						message="Please enter date in proper format for Last Meeting Date!";
					}
					else
					{
						message=message+"\n Please enter date in proper format for Last Meeting Date!";
					}
				}
			}
			System.out.println("check21: "+check21);

			for(int i=0;i<emailid.length();i++)
			{
				ch=emailid.charAt(i);
				if(ch!='@' || ch!='.')
				{
					check22=true;
					if(message.length()==0)
					{
						System.out.println("in if");
						message="Please enter a proper Email ID!";
					}
					else
					{
						System.out.println("in else");
						message=message+"\n Please enter a proper Email ID!";
					}
				}
			}
			System.out.println("check22: "+check22);
			
			for(int i=0;i<emailtype.length();i++)
			{
				ch=emailtype.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check23=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Email Type!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Email Type!";
					}
				}
			}
			System.out.println("check23: "+check23);

			for(int i=0;i<phoneno.length();i++)
			{
				ch=phoneno.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check24=true;
					if(message.length()==0)
					{
						message="Please enter numeric values for Phone No!";
					}
					else
					{
						message=message+"\n Please enter numeric values for Phone No!";
					}
				}
			}
			System.out.println("check24: "+check24);

			for(int i=0;i<phonetype.length();i++)
			{
				ch=phonetype.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check25=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Phone Type!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Phone Type!";
					}
				}
			}
			System.out.println("check25: "+check25);

			for(int i=0;i<extention.length();i++)
			{
				ch=extention.charAt(i);
				if((ch>=97 && ch<=122) || (ch>=65 && ch<=90))
				{
					check26=true;
					if(message.length()==0)
					{
						message="Please enter numeric values for Extention!";
					}
					else
					{
						message=message+"\n Please enter numeric values for Extention!";
					}
				}
			}
			System.out.println("check26: "+check26);

			for(int i=0;i<status.length();i++)
			{
				ch=status.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check27=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Status!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Status!";
					}
				}
			}
			System.out.println("check27: "+check27);

			for(int i=0;i<meetingtype.length();i++)
			{
				ch=meetingtype.charAt(i);
				if(ch==1 || ch==2 || ch==3 || ch==4 || ch==5 || ch==6 || ch==7 || ch==8 || ch==9 || ch==0)
				{
					check28=true;
					if(message.length()==0)
					{
						message="Please enter only alphabets for Meeting Type!";
					}
					else
					{
						message=message+"\n Please enter only alphabets for Meeting Type!";
					}
				}
			}
			System.out.println("check28: "+check28);

			if(check1==true || check2==true || check3==true || check4==true || check5==true || check6==true || check7==true || check8==true || check9==true || check10==true || check11==true || check12==true || check13==true || check14==true || check15==true || check16==true || check17==true || check18==true || check19==true || check20==true || check21==true || check22==true || check23==true || check24==true || check25==true || check26==true || check27==true || check28==true)
			{
				check1=false;check2=false;check3=false;check4=false;check5=false;check6=false;check7=false;check8=false;check9=false;check10=false;
			    Toolkit.getDefaultToolkit().beep();
			    JOptionPane optionPane = new JOptionPane(message,JOptionPane.WARNING_MESSAGE);
			    JDialog dialog = optionPane.createDialog("Warning!");
			    dialog.setAlwaysOnTop(true);
			    dialog.setVisible(true);
			    chk=true;
			}
		
			if(passport.length()==0 || contactid.length()==0 || groupid.length()==0 || firstname.length()==0 || middlename.length()==0 || lastname.length()==0 || gender.length()==0 || birthdate.length()==0 || faxno.length()==0 || methodofcontact.length()==0 || addresstype.length()==0 || county.length()==0 || city.length()==0 || state.length()==0 || zipcode.length()==0 || streetname.length()==0 || aptno.length()==0 || meetingsubject.length()==0 || meetingtype.length()==0 || date.length()==0 || place.length()==0 || timezone.length()==0 || birthday.length()==0 || lastmeetingdate.length()==0 || emailid.length()==0 || emailtype.length()==0 || phoneno.length()==0 || phonetype.length()==0 || extention.length()==0 || status.length()==0) 
			{
				message="Please enter all the fields first!";
				check1=false;check2=false;check3=false;check4=false;check5=false;check6=false;check7=false;check8=false;check9=false;check10=false;
			    Toolkit.getDefaultToolkit().beep();
			    JOptionPane optionPane = new JOptionPane(message,JOptionPane.WARNING_MESSAGE);
			    JDialog dialog = optionPane.createDialog("Warning!");
			    dialog.setAlwaysOnTop(true);
			    dialog.setVisible(true);
			    chk=true;
			}
			
			if(chk==false)
			{
			try
			{
				
				String q="SET FOREIGN_KEY_CHECKS = 0";
				rs=stmt.executeQuery(q);
				
				String query="update contactmanagerdatabase1.contact_person set P_Passport_No='"+passport+"',P_Contact_ID='"+contactid+"',P_Group_ID='"+groupid+"',P_First_Name='"+firstname+"',P_Middle_Name='"+middlename+"',P_Last_Name='"+lastname+"',P_Gender='"+gender+"',P_Birth_Date='"+birthdate+"',P_Fax_No='"+faxno+"',Preferred_Method_Of_Contact='"+methodofcontact+"' where p_contact_id='"+msg+"'";
				System.out.println("query: "+query);
				stmt.executeUpdate(query);
				
				String query1="update contactmanagerdatabase1.address_details set Ad_Contact_Person_ID='"+contactid+"',Address_Type='"+addresstype+"',County='"+county+"',City='"+city+"',State='"+state+"',ZipCode='"+zipcode+"',Street_Name='"+streetname+"',Apt_House_No='"+aptno+"'  where ad_contact_person_id='"+msg+"'";
				System.out.println("query1: "+query1);
				stmt.executeUpdate(query1);
				
				String query2="update contactmanagerdatabase1.appointment_details set Ap_Contact_Person_ID='"+contactid+"',Call_Meeting_Subject='"+meetingsubject+"',Meeting_Type='"+meetingtype+"',Date='"+date+"',Place='"+place+"',TimeZone='"+timezone+"',Birthday='"+birthday+"',Last_Meeting_Date='"+lastmeetingdate+"' where ap_contact_person_id='"+msg+"'";
				System.out.println("query2: "+query2);
				stmt.executeUpdate(query2);
				
				String query3="update contactmanagerdatabase1.email_details set E_Contact_Person_ID='"+contactid+"',Email_ID='"+emailid+"',Email_Group_ID='"+groupid+"',Email_Type='"+emailtype+"' where e_contact_person_id='"+msg+"'";
				System.out.println("query3: "+query3);
				stmt.executeUpdate(query3);

				String query4="update contactmanagerdatabase1.phone_details set P_Contact_Person_ID='"+contactid+"',Phone_Number='"+phoneno+"',Phone_Type='"+phonetype+"',Extention='"+extention+"',Status='"+status+"' where p_contact_person_id='"+msg+"'";
				System.out.println("query4: "+query4);
				stmt.executeUpdate(query4);
				
				String query5="update contactmanagerdatabase1.contact_manager set M_Passport_No='"+passport+"',M_Contact_Person_ID='"+contactid+"',M_First_Name='"+firstname+"',M_Middle_Name='"+middlename+"',M_Last_Name='"+lastname+"',M_Gender='"+gender+"',M_Birth_Date='"+birthdate+"',M_Email_ID='"+emailid+"',M_Phone_No='"+phoneno+"',M_Address_Type='"+addresstype+"',M_County='"+county+"',M_City='"+city+"',M_State='"+state+"',M_Zip_Code='"+zipcode+"',M_Street_Name='"+streetname+"',M_Apt_House_No='"+aptno+"' where M_Contact_Person_ID='"+msg+"'";
				System.out.println("query5: "+query5);
				stmt.executeUpdate(query5);
				
				src.setText("Record Updated!");
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

	public void itemStateChanged(ItemEvent e) 
	{
		msg=c.getSelectedItem();
		index=c.getSelectedIndex();
		String d="-";

//		if(cnt>0)
//		{
//			//this.remove(panel);
//		}
		
		try
		{
			//cnt++;
			////////////////CONTACT PERSON DETAILS///////////////
			
			String query="SELECT * FROM contactmanagerdatabase1.contact_person where p_contact_id='"+msg+"'";
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
			  passport=(rs.getString(1)==null?d:rs.getString(1).toString());
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
			
			txtpassport.setText(passport);
			txtcontactid.setText(contactid);
			txtgroupid.setText(groupid);
			txtfirstname.setText(firstname);
			txtmiddlename.setText(middlename);
			txtlastname.setText(lastname);
			txtgender.setText(gender);
			txtbirthdate.setText(birthdate);
			txtfaxno.setText(faxno);
			txtmethodofcontact.setText(methodofcontact);
			
			//////////////////////ADDRESS DETAILS/////////////

			String a="";
			String query1="select * from contactmanagerdatabase1.address_details where ad_contact_person_id='"+msg+"'";
			System.out.println("query1: "+query1);
			rs1 = stmt1.executeQuery(query1);
			while(rs1.next())
			{
				System.out.println("ct here: "+ct);
				addresstype=(rs1.getString(2)==null?d:rs1.getString(2).toString());
				county=(rs1.getString(3)==null?d:rs1.getString(3).toString());
				city=(rs1.getString(4)==null?d:rs1.getString(4).toString());
				state=(rs1.getString(5)==null?d:rs1.getString(5).toString());
				zipcode=(rs1.getString(6)==null?d:rs1.getString(6).toString());
				streetname=(rs1.getString(7)==null?d:rs1.getString(7).toString());
				aptno=(rs1.getString(8)==null?d:rs1.getString(8).toString());
				
			}
			rs1.close();
			
			txtaddresstype.setText(addresstype);
			txtcounty.setText(county);
			txtcity.setText(city);
			txtstate.setText(state);
			txtzipcode.setText(zipcode);
			txtstreetname.setText(streetname);
			txtaptno.setText(aptno);

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
			
			txtmeetingsubject.setText(meetingsubject);
			txtmeetingtype.setText(meetingtype);
			txtdate.setText(date);
			txtplace.setText(place);
			txttimezone.setText(timezone);
			txtbirthday.setText(birthday);
			txtlastmeetingdate.setText(lastmeetingdate);
			
			///////////////////Email Details////////////////
			String e1="";
			int ect=0;
			String query3="select * from contactmanagerdatabase1.email_details where e_contact_person_id='"+msg+"'";
			//System.out.println("query3: "+query3);
			rs3=stmt3.executeQuery(query3);
			while(rs3.next())
			{
				//System.out.println("ect here: "+ect);
				emailid=(rs3.getString(2)==null?d:rs3.getString(2));
				emailtype=(rs3.getString(4)==null?d:rs3.getString(4));
				
			}
			rs3.close();
			
			txtemailid.setText(emailid);
			txtemailtype.setText(emailtype);
			///////////////////Phone Details//////////////////
			
			String m="";
			int pct=0;
			String query4="select * from contactmanagerdatabase1.phone_details where p_contact_person_id='"+msg+"'";
			rs4=stmt3.executeQuery(query4);
			while(rs4.next())
			{
				System.out.println("pct here: "+pct);
				phoneno=(rs4.getString(2)==null?d:rs4.getString(2));
				phonetype=(rs4.getString(3)==null?d:rs4.getString(3));
				extention=(rs4.getString(4)==null?d:rs4.getString(4));
				status=(rs4.getString(5)==null?d:rs4.getString(5));
			}
			rs4.close();
			
			txtphoneno.setText(phoneno);
			txtphonetype.setText(phonetype);
			txtextention.setText(extention);
			txtstatus.setText(status);
			
			this.setVisible(true);
		
		}
		catch(Exception e2)
		{
			System.out.println("Exception e: "+e2);
		}
	}

}
