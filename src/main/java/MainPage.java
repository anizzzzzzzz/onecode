/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import InstantLogin.Login;
import InstantLogin.LoginFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Anish Maharjan
 */
public class MainPage implements ActionListener
{
    Login login=null;

    LoginFactory lf=new LoginFactory();
    
    JPanel p[]=new JPanel[10];
    JButton b2[]=new JButton[10];    
    JLabel l2[]=new JLabel[10];
    JLabel l1[]=new JLabel[10];
    JButton delbtn[]=new JButton[10];
    JButton editbtn[]=new JButton[10];
    
    int i=0;
    
    JFrame f;
    
    JLabel userName,welcome,instant,footer;
    JButton logoutbtn,refreshbtn;
    
    JPanel p1,p2,p3,p4;
    
    JMenuBar mb;
    JMenu file,help,add;
    JMenuItem mexit,minfo,mcont,mfb,mhotmail;
    
    private String fname=null,lname=null;
    private String masterkey=null;
    private int uid=0;
    
    private int count=0;
    
    public MainPage(String fname,String lname,String masterkey,int uid)
    {
        f=new JFrame("One Code");
        
        //--------setting values in getter setter method-------------------
        setMasterKey(masterkey);
        setUid(uid);
        setFirstName(fname);
        setLastName(lname);
        //-------------------------------------------------------------------
        
        menuPart();
        
        bodyPart();
        
        f.setVisible(true);
        f.setSize(800,650);                             
        f.setLocation(325,25);
        f.setResizable(false);  
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
   
    
    //-------------------------Setter Getter Method---------------------------------
    public void setMasterKey(String masterkey)
    {
        this.masterkey=masterkey;
    }
    
    public String getMasterKey(){return masterkey;}
    
    public void setUid(int uid)
    {
        this.uid=uid;
    }
    
    public int getUid(){return uid;}
    
    public void setFirstName(String fname)
    {
        this.fname=fname;
    }
    
    public String getFirstName(){return fname;}
    
    public void setLastName(String lname)
    {
        this.lname=lname;
    }
    
    public String getLastName(){return lname;}
    
    public void setCount(int count)
    {
        this.count=count;
    }
    
    public int getCount(){return count;}
    
    //----------------------------------------------------------
    
    
    public void menuPart()
    {
        mb=new JMenuBar();
        
        file= new JMenu("File");
        help=new JMenu("Help"); 
        add=new JMenu("Add Account");           
        
        mexit= new JMenuItem("Exit");
        mfb= new JMenuItem("Facebook");
        mhotmail= new JMenuItem("Hotmail");
        
        minfo= new JMenuItem("About");
        mcont= new JMenuItem("Contact");
        
        
        f.setJMenuBar(mb);
        
        mb.add(file);
        mb.add(help);
        
        file.add(add);
        add.add(mfb);
        mfb.addActionListener(this);
        add.add(mhotmail);
        mhotmail.addActionListener(this);
        file.addSeparator();        
        
        file.add(mexit);                
        mexit.addActionListener(this);
        
        help.add(minfo);
        help.addSeparator();
        minfo.addActionListener(this);
        
        help.add(mcont);
        mcont.addActionListener(this);
    }
    
    public void bodyPart()
    {
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p4=new JPanel();
        
        welcome=new JLabel();        
        
        instant=new JLabel("Instant Login");
        userName=new JLabel("Username");
        footer=new JLabel("@Copyright");        
        
        
        refreshbtn=new JButton("Refresh");
        logoutbtn=new JButton("Logout");
        
        f.setLayout(null); 
        
        //-------------Panel 1--------------------
        f.add(p1);
        p1.setBackground(Color.BLACK);
        p1.setBounds(5,5,785,80);
        
        p1.setLayout(null);
        
        p1.add(welcome);
        welcome.setBounds(10, 20, 250, 40);
        welcome.setForeground(Color.white);
        welcome.setText("Welcome, "+getFirstName()+" "+getLastName());
        
        p1.add(refreshbtn);
        refreshbtn.setBounds(470, 20, 150, 40);  
        refreshbtn.addActionListener(this);
                
        p1.add(logoutbtn);
        logoutbtn.setBounds(630, 20, 150, 40);  
        logoutbtn.addActionListener(this);
        //-------------End of Panel 1--------------------
        
        
        
        //-------------Panel 2--------------------
        f.add(p2);
        p2.setBackground(new Color(160, 222, 160));
        p2.setBounds(5,90,785,468);
        
        p2.setLayout(null);
        
        p2.add(instant);
        instant.setBounds(320, 0, 200, 30);
        instant.setFont(new Font("Times New Roman",Font.BOLD,25));
        
        p2.add(p4);
        p4.setBounds(5,40,775,423);         
        p4.setBackground(new Color(204, 221, 255));
        
        p4.setLayout(new GridLayout(10,1,5,5));
        
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onecode","root","");
                        
            PreparedStatement pst=con.prepareStatement("SELECT fno,firstname,lname,emailid,password,userUid,socialAccount FROM facebook where userUid=? UNION SELECT hno,firstname,lname,emailid,password,userUid,socialAccount FROM hotmail where userUid=? ");
            pst.setInt(1,getUid());
            pst.setInt(2,getUid());            
            
            ResultSet rs=pst.executeQuery();
            
            while(rs.next())
            {
                   String fname=rs.getString("firstname");
                   String lname=rs.getString("lname");
                   String email=rs.getString("emailid");
                   String pass=rs.getString("password");
                   String accountType=rs.getString("socialAccount");
                   int userId=Integer.parseInt(rs.getString(1)); 
                   
                   ++i;
                   
                   p[i]=new JPanel();
                   
                   p4.add(p[i]);
                   p[i].setLayout(null);
                   
                   p[i].setSize(550, 100);
                   
                   l1[i]=new JLabel();
                   l1[i].setText(fname+" "+lname);
                   p[i].add(l1[i]);
                   l1[i].setBounds(20,5,100,30);
                    
                   l2[i]=new JLabel();
                   l2[i].setText(email);
                   p[i].add(l2[i]);
                   l2[i].setBounds(160,5,200,30);
                   
                   
                   if(accountType.equalsIgnoreCase("facebook"))
                   {
                       b2[i]=new JButton("Facebook");
                   }
                   else 
                   {
                       b2[i]=new JButton("Hotmail");
                   }
                   
                   
                   p[i].add(b2[i]);
                   b2[i].setBounds(450,5,130,30);
                   
                   b2[i].addActionListener(new ActionListener()
                   {
                       public void actionPerformed(ActionEvent ae)
                       {
                           try
                           { 
                               int counting=getCount();
                               
                               AESEncryption aes=new AESEncryption(masterkey);
                               String decryptPass=aes.decrypt(pass);
                                 
                               
                               if(accountType.equalsIgnoreCase("facebook"))
                               {
                                   login=lf.getLogin("facebook");
                               }
                               else 
                               {
                                   login=lf.getLogin("hotmail");
                               }      
                               
                               login.login(email, decryptPass, counting);
            
                               counting=counting+1;
                               
                               setCount(counting);
                           }
                           catch(Exception ex)
                           {
                               JOptionPane.showMessageDialog(null, ae);
                           }
                       }
                   });
                   
                   delbtn[i]=new JButton("Delete");
                   p[i].add(delbtn[i]);
                   delbtn[i].setBounds(600, 5, 80, 30);
                   delbtn[i].addActionListener(new ActionListener(){
                   
                       public void actionPerformed(ActionEvent ae)
                       {
                           try
                           {
                               String accType=null;
                               
                               if(accountType.equals("facebook"))
                               {
                                   accType="fno";
                               }
                               else
                               {
                                   accType="hno";
                               }
                               
                               Class.forName("com.mysql.jdbc.Driver");
                               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onecode","root","");
                               
                               PreparedStatement pst=con.prepareStatement("delete from "+accountType+" where "+accType+"=?");
                               pst.setInt(1,userId);
                               
                               int del=pst.executeUpdate();
                               
                               if(del>0)
                               {
                                   JOptionPane.showMessageDialog(f, userId+" "+email+ " is deleted");
                                   
                                   f.dispose();
            
                                   new MainPage(getFirstName(),getLastName(),getMasterKey(),getUid());
                               }
                           }
                           catch(Exception ex)
                           {
                               
                           }
                       }
                   });
                   
                   editbtn[i]=new JButton("Edit");
                   p[i].add(editbtn[i]);
                   editbtn[i].setBounds(690, 5, 80, 30);
                   editbtn[i].addActionListener(new ActionListener(){
                       
                       public void actionPerformed(ActionEvent ae)
                       {
                           new UpdateGUI(email,fname,lname,masterkey,userId,accountType);
                       }
                   });
                   
                   p4.revalidate(); // invoke layout manager
                   p4.repaint(); //paint all components
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Database Error...");
        }
        
        //-------------End of Panel 2--------------------
        
        //-------------Panel 3--------------------
        f.add(p3);
        p3.setBackground(Color.gray);
        p3.setBounds(5,563,785,30);
        
        p3.add(footer);
        //-------------End of Panel 3--------------------
        
        
    }
    
   @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==mfb)
        {
            String masterkey=getMasterKey();
            int uid=getUid();
            
            new FacebookSave(masterkey, uid);
        }
        
        if(ae.getSource()==logoutbtn)
        {
            f.dispose();
            
            new LoginPage();
        }
        
        if(ae.getSource()==mhotmail)
        {
            new HotmailSave(masterkey, uid);
        }
        
        if(ae.getSource()==refreshbtn)
        {
            f.dispose();
            
            new MainPage(getFirstName(),getLastName(),getMasterKey(),getUid());
        }
        
        if(ae.getSource()==mexit)
        {
            System.exit(0);
        }
        
        if(ae.getSource()==minfo)
        {
            new Information();
        }
        
        if(ae.getSource()==mcont)
        {
            JOptionPane.showMessageDialog(f, "Anish Maharjan:- lookout_forme@hotmail.com\n Bipin Shrestha:- bipin_shrestha@gmail.com\n Rohan Shrestha:- rohan_shres@hotmail.com \n Anuj Shrestha:- anuj_shrestha@yahoo.com", "Email Us At",1);
        }
    }
}
