package foundation;

import util.CryptoTools;

public class C_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		
		for (int i = 1; i < 26; i++) {
			
			byte[] temp = new byte[ct.length];
			double dotProduct = 0;
			
			for (int j = 0; j < ct.length; j++) {
				temp[j] = (byte) ((ct[j] - 'A' - i + 26) % 26 + 'A'); 
			}
			
			int[] tempFreq = CryptoTools.getFrequencies(temp);
			
			for (int k = 0; k < tempFreq.length; k++) {
				dotProduct += tempFreq[k] * CryptoTools.ENGLISH[k];
			}
			
			System.out.printf("Shift: %2d\t Dot Product: %.2f\n", i, dotProduct);
		}
		
		System.out.println();
		
		for (int i = 0; i < ct.length; i++) {
			System.out.print((char) ((ct[i] - 'A' - 22 + 26) % 26 + 'A')); // Key = 22
		}
	}

}
