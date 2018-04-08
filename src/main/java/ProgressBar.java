/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Anish Maharjan
 */
public class ProgressBar 
{
    static JFrame f;
    static JProgressBar bar;
    JLabel label1,label2,label3;
    JPanel p1,p2;
    static int i=0;
    public ProgressBar()
    {
        f=new JFrame("One Code");
        f.setSize(1012,492);       
        f.setBackground(Color.GRAY);    

        JPanel p1=new JPanel();
        JPanel p2=new JPanel();        
        
        bar=new JProgressBar(0,100);
        label1=new JLabel();
        label2=new JLabel("One Code,No Hassle");
        label3=new JLabel("Loading");
        
        ImageIcon img = new ImageIcon(new ImageIcon("Images/one_coder1.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        
        f.setLayout(null);
        
        //----------------------------------------------------p1
        f.add(p1);     
        p1.setBounds(5, 5, 995, 350);
        p1.setBackground(new Color(109,110,115));
        
        p1.setLayout(null);
        
        p1.add(label1);
        label1.setBounds(400,60,200,200);
        label1.setIcon(img);
        
        p1.add(label2);
        label2.setBounds(415,265,200,50);
        label2.setFont(new Font("Arial", Font.PLAIN,18));
        
        //----------------------------------------------------p1
        
        
        //-----------------------------------------------------p2        
        
        f.add(p2);     
        p2.setBounds(5, 358, 995, 100);
        p2.setBackground(new Color(109,110,115));
        
        p2.setLayout(null);
        
        p2.add(label3);
        label3.setBounds(465,5,100,25);
        label3.setFont(new Font("Arial", Font.BOLD,15));
        
        p2.add(bar);        
        bar.setValue(0);
        bar.setStringPainted(true);
        bar.setOrientation(SwingConstants.HORIZONTAL);
        bar.setBounds(5, 30, 975, 40);
        
        //-----------------------------------------------------p2      
        
        
        f.setVisible(true);
        f.setLocation(200,110);
        f.setResizable(false);  
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
    
    public static void bar()
    {
        while(i<=100)
        {
            bar.setValue(i);
            i=i+5;
            try
            {
                Thread.sleep(200);                
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            
            if(i==100)
            {   
                f.dispose();
                new LoginPage();
            }
        }
    }
   
    public static void main(String[] args) 
    {
        new ProgressBar();
        
        Thread t=new Thread(){
          public void run()
          {
              bar();
          }
        };
        
        t.start();
    }
    
}
