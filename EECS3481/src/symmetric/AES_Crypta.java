package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_Crypta {

	public static void main(String[] args) throws Exception {
		
//		byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
//		byte[] ky = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
//		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");
		byte[] ky = CryptoTools.hexToBytes("444F204E4F542054454C4C2045564521");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] pt = cipher.doFinal(ct);
		
		System.out.println("PT = " + new String(pt));
	}

}
