package com.revature.password;

import java.util.Random;

public class passwordJenn {
	
	    // This our Password generating method
	    // We use static here, so that we don't have to
	    // make any object for it
	   public static String jenPassword()
	    {
	        System.out.println("Generating password using random() : ");
	        System.out.print("Your new password is : ");
	        
	        //How long we want the password to be
	        int len = 10;
	        
	        // A strong password has Cap_chars, Lower_chars,
	        // numeric value and symbols. So we are using all of
	        // them to generate our password
	        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
	        String numbers = "0123456789";
	                String symbols = "!@#$%^&*_=+-/.?<>)";
	 
	 
	        String values = Capital_chars + Small_chars +
	                        numbers + symbols;
	 
	        Random rndm_method = new Random();
	 
	        String password = "";
	 
	        for (int i = 0; i < len; i++)
	        {
	            // here we add a random character from all possible characters to our password
	            password += values.charAt(rndm_method.nextInt(values.length()));
	 
	        }
	        
	        return password;
	    }
	}

