package hash;

import java.security.MessageDigest;

import util.CryptoTools;

public class SHA1_HMAC {

	public static void main(String[] args) throws Exception {

//		byte[] K = "This is an ultra-secret key".getBytes();
//		byte[] m = "Mainly cloudy with 40 percent chance of showers".getBytes();
//		MessageDigest md = MessageDigest.getInstance("SHA-1");
//		int blockSize = 64;
//		
//		byte[] opad = CryptoTools.hexToBytes("5c");
//		byte[] ipad = CryptoTools.hexToBytes("36");
////		byte[] opad = CryptoTools.hexToBytes("36");
////		byte[] ipad = CryptoTools.hexToBytes("5c");
//		
//		byte[] oKeyPad = new byte[blockSize];
//		byte[] iKeyPad = new byte[blockSize];
//		
//		if (K.length > blockSize) {
//			K = md.digest(K);
//		}
//		
//		if (K.length < blockSize) {
//			K = Pad(K, blockSize);
//		}
//		
//		for (int i = 0; i < blockSize; i++) {
//			oKeyPad[i] = (byte) (K[i] ^ opad[0]);
//			iKeyPad[i] = (byte) (K[i] ^ ipad[0]);
//		}
//
//		System.out.println(CryptoTools.bytesToHex(md.digest(concatenation(oKeyPad, md.digest(concatenation(iKeyPad, m))))));
		
		byte[] K = CryptoTools.hexToBytes("69B045A456AB2435BDEFA4");
//		byte[] m = "Meet at 6:30 pm on 20.".getBytes(); // No
		byte[] m = "Buy 270 RY at MarketP.".getBytes(); // Yes
//		byte[] m = "Temperature 28 in YYZ.".getBytes(); // No
//		byte[] m = "Approach runway 24 SW.".getBytes(); // No
		byte[] Km = concatenation(K, m);
		byte[] KmK = concatenation(Km, K);
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hash = md.digest(KmK);
		
		System.out.println(CryptoTools.bytesToHex(hash));
	}
	
	public static byte[] Pad(byte[] k, int bs) {
		
		byte[] result = new byte[bs];
		
		for (int i = 0; i < bs; i++) {
			
			if (i < k.length) {
				result[i] = k[i];
			} else {
				result[i] = 0;
			}	
		}
		
		return result;
	}
	
	public static byte[] concatenation(byte[] a, byte[] b) {
		
		byte[] result = new byte[a.length + b.length];
		
		for (int i = 0; i < result.length; i++) {
			
			if (i < a.length) {
				result[i] = a[i];
			} else {
				result[i] = b[i - a.length];
			}	
		}
		
		return result;
	}

}
