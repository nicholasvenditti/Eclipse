package foundation;

import util.CryptoTools;

public class C_Crypta {

	public static void main(String[] args) throws Exception {

		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		int[] ctFreq = CryptoTools.getFrequencies(ct);
		
		for (int i = 0; i < ctFreq.length; i++) {
			
			double tempFreq = ctFreq[i] / (double) ct.length;
			
			System.out.printf("Frequency of %c: %.2f\n", (char) (i + 'A'), tempFreq);
		}
		
		System.out.println();
		
		for (int i = 0; i < ct.length; i++) {
			System.out.print((char) ((ct[i] - 'A' - 22 + 26) % 26 + 'A')); // Key = 22
		}
	}

}
