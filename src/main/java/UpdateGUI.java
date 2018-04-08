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

public class UpdateGUI 
{
    JLabel title,username,pass1label,fname,lname,pass2label;
    JTextField usertext,fnametxt,lnametxt;
    JPasswordField pass1txt,pass2txt;
    JButton edit;
    JFrame f;
    JPanel p;
    
    String msg;
    
    Connection con;
    PreparedStatement pst;
   
    
    public UpdateGUI(String user,String first,String last,String masterkey,int accNo, String accountType)
    {
        f=new JFrame("Edit");
        f.setSize(500,450);
        
        p=new JPanel();
        
        title=new JLabel("-- Edit --");
        username=new JLabel("Username");
        pass1label=new JLabel("New Password");
        pass2label=new JLabel("Confirm Password");
        fname=new JLabel("Frist Name");
        lname=new JLabel("Last Name");
                
        usertext=new JTextField();
        fnametxt=new JTextField();
        lnametxt=new JTextField();
        
        pass1txt=new JPasswordField();
        pass2txt=new JPasswordField();
        
        edit=new JButton("Edit");               
               
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
        usertext.setText(user);
        
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
        fnametxt.setText(first);
        
        p.add(lname);
        lname.setBounds(70,240,150,25);        
        p.add(lnametxt);
        lnametxt.setBounds(200,240,250,25);
        lnametxt.setText(last);
        
        p.add(edit);
        edit.setBounds(200,275,250,25);  
        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String pass1=pass1txt.getText().toString();
                String pass2=pass2txt.getText().toString();
                
                if(pass1.equals(pass2))
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
                   
                    if(pass1.equals(pass2))
                    {
                        try
                        {
                            AESEncryption aes=new AESEncryption(masterkey);
                            
                            String encryptPass=aes.encryt(pass1);
                        
                            ConnectionDB con=new ConnectionDB();
                        
                            pst=con.con.prepareStatement("update "+accountType+" set emailid=?,password=?,firstname=?,lname=? where "+accType+" =?");
                            pst.setString(1,usertext.getText());
                            pst.setString(2, encryptPass);
                            pst.setString(3,fnametxt.getText());
                            pst.setString(4, lnametxt.getText());
                            pst.setInt(5,accNo);
                                
                            int i= pst.executeUpdate();
                    
                            if(i>0)
                            {
                                JOptionPane.showMessageDialog(null,"Record Updated Successfully");
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
            else
            {
                JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
            }
            }
        });       
        
                
        f.setVisible(true);
        f.setLocation(450,110);
        f.setResizable(false);
                
    }
}
