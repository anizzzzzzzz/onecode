/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Anish Maharjan
 */
public class Information implements ActionListener
{
    JFrame f;
    JTextPane ta;
    JButton btn;
    JPanel p;
    public Information()
    {
        f=new JFrame("One Code");
        f.setSize(500,420);
        
        p=new JPanel();
        ta=new JTextPane();
        btn=new JButton("Ok");
        
        f.add(p);
        
        p.setLayout(null);
        p.setBackground(new Color(180,162,251));
        
        p.add(ta);
        ta.setBounds(5,5,475,330);
        fileReading();
        ta.setEditable(false);
        ta.setBackground(new Color(180,162,251));
        ta.setFont(new Font("Sans Serif", Font.BOLD, 12));
        
        p.add(btn);
        btn.setBounds(380,340,100,30);
        btn.addActionListener(this);
        
        f.setVisible(true);
        f.setLocation(450,110);
        f.setResizable(false); 
    }
    
   
    public void fileReading() 
    {
        try
        {
            File file=new File("Images/onecodeinfo.txt");
            FileReader fr=new FileReader(file);
            
            while(fr.read() != -1 )
            {
                ta.read(fr,null);
            }
            
            fr.close();
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }
        catch(IOException io)
        {
            System.out.println(io);
        }
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn)
        {
            f.dispose();
        }
    }
}
