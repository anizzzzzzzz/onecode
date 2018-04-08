/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Anish Maharjan
 */
public class PinCodeInputDialogue {
    
    public int pinCode() 
    {
   
    int loopNumber = -1;
    int pin=-1;
    int code=0;

    while (loopNumber == -1) 
    { 
        JPasswordField pf = new JPasswordField();
        
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter 4 digit pin code", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
              
        if (okCxl == JOptionPane.OK_OPTION) 
        {
                String password = new String(pf.getPassword());
             
                if(password.length()==4)
                {
                    loopNumber = stringToInt(password);  
                    
                    pin=loopNumber;
                }
                else if(password.length()>=4)
                {
                    JOptionPane.showMessageDialog(null,"Pin cannot have digit more than 4");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Pin cannot have digit less than 4");
                }
         }
        else
        {
            loopNumber=1;
        }
        
        if(pin!=-1)
        {
            code= pin;
        }       
    }
    
    return code;
}

private static int stringToInt(String string) {

    try {
        return Integer.parseInt(string);
    }
    catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,"Not a number! Please enter a number.");
        return -1;
    }
}
    
}
