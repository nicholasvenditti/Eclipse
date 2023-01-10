import java.util.Scanner;

public class C318 {

	public static void main(String Args[]) {
		Scanner input = new Scanner(System.in);
		String s = input.next();
		input.close();
		System.out.println(reverse(s, s.length()));
	}
	
	public static String reverse(String s, int length) {
		
		if (length == 0) {
			return "";
		} else {
			return s.charAt(length - 1) + reverse(s, length - 1);
		}
	}
}
