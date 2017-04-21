package passward;

import java.util.Scanner;

/**
 * Created by: Brittany Safford & Zachary Laborde
 */

public class ConsoleClient {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String again = "";
        String password = "";

        do {
            System.out.println("Enter password: ");
            password = keyboard.nextLine();

            DifficultyCalculator calculator = new DifficultyCalculator(password);

            System.out.println("Space Depth: " + calculator.getSpaceDepth());
            System.out.println("Password Strength: " + calculator.getStrength());

            System.out.println("Estimated Time To Break in Online Attack Scenario " +
                    calculator.getEstimatedTimeToBreakOnlineAttack());

            System.out.println("Estimated Time To Break in Offline Fast Attack Scenario " +
                    calculator.getEstimatedTimeToBreakOfflineFastAttack());

            System.out.println("Estimated Time To Break in Massive Cracking Attack Scenario " +
                    calculator.getEstimatedTimeToBreakMassiveCrackingAttack());

            System.out.println();
            System.out.println("Would You Like Enter Another Password? (Yes/No)");
            again = keyboard.nextLine();

        } while (again.equalsIgnoreCase("Yes"));

    }
}
