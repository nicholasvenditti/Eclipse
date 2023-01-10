package model;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

	private String hashpass;
	private int salt;
	
	public Password(String pass) {
		this.salt= makeSalt();
		this.hashpass= getSHA512(pass, salt);
	}
	
	private int makeSalt () {
		byte[] salt= null;
		try {
			SecureRandom sr= SecureRandom.getInstance("SHA1PRNG");
			salt= new byte[4];
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int saltInt= ByteBuffer.wrap(salt).getInt();
		return saltInt;
	}
	
	public static String getSHA512 (String pass, int salt) {
		String hashpass= null;
		try {
			MessageDigest md= MessageDigest.getInstance("SHA-512");
			md.update(ByteBuffer.allocate(4).putInt(salt).array());
			byte[] bytes= md.digest(pass.getBytes());
			StringBuilder sb= new StringBuilder();
			for (int i=0; i<bytes.length; i++)
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			hashpass= sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashpass;
	}

	/**
	 * @return the hashpass
	 */
	public String getHashpass() {
		return hashpass;
	}

	/**
	 * @return the salt
	 */
	public int getSalt() {
		return salt;
	}
	
	public String toString() {
		return this.hashpass + " (" + this.salt + ")";
	}
}
