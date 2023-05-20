package hash;

import java.math.BigInteger;
import java.security.MessageDigest;

public class RSAwSHA2_Signature {

	public static void main(String[] args) throws Exception {
		
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		byte[] m = "Meet me at 5 pm tomorrow".getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] hash = md.digest(m);
		
		BigInteger h = new BigInteger(hash);
		BigInteger s = h.modPow(d, n);
		
		BigInteger hPrime = s.modPow(e, n);
		
		System.out.println("If I send the message \"" + new String(m) + "\" and the signature:\n" + s);
		System.out.println("then she can verify it came from me by hashing the message to:\n" + h);
		System.out.println("decrypting the signature to:\n" + hPrime);
		System.out.println("and comparing the two.");
	}

}
