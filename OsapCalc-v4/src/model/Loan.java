package model;

public class Loan {
	
	public int error = 0;

	public Loan() {
		
	}
	
	public double computePayment(String p, String a, String i, String g, String gp, String fi) throws Exception {
		
		double A = Double.parseDouble(p);
		double n = Double.parseDouble(a);
		double r = Double.parseDouble(i);
		String grace = g;
		double graceN = Double.parseDouble(gp);
		double fixedR = Double.parseDouble(fi);
		
		if (!(A > 0)) {
			error = 1;
		} else if (!(r > 0) || !(r < 100)) {
			error = 2;
		} else if (!(n > 0)) {
			error = 3;
		} else {
			error = 0;
		}
		
		double monthlyPayment = (((r + fixedR) / 100) / 12) * A / (1 - Math.pow(1 + (((r + fixedR) / 100) / 12), -n));
		
		if (grace != null) {
			
			double graceR = computeGraceInterest(A + "", graceN + "", r + "", fixedR + "");
			
			monthlyPayment += (graceR / graceN);
		}
		
		return monthlyPayment;
	}
	
	public double computeGraceInterest(String p, String gp, String i, String fi) throws Exception {
		
		double A = Double.parseDouble(p);
		double graceN = Double.parseDouble(gp);
		double r = Double.parseDouble(i);
		double fixedR = Double.parseDouble(fi);
	
		double graceR = A * (((r + fixedR) / 100) / 12) * graceN;
		
		return graceR;
	}
}
