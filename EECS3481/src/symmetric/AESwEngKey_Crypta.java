package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AESwEngKey_Crypta {

	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
		byte[] ky = "DO NOT TELL EVE!".getBytes();
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] pt = cipher.doFinal(ct);
		
		System.out.println("PT = " + new String(pt));
	}

}
