package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

public class RSAwoN_Crypta {

	public static void main(String[] args) throws Exception {
		
//		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
//		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
//		BigInteger e = new BigInteger("65537");
//		BigInteger c = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
//
//		BigInteger d = e.modInverse(phi);
//		BigInteger p = (phi.divide(q.subtract(BigInteger.ONE))).add(BigInteger.ONE);
//		BigInteger n = p.multiply(q);
//		
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
//		PrivateKey priv = keyFactory.generatePrivate(privSpec);
//		
//		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//		cipher.init(Cipher.DECRYPT_MODE, priv);
//		byte[] m = cipher.doFinal(c.toByteArray());
//		
//		System.out.println(new String(m).trim());
		
		BigInteger p = new BigInteger("179463171013496453328629679915795416729");
		BigInteger q = new BigInteger("322978557223203542227398543604249761163");
		BigInteger e = new BigInteger("1031");
		BigInteger ct = new BigInteger("7589652593390754427973484574507614526893014069016754756609842532510355679724");
		
		BigInteger n = p.multiply(q);
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		BigInteger d = e.modInverse(phi);
		
//		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
//		PrivateKey priv = keyFactory.generatePrivate(privSpec);
//		
//		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
//		cipher.init(Cipher.DECRYPT_MODE, priv);
//		byte[] m = cipher.doFinal(ct.toByteArray());
		
		byte[] m = ct.modPow(d, n).toByteArray();
		
		System.out.println(new String(m).trim());
	}

}
