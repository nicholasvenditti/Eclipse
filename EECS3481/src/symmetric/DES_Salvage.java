package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_Salvage {

	public static void main(String[] args) throws Exception {
		
		byte[] c1 = CryptoTools.hexToBytes("4E51297B424F90D8");
		byte[] c2 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
		byte[] ky = "CSE@YORK".getBytes();
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(c1);
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] p2 = cipher.doFinal(c2);
		
		System.out.println("P2 = " + new String(p2)); // Can only salvage P2
	}

}
