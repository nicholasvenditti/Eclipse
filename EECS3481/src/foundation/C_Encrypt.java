package foundation;

import util.CryptoTools;

public class C_Encrypt {

	public static void main(String[] args) throws Exception {
		
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.pt");
		byte[] pt = CryptoTools.clean(raw);
		CryptoTools.bytesToFile(pt, "data/MSG1.clean");
		
		int key = 19;
		byte[] ct = new byte[pt.length];
		
		for (int i = 0; i < pt.length; i++) {
			ct[i] = (byte) ((pt[i] - 'A' + key) % 26 + 'A');
		}
		
		CryptoTools.bytesToFile(ct, "data/MSG1.ct");
		System.out.println("MD5: " + CryptoTools.getMD5(ct));
		System.out.println("IC of pt: " + CryptoTools.getIC(pt));
		System.out.println("IC of ct: " + CryptoTools.getIC(ct));
	}

}
