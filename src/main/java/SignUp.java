/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anish Maharjan
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class SignUp implements ActionListener
{
    JLabel title,username,pass1label,fname,lname,email;
    JTextField usertext,fnametxt,lnametxt,emailtxt;
    JPasswordField pass1txt,pass2txt;
    JButton signup;
    JFrame f;
    JPanel p;
    
    String msg;
    
    Connection con;
    PreparedStatement pst;
    
    public SignUp()
    {
        f=new JFrame("Sign Up");
        f.setSize(500,450);
        
        p=new JPanel();
        
        title=new JLabel("-- Sign Up --");
        username=new JLabel("Username");
        pass1label=new JLabel("Master Key");
        fname=new JLabel("First Name");
        lname=new JLabel("Last Name");
        email=new JLabel("Email-id");
                
        usertext=new JTextField();
        fnametxt=new JTextField();
        lnametxt=new JTextField();
        emailtxt=new JTextField();
        
        pass1txt=new JPasswordField();
        pass2txt=new JPasswordField();
        
        signup=new JButton("Sign Up");               
               
        f.add(p);
        p.setBackground(new Color(109,110,115));
        
        p.setLayout(null);
        
        p.add(title);
        title.setBounds(190,50,200,40);
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        
        p.add(username);
        username.setBounds(70,100,150,25);        
        p.add(usertext);
        usertext.setBounds(200,100,250,25);
        
        p.add(pass1label);
        pass1label.setBounds(70,135,150,25);        
        p.add(pass1txt);
        pass1txt.setBounds(200,135,250,25);
        
       
        p.add(fname);
        fname.setBounds(70,170,150,25);        
        p.add(fnametxt);
        fnametxt.setBounds(200,170,250,25);
        
        p.add(lname);
        lname.setBounds(70,205,150,25);        
        p.add(lnametxt);
        lnametxt.setBounds(200,205,250,25);
        
        p.add(email);
        email.setBounds(70,240,150,25);        
        p.add(emailtxt);
        emailtxt.setBounds(200,240,250,25);
        
        p.add(signup);
        signup.setBounds(200,275,250,25);  
        signup.addActionListener(this);       
        
        
        f.setVisible(true);
        f.setLocation(450,110);
        f.setResizable(false);
                
    }
    
    
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==signup)
        {
            PinCodeInputDialogue pcid=new PinCodeInputDialogue();
            int pin=pcid.pinCode();
            
            String pincode=Integer.toString(pin);
            
            AESEncryption aes;
            String encryptKey=null;
                
            if(pin!=0)
            {
                try
                {
                    aes=new AESEncryption(pincode);
                    encryptKey=aes.encryt(pass1txt.getText().toString());
                }
                catch(Exception ex){}
            }
        
            try
            {
                ConnectionDB con=new ConnectionDB();
    
                pst=con.con.prepareStatement("insert into one_code_user (username,masterkey,fname,lname,email) values(?,?,?,?,?)");
                pst.setString(1,usertext.getText());
                pst.setString(2, encryptKey);
                pst.setString(3,fnametxt.getText());
                pst.setString(4, lnametxt.getText());
                pst.setString(5, emailtxt.getText());
                    
                int i= pst.executeUpdate();
                    
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"User Added Successfully");
                    usertext.setText("");
                    pass1txt.setText("");
                    fnametxt.setText("");
                    lnametxt.setText("");
                    emailtxt.setText("");                        
                }                   
   
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex);
            }

        }
    }

}
