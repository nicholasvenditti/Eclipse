import java.util.Scanner;

public class BinaryConversions {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String s = input.next();
		input.close();
		
		char c = s.charAt(0);
		if (c == 'b') {
			System.out.println(binaryToDecimal(s.substring(1)));
		} else {
			System.out.println(decimalToBinary(Integer.parseInt(s)));
		}
	}
	
	public static String decimalToBinary(int x) {
		
		int temp = (int) (Math.log(x) / Math.log(2));
		String result = "b0";
		
		for (int i = temp; i >= 0; i--) {
			
			if (x >= Math.pow(2, i)) {
				result += "1";
				x -= Math.pow(2, i);
			} else {
				result += "0";
			}
		}
		
		return result;
	}

	public static int binaryToDecimal(String s) {
		
		int temp = s.length() - 1;
		int result = 0;

		for (int i = temp; i >= 0; i--) {
			
			if (s.charAt(temp - i) == '1') {
				result += Math.pow(2, i);
			}
		}
		
		return result;
	}
}
