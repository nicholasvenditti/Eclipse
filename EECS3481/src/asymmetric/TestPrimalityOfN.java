package asymmetric;

import java.math.BigInteger;

public class TestPrimalityOfN {

	public static void main(String[] args) throws Exception {
		
//		BigInteger r = new BigInteger("1033931178476059651954862004553");
//		BigInteger a = new BigInteger("2");
//		BigInteger q = r.subtract(BigInteger.ONE);
//		BigInteger k = new BigInteger("0");
//		
//		while(q.mod(a).intValue() == 0) {
//			q = q.divide(a);
//			k = k.add(BigInteger.ONE);
//		}
//		
//		BigInteger x = a.modPow(q, r);
//		
//		if (x.intValue() != 1 && x.intValue() != r.intValue() - 1) {
//			
//			for (int i = 0; i < k.intValue() - 1 && x.intValue() != r.intValue() - 1; i++) {
//				x = x.modPow(a, r);
//				
//				if (x.intValue() == 1) {
//					System.out.println("Composite");
//				} 
//			}
//			
//			if (x.intValue() != r.intValue() - 1) {
//				System.out.println("Composite");
//			} else {
//				System.out.println("Inconclusive");
//			}
//			
//		} else {
//			System.out.println("Inconclusive");
//		}
//	}
		
		BigInteger r = new BigInteger("2328801737485902541849618724904455859669007359741761441520233194808244207500198634924778381673592639127");
//		BigInteger e = new BigInteger("101");
//		BigInteger d = new BigInteger("1614021006178348296331418850434525717972038799524606954771790822915702530615601326270165594230346235021");
		
//		BigInteger r = new BigInteger("1033931178476059651954862004553");
		BigInteger a = new BigInteger("2");
		BigInteger b = new BigInteger("3");
		BigInteger q = r.subtract(BigInteger.ONE);
		BigInteger k = new BigInteger("0");
		
		while(q.mod(a).intValue() == 0) {
			q = q.divide(a);
			k = k.add(BigInteger.ONE);
		}
		
		BigInteger x = b.modPow(q, r);
		
		if (x.intValue() != 1 && x.intValue() != r.intValue() - 1) {
			
			for (int i = 0; i < k.intValue() - 1 && x.intValue() != r.intValue() - 1; i++) {
				x = x.modPow(b, r);
				
				if (x.intValue() == 1) {
					System.out.println("Composite1");
				} 
			}
			
			if (x.intValue() != r.intValue() - 1) {
				System.out.println("Composite2");
			} else {
				System.out.println("Inconclusive");
			}
			
		} else {
			System.out.println("Inconclusive");
		}
	}

}
