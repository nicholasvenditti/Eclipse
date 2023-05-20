package hash;

import java.math.BigInteger;

public class RSAwS_Crypta {

	public static void main(String[] args) throws Exception {
		
		BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
		BigInteger eA = new BigInteger("1571");
		
		BigInteger pB = new BigInteger("98763457697834568934613");
		BigInteger qB = new BigInteger("8495789457893457345793");
		BigInteger eB = new BigInteger("87697");
		
		BigInteger mPrime = new BigInteger("418726553997094258577980055061305150940547956");
		BigInteger sPrime = new BigInteger("749142649641548101520133634736865752883277237");
	
		BigInteger nB = pB.multiply(qB);
		BigInteger phiB = (pB.subtract(BigInteger.ONE)).multiply(qB.subtract(BigInteger.ONE));
		BigInteger dB = eB.modInverse(phiB);
		
		BigInteger m = mPrime.modPow(dB, nB);
		BigInteger s = (sPrime.modPow(dB, nB)).modPow(eA, nA);
		
		System.out.println("The message that Alice sent was \"" + m + "\".");
		System.out.println("Bob can be assured of its origin integrity because it is the same as \"" + s + "\".");
	}

}
