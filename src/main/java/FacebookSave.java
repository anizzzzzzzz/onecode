/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

public class FacebookSave implements ActionListener
{
    JLabel title,username,pass1label,pass2label,fname,lname,email;
    JTextField usertext,fnametxt,lnametxt,emailtxt;
    JPasswordField pass1txt,pass2txt;
    JButton signup,cancel;
    JFrame f;
    JPanel p;
    
    PreparedStatement pst;
    
    String key=null;
    int uid=0;
    
    String encryptPass=null;
    
    public FacebookSave(String key,int uid)
    {
        f=new JFrame("Sign Up");
        f.setSize(500,420);
        
        p=new JPanel();
        
        title=new JLabel("-- Add Facebook Account --");
        username=new JLabel("Email-Id");
        pass1label=new JLabel("Password");
        pass2label=new JLabel("Confirm Password");
        fname=new JLabel("First Name");
        lname=new JLabel("Last Name");        
        
        usertext=new JTextField();
        fnametxt=new JTextField();
        lnametxt=new JTextField();
                
        pass1txt=new JPasswordField();
        pass2txt=new JPasswordField();
        
        signup=new JButton("Sign Up");               
        cancel=new JButton("Cancel");               
               
        f.add(p);
        p.setBackground(new Color(109,110,115));
        
        p.setLayout(null);
        
        p.add(title);
        title.setBounds(120,50,300,40);
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        
        p.add(username);
        username.setBounds(70,100,150,25);        
        p.add(usertext);
        usertext.setBounds(200,100,250,25);
        
        p.add(pass1label);
        pass1label.setBounds(70,135,150,25);        
        p.add(pass1txt);
        pass1txt.setBounds(200,135,250,25);
        
        p.add(pass2label);
        pass2label.setBounds(70,170,150,25);        
        p.add(pass2txt);
        pass2txt.setBounds(200,170,250,25);
        
        p.add(fname);
        fname.setBounds(70,205,150,25);        
        p.add(fnametxt);
        fnametxt.setBounds(200,205,250,25);
        
        p.add(lname);
        lname.setBounds(70,240,150,25);        
        p.add(lnametxt);
        lnametxt.setBounds(200,240,250,25);
        
        p.add(signup);
        signup.setBounds(70,275,200,25); 
        signup.addActionListener(this);
        
        p.add(cancel);
        cancel.setBounds(270,275,200,25);      
        
        this.key=key;
        this.uid=uid;
        
        f.setVisible(true);
        f.setLocation(450,110);
        f.setResizable(false);        
    }
    
   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==signup)
        {
            String pass1=pass1txt.getText().toString();
            String pass2=pass2txt.getText().toString();
            
            String email=usertext.getText();
            String fname=fnametxt.getText();
            String lname=lnametxt.getText();           
            String socialAccount="facebook";
            
            if(pass1.equals(pass2))
            {
                try
                {
                    AESEncryption aes=new AESEncryption(key);
                    
                    encryptPass=aes.encryt(pass1);
                    
                    ConnectionDB con=new ConnectionDB();
                    
                    pst=con.con.prepareStatement("insert into facebook (emailid,password,firstname,lname,userUid,socialAccount) values(?,?,?,?,?,?)");
                    pst.setString(1,email);
                    pst.setString(2, encryptPass);
                    pst.setString(3,fname);
                    pst.setString(4, lname);
                    pst.setInt(5, uid);
                    pst.setString(6,socialAccount);
                    
                    int i= pst.executeUpdate();
                    
                    if(i>0)
                    {
                        JOptionPane.showMessageDialog(null,"Record Added Successfully");
                        usertext.setText("");
                        pass1txt.setText("");
                        pass2txt.setText("");
                        fnametxt.setText("");
                        lnametxt.setText("");                                              
                    }                   
                    
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
            }
        }
    }
    
}

