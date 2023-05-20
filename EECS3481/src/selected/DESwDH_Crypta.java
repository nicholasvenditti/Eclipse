package selected;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DESwDH_Crypta {

	public static void main(String[] args) throws Exception {
		
		BigInteger p = new BigInteger("1426978031065901624399459");  //prime modulus
		BigInteger aX = new BigInteger("94710650508083161491791");  //Alice's DH private
		BigInteger bY = new BigInteger("170916825306137482315912"); //Bob's DH public
		String c = "7B6D626066B5444BE1A0D3CD0085C965"; //The received DES/ECB/PKCS5Padding ciphertext 0x
		
		byte[] K = (bY.modPow(aX, p)).toByteArray();
		byte[] ct = CryptoTools.hexToBytes(c);
		byte[] ky = new byte[8];
		
		for (int i = 0; i < 8; i++) {
			ky[i] = K[i];
		}
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] pt = cipher.doFinal(ct);
		
		System.out.println("PT = " + new String(pt));
	}

}
