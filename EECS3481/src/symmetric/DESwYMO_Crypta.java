package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DESwYMO_Crypta {

	public static void main(String[] args) throws Exception {
		
//		byte[] c0 = CryptoTools.hexToBytes("437DBAB5607137A5");
//		byte[] c1 = CryptoTools.hexToBytes("CFC1031114634087");
//		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
//		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		byte[] c0 = CryptoTools.hexToBytes("7AA38A029E773CBB");
		byte[] c1 = CryptoTools.hexToBytes("C188A9FCEADAE99D");
		byte[] c2 = CryptoTools.hexToBytes("A560B784C99AFEF2");
		byte[] ky = CryptoTools.hexToBytes("4F75725269676874");
		byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		
//		cipher.init(Cipher.DECRYPT_MODE, secret);
//		byte[] p0 = cipher.doFinal(c0);
//		YorkModeOfOperation(p0, iv);
//		
//		cipher.init(Cipher.DECRYPT_MODE, secret);
//		byte[] p1 = cipher.doFinal(c1);
//		YorkModeOfOperation(p1, c0);
		
		byte[] t0 = SAEModeOfOperation(c0, iv);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] p0 = cipher.doFinal(t0);
		
		byte[] t1 = SAEModeOfOperation(c1, c0);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] p1 = cipher.doFinal(t1);
		
		byte[] t2 = SAEModeOfOperation(c2, c1);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] p2 = cipher.doFinal(t2);
		
		//System.out.println("PT = " + new String(p0) + new String(p1));
		System.out.println("PT = " + new String(p0) + new String(p1) + new String(p2));
	}
	
	public static byte[] getBitwiseNot(byte[] a) {
		
		byte[] r = new byte[a.length];
		
		for (int i = 0; i < a.length; i++) {
			r[i] = (byte) (a[i] ^ 255);
		}
		
		return r;
	}
	
	public static void YorkModeOfOperation(byte[] a, byte[] iv) {
		
		byte[] notIv = getBitwiseNot(iv);
		
		for (int i = 0; i < a.length; i++) {
			a[i] ^= notIv[i];
		}
	}
	
	public static byte[] SAEModeOfOperation(byte[] a, byte[] iv) {
		
		byte[] r = new byte[a.length];
		
		for (int i = 0; i < a.length; i++) {
			r[i] = (byte) (a[i] ^ iv[i]);
		}
		
		return r;
	}
}
