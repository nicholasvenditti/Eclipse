package foundation;

import util.CryptoTools;

public class V_Crypta {

	public static void main(String[] args) throws Exception {
		
		//byte[] ct = CryptoTools.fileToBytes("data/MSG4.ct");
		byte[] ct = CryptoTools.fileToBytes("data/TEST_MSG1.ct");
		
		for (int K = 2; K < 50; K++) {
			
			byte[] temp = getTemp(ct, K, 0);
			
			System.out.printf("Key length: %2d\t IC: %.4f\n", K, CryptoTools.getIC(temp)); // K = 9
		}
		
		System.out.println();
		
		int K = 10;
		
		for (int i = 0; i < K; i++) {
			
			byte[] temp = getTemp(ct, K, i);
			
			for (int k = 0; k < temp.length; k++) {
				temp[k] = (byte) ((temp[k] - 'A' + 78) % 26 + 'A');
			}
			
			int[] tempFreq = CryptoTools.getFrequencies(temp);
			
			System.out.println("For key letter " + (i + 1) + ":");
			
			for (int j = 0; j < tempFreq.length; j++) {
				System.out.printf("Frequency of %c: %.2f\n", (char) (j + 'A'), tempFreq[j] / (double) temp.length);
			}
			
			System.out.println();
		}
		
		for (int j = 0; j < ct.length; j++) {
			
			int shift = 0;
			
			switch (j % K) {
			case 0:
				//shift = 4;
				shift = 0;
				break;
			case 1:
				//shift = 11;
				shift = 15;
				break;
			case 2:
				//shift = 0;
				shift = 17;
				break;
			case 3:
				//shift = 1;
				shift = 14;
				break;
			case 4:
				//shift = 14;
				shift = 23;
				break;
			case 5:
				//shift = 17;
				shift = 8;
				break;
			case 6:
				//shift = 0;
				shift = 12;
				break;
			case 7:
				//shift = 19;
				shift = 0;
				break;
			case 8:
				//shift = 4;
				shift = 19;
				break;
			case 9:
				shift = 4;
				break;
			}	
			
			System.out.print((char) ((ct[j] - 'A' - shift + 26) % 26 + 'A')); // Key = ELABORATE	
		}
	}

	private static byte[] getTemp(byte[] a, int step, int i) {
		
		byte[] result = new byte[(a.length / step) + 1];
		
		for (int j = 0; i < a.length; i += step, j++) {
			result[j] = a[i];
		}
		
		return result;
	}
}
