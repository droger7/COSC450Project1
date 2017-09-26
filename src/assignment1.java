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

import java.util.Scanner;

public class assignment1 {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter a single hex value: ");
		String H = scan.next();
		
		System.out.println("Enter a small string: ");
		String T=scan.next();
		
		//convert hex to decimal
		int Hdecimal = Integer.parseInt(H,16);
		System.out.println("Hex value in decimal is: "+Hdecimal);
		
		//Padding for T
		String paddedString = "00000000000000000000000000000000";
		T = T + paddedString.substring(T.length());

		//K Creation
		String K = H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H+H;
		
		//Encryption
		String S = T+K; 
		System.out.println("T="+T);
		System.out.println("S="+S);
		System.out.println("K="+K);
		
		//Print out of Hex Encrypted 
		

		
	}
	

}
