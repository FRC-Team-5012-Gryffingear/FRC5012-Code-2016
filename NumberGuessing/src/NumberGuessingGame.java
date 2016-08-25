import java.util.Scanner;

public class NumberGuessingGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number = 37;
		int guess = 0;

		do {
			Scanner scan = new Scanner(System.in);

			guess = scan.nextInt();

			if (guess < number) {
				System.out.println("Too Low!");
			} else if (guess > number) {
				System.out.println("Too High!");
			} else {
				System.out.println("You Smell!!!");
			}
		} while (number != guess);

	}

}


