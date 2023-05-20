package foundation;

import java.math.BigInteger;
import util.CryptoTools;

public class A_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		//byte[] ct = CryptoTools.fileToBytes("data/MSG3.ct");
		byte[] ct = CryptoTools.fileToBytes("data/GRADE_MSG1.ct");
		
		for (int a = 1; a < 26; a += (a == 11? 4: 2)) {
		
			int inverse = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26)).intValue();
			
			for (int B = 0; B < 26; B++) {
				
				byte[] temp = new byte[ct.length];
				double dotProduct = 0;
				
				for (int i = 0; i < ct.length; i++) {
					temp[i] = (byte) ((((ct[i] - 'A' - B + 26) % 26) * inverse) % 26 + 'A'); 
				}
				
				int[] tempFreq = CryptoTools.getFrequencies(temp);
				
				for (int j = 0; j < tempFreq.length; j++) {
					dotProduct += tempFreq[j] * CryptoTools.ENGLISH[j];
				}
				
				System.out.printf("Alpha: %2d\t Beta: %2d\t Dot Product: %.2f\n", a, B, dotProduct);
			}
		}
		
		System.out.println();
		
//		int inverse = BigInteger.valueOf(5).modInverse(BigInteger.valueOf(26)).intValue();
//		
//		for (int i = 0; i < ct.length; i++) {
//			System.out.print((char) ((((ct[i] - 'A' - 14 + 26) % 26) * inverse) % 26 + 'A')); // a = 5, B = 14
//		}
		
		int inverse = BigInteger.valueOf(21).modInverse(BigInteger.valueOf(26)).intValue();
		
		for (int i = 0; i < ct.length; i++) {
			System.out.print((char) ((((ct[i] - 'A' - 9 + 26) % 26) * inverse) % 26 + 'A')); // a = 21, B = 9
		}
	}

}
