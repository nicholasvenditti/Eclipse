package hash;

import java.util.Scanner;

public class BirthdayAttack {

	public static void main(String[] args) throws Exception {
		
		final int N = 365;
		int x;
		
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.print("Pick a value for x: ");
			
			x = scanner.nextInt();
		} while (x < 2);
		
		scanner.close();
		
		double S = 1 - Math.pow(Math.E, (-(Math.pow(x, 2)) / (2 * N)));
		
		System.out.printf("\nWith %d people in a room, the probability that at least two of them have the same birthday is approximately %.2f%%.", x, S * 100);
		
		int successes = 0;
		int trials = 1000000;
		int[] birthdays = new int[x];
		
		for (int i = 0; i < trials; i++) {
			
			for (int j = 0; j < x; j++) {
				birthdays[j] = (int) (Math.floor(Math.random() * N) + 1);
			}
			
			boolean duplicate = false;
			
			for (int k = 0; k < x && !duplicate; k++) {
				
				for (int l = k + 1; l < x; l++) {
					duplicate |= birthdays[k] == birthdays[l];
				}
			}
			
			if (duplicate) {
				successes++;
			}
		}
		
		double sPrime = successes / (double) trials;
		
		System.out.printf("\nWith %d people in a room, the probability that at least two of them have the same birthday is experimentally %.2f%%.", x, sPrime * 100);
	}

}
