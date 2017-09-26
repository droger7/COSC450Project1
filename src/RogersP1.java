/**
 * Created by:
 * David Rogers
 * Ben Alden
 * Garrett Bates
 * Ryan Oechler
 * Sidney Heier
 * Shovon Chowdhury
 *
 */

/**
 * The program first uses AES in counter mode as follows:
Encrypts the 16-byte value S consisting of T padded on the right with zeros 
(if necessary) with the key K, where K is the 16-byte value consisting of H 
repeated 32 times, and the counter is initialized to the 16-byte value 
consisting of all zeros. It then prints the encrypted result in hex.
Next, it authenticates the original 16-byte value S above using HMAC SHA256 
with the same key K above and with the IV for HMAC SHA 256 taken to be the 
32-byte value S concatenated with itself. It then prints the final computed 
HMAC result in hex.
 */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import java.io.*;
import java.util.*;

public class RogersP1 {

	public static void main(String[] args) throws Exception {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter a single hex value: ");
		int H = Integer.parseInt(scan.next(), 16);
		
		System.out.println("Enter a small string (no spaces): ");
		String T=scan.next();
		String paddedString = "0000000000000000";
		T = T + paddedString.substring(T.length());
		byte S[] = T.getBytes();
		
		//K Creation
		byte [] K = new byte[16];
		for (int i = 0; i < 16; i++)
		{
			K[i] = (byte)(H * 17); 
		}
		
		//Counter Creation
		byte[] C = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		//AES Counter Mode Encryption 
		byte [] encBytes = encryptAES(K, S, C);
		String encStr = new String(encBytes);		
		
		System.out.println();
		System.out.println("Hex value for key :  " + Integer.toString(H));
		System.out.println("Plaintext string  :  " + T);		
		System.out.println("Encrypted string  :  " + encStr);
		
	}
	
	public static byte[] encryptAES(byte key[], byte data[], byte counter[]) throws Exception
    {
           SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

           // Perform AES encryption on the counter counter
           Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
           cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
           byte [] encryptedCounter = cipher.doFinal(counter);
           byte [] encryptedData = new byte[data.length];
           
           // XOR the data with the encrypted counter
           for (int i = 0; i < data.length; i++)
           {
        	   encryptedData[i] = (byte)(data[i]^encryptedCounter[i]);
           }
           
           return encryptedData;
    }
}



