package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

public class RSAwP_Crypta {

	public static void main(String[] args) throws Exception {
		
		BigInteger e = new BigInteger("74327");
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger c = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");
		BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");
		
		BigInteger q = n.divide(p);
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		BigInteger d = e.modInverse(phi);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, priv);
		byte[] pt = cipher.doFinal(c.toByteArray());
		
		System.out.println(new String(pt).trim());
	}

}
