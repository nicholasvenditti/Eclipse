package hash;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class GRADE_AES_MAC {

	public static void main(String[] args) throws Exception {
					
		byte[] m = "No one can make you feel inferior without your consent.".getBytes();
		byte[] K = "defensethroughou".getBytes();
	
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(m);
		
		Key secret = new SecretKeySpec(K, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] mac = cipher.doFinal(hash);
		
		System.out.println(CryptoTools.bytesToHex(mac));
	}

}
