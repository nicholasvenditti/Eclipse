package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_Crypta {

	public static void main(String[] args) throws Exception {
		
		//byte[] hex = CryptoTools.fileToBytes("data/MSG5.ct");
		byte[] hex = CryptoTools.fileToBytes("data/GRADE_MSG2.ct");
		byte[] ct = CryptoTools.hexToBytes(new String(hex));
		//byte[] ky = "FACEBOOK".getBytes();
		byte[] ky = CryptoTools.hexToBytes("636F6E74696E7565");
		byte[] notKy = new byte[ky.length];
		
		for (int i = 0; i < ky.length; i++) {
			notKy[i] = (byte) (ky[i] ^ 255);
		}
		
		Key secret1 = new SecretKeySpec(ky, "DES");
		Key secret2 = new SecretKeySpec(notKy, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		
		cipher.init(Cipher.DECRYPT_MODE, secret2);
		byte[] temp = cipher.doFinal(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret1);
		byte[] pt = cipher.doFinal(temp);
		
		System.out.println("PT = " + new String(pt));
	}

}
