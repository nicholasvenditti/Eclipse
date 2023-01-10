import java.util.Scanner;

public class EgyptianMultiplication {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		int y = input.nextInt();
		input.close();
		System.out.println(egyptianMultiplication(x,y));
	}
	
	public static int egyptianMultiplication(int x, int y) {

		int l = x;
		int r = y;
		int s = 0;

		while (l >= 1) {
			
			if (l % 2 == 1) {
				l--;
				s += r;
			}
				
			l /= 2;
			r *= 2;	
		}

		return s;
	}
}
