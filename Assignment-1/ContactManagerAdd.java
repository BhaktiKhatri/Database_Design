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

public class ContactManagerAdd extends JFrame 
{
	static Connection conn = null;
	static ResultSet rs = null,rs1=null,rs2=null,rs3=null;
	static Statement stmt = null,stmt1=null,stmt2=null,stmt3=null;

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
	char ch;
	boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false, check7 = false, check8 = false, check9 = false, check10 = false;
	boolean check11 = false, check12 = false, check13 = false, check14 = false, check15 = false, check16 = false, check17 = false, check18 = false, check19 = false, check20 = false;
	boolean check21 = false, check22 = false, check23 = false, check24 = false, check25 = false, check26 = false, check27 = false, check28 = false, check29 = false, check30 = false;
	String message="";
	int countall=0;
	boolean chk=false;
	
	ContactManagerAdd()
	{
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

		submit = new JButton("Add Data");
		submit.addActionListener(new AddData());
//		lblpassport.setBorder(b);
//		lblpassport.setFont(font);
//		lblpassport.setForeground(Color.blue);
//		txtpassport.setBorder(b);

		this.add(submit,BorderLayout.CENTER);
		this.setBounds(10,10,1000,900);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Contact Manager Add Details");
		this.setVisible(true);
		
	}
		
	private class AddData implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JButton src = (JButton)e.getSource();
			//src.setText("Record Submitted");	
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
				
				String query="insert into contactmanagerdatabase1.contact_person values ('"+passport+"','"+contactid+"','"+groupid+"','"+firstname+"','"+middlename+"','"+lastname+"','"+gender+"','"+birthdate+"','"+faxno+"','"+methodofcontact+"')";
				System.out.println("query: "+query);
				stmt.executeUpdate(query);
				
				String query1="insert into contactmanagerdatabase1.address_details values ('"+contactid+"','"+addresstype+"','"+county+"','"+city+"','"+state+"','"+zipcode+"','"+streetname+"','"+aptno+"')";
				System.out.println("query1: "+query1);
				stmt.executeUpdate(query1);
				
				String query2="insert into contactmanagerdatabase1.appointment_details values ('"+contactid+"','"+meetingsubject+"','"+meetingtype+"','"+date+"','"+place+"','"+timezone+"','"+birthday+"','"+lastmeetingdate+"')";
				System.out.println("query2: "+query2);
				stmt.executeUpdate(query2);
				
				String query3="insert into contactmanagerdatabase1.email_details values ('"+contactid+"','"+emailid+"','"+groupid+"','"+emailtype+"')";
				System.out.println("query3: "+query3);
				stmt.executeUpdate(query3);

				String query4="insert into contactmanagerdatabase1.phone_details values ('"+contactid+"','"+phoneno+"','"+phonetype+"','"+extention+"','"+status+"')";
				System.out.println("query4: "+query4);
				stmt.executeUpdate(query4);
				
				String query5="insert into contactmanagerdatabase1.contact_manager values ('"+passport+"','"+firstname+"','"+middlename+"','"+lastname+"','"+emailid+"','"+phoneno+"','"+birthdate+"','"+gender+"','"+addresstype+"','"+county+"','"+city+"','"+state+"','"+zipcode+"','"+streetname+"','"+aptno+"','"+contactid+"')";
				System.out.println("query5: "+query5);
				stmt.executeUpdate(query5);
				
				src.setText("Record Submitted");
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
            ContactManagerAdd cma = new ContactManagerAdd();
		}
		catch (Exception e)
		{
			System.out.println("Exception: "+e);
		}
	}

}
