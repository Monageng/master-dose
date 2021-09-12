package co.za.master.dose.samples;

import co.za.master.dose.utils.PasswordUtils;

public class ProtectUserPassword {

	public static void main(String[] args) {
		 	String myPassword = "admin";
	        
	        // Generate Salt. The generated value can be stored in DB. 
	        String salt = PasswordUtils.getSalt(30);
	        
	        // Protect user's password. The generated value can be stored in DB.
	        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, "aHFoLxRa4K6uxhz2BnX4DqvkMeOSpQ");
	        
	        // Print out protected password 
	        System.out.println("My secure password = " + mySecurePassword);
	        System.out.println("Salt value = " + salt);
	}

}

//z0wAUhGsJD4Ajg7+vrOuhYffpRKM6LY/arHqzlpFOW8=