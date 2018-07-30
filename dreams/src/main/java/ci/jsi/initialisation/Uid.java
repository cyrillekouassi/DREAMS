package ci.jsi.initialisation;


import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class Uid {

	private static final String allowedLetters[] = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789"};
	private static final String allowedChars = String.join("",allowedLetters);
	private static SecureRandom rnd = new SecureRandom();
	
	public Uid() {
		
	}

	public String getUid() {
		/*for(int i = 0;i<50;i++)
			System.out.println(randomString(11));*/
		return randomString(11);
	}
	
	private String randomString(int len){
		//System.out.println(allowedChars);
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(allowedChars.charAt(rnd.nextInt(allowedChars.length())));
		//System.out.println(sb);
		return sb.toString();
	}
}
