package asymmetric;

import java.math.BigInteger;

public class FindSmallestIntX {

	public static void main(String[] args) throws Exception {
		
		BigInteger n1 = new BigInteger("1055827021987");
		BigInteger c1 = new BigInteger("365944767426");
		BigInteger n2 = new BigInteger("973491987203");
		BigInteger c2 = new BigInteger("698856040412");
		
		BigInteger x = chineseRemainderTheorem(n1, c1, n2, c2);
		
		System.out.println(x);
	}

	public static BigInteger chineseRemainderTheorem(BigInteger n1, BigInteger c1, BigInteger n2, BigInteger c2) {
		
		BigInteger invOfN2 = n2.modInverse(n1);
		BigInteger invOfN1 = n1.modInverse(n2);
		
		BigInteger x = ((c1.multiply(n2).multiply(invOfN2)).add(c2.multiply(n1).multiply(invOfN1))).mod(n1.multiply(n2));
		
		return x;
	}
}
