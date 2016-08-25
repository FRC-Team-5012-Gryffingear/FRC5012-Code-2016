import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double numberA = 0.0;
		double numberB = 0.0;
		double answer = 0.0;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter a number.");
		numberA = scan.nextDouble();

		System.out.println("Please enter another number.");
		numberB = scan.nextDouble();
		
		
		answer = numberA + numberB;
		System.out.println("Your answer is: " + answer);
		
	}

}
