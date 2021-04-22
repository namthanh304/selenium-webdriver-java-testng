package webdriver;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random email = new Random();
		int length = 7;
	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = email.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	     
	    }
	    String randomString = sb.toString();
	    String tmp_email = randomString + "@gmail.com";
	}
}
