package hash;

import java.security.MessageDigest;

import util.CryptoTools;

public class FindPreImage {

	public static void main(String[] args) throws Exception {
		
		String psw = "idk";
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(psw.getBytes());
		System.out.println(CryptoTools.bytesToHex(digest));
	}

}
