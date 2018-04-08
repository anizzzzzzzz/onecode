import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anish Maharjan
 */

public class AESEncryption 
{
        private static final String ALGO="AES";
        private byte[] keyValue;
        
        public AESEncryption(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException
        {
            keyValue=key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyValue = sha.digest(keyValue);
            keyValue = Arrays.copyOf(keyValue, 16);
        }
        
        public String encryt(String Data) throws Exception
        {
            Key key=generateKey();
            Cipher c=Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal=c.doFinal(Data.getBytes());
            String encryptedValue= new BASE64Encoder().encode(encVal);
            return encryptedValue;
        }
        
        public String decrypt(String encryptedData) throws Exception
        {
            Key key=generateKey();
            Cipher c= Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue=new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue=c.doFinal(decordedValue);
            String decryptedValue=new String(decValue);
            return decryptedValue;
        }
        
        private Key generateKey() throws Exception
        {
            Key key=new SecretKeySpec(keyValue,ALGO);
            return key;
        }       
        
}
