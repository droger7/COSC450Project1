/**
 * Created by:
 * David Rogers
 * Ben Alden
 * Garrett Bates
 * Ryan Oechler
 * Sidney Heier
 * Shovon Chowdhury
 */

/*
Write a Java socket program to do a Diffie-Hellman (DH) key exchange as follows.
The client A and server B must perform the given actions in order as specified below:
*/

import java.math.BigInteger;
import java.util.Scanner;

    public class RogersP2 {
        public static void main(String args[]) {

            Scanner stdin = new Scanner(System.in);
            BigInteger q;

            // Asks user at A to enter prime q as a string
            System.out.println("Enter the approximate value of q you want (must be prime).");
            String ans = stdin.next();
            q = getNextPrime(ans);//Test if alpha is a primitive root; if not, reenter alpha and test
            System.out.println("Your prime is " + q + ".");

            // Asks user at A to enter a possible primitive root of q (alpha) as a string
            System.out.println("Now, enter a number in between 2 and q-1 (primitive root).");
            BigInteger g = new BigInteger(stdin.next());

            // Asks user at A to enter A’s private key X(A) as an integer
            System.out.println("Person A: enter your private key now.");
            BigInteger Xa = new BigInteger(stdin.next());

            // Make A's calculation.
            BigInteger Ya = g.modPow(Xa, q);

            // Uses Diffie-Hellman to generate A’s public key Y(A) and prints Y(A)
            // Uses the socket to send q, alpha, A’s public key Y(A), and A’s email address to the server B
            // Uses the socket to receive the information sent by the server B

            System.out.println("\nPerson A sends public key to person B " + Ya	+ ".");

            // Ask user at B to enter B’s private key X(B) as a string
            System.out.println("\nPerson B: enter your secret number now.");
            BigInteger Xb = new BigInteger(stdin.next());

            // Make B's calculation.
            BigInteger Yb = g.modPow(Xb, q);

            // This is the value that will get sent from B to A.
            // This value does NOT compromise the value of Xb easily.
            System.out.println("Person B sends public key to person A " + Yb	+ ".");

            // Uses the socket to send server B’s public key Y(B) and email address to the client A
            BigInteger ACalculatesKey = Yb.modPow(Xa, q);

            // Uses the socket to send q, alpha, A’s public key Y(A), and A’s email address to the server B
            BigInteger BCalculatesKey = Ya.modPow(Xb, q);

            // Uses Diffie-Hellman to generate the shared key K and prints K.
            System.out.println("\nA takes " + Yb + " raises it to the power " 	+ Xa + " mod " + q);
            System.out.println("The Shared Key A calculates is " + ACalculatesKey + ".");

            // Uses Diffie-Hellman to generate the shared key K and prints K.
            System.out.println("\nB takes " + Ya + " raises it to the power " + Xb + " mod " + q);
            System.out.println("The Shared Key B calculates is " + BCalculatesKey 	+ ".");

        }

        public static BigInteger getNextPrime(String ans) {

            BigInteger test = new BigInteger(ans);
            while (!test.isProbablePrime(99))
                //		test = test.add(one);
                test = test.add(BigInteger.ONE);
            return test;
        }

    }


