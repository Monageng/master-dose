package co.za.master.dose.samples;

import co.za.master.dose.utils.PasswordUtils;

public class VerifyProvidedPassword {

	public static void main(String[] args) {
		// User provided password to validate
        String providedPassword = "admin";
                
        // Encrypted and Base64 encoded password read from database
        String securePassword = "z0wAUhGsJD4Ajg7+vrOuhYffpRKM6LY/arHqzlpFOW8=";
        
        // Salt value stored in database 
        String salt = "aHFoLxRa4K6uxhz2BnX4DqvkMeOSpQ";
        
        boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
        
        if(passwordMatch) 
        {
            System.out.println("Provided user password " + providedPassword + " is correct.");
        } else {
            System.out.println("Provided password is incorrect");
        }
		
	}

}

//z0wAUhGsJD4Ajg7+vrOuhYffpRKM6LY/arHqzlpFOW8=