package sample;

import java.util.regex.Pattern;

public class DifficultyCalculator {
    private static final String PASSWORD_NUMBER_STRENGTH = "[0-9]";
    private static final int PASSWORD_NUMBER_VALUE = 10;

    private static final String PASSWORD_LETTER_STRENGTH = "[a-zA-Z]";
    private static final int PASSWORD_LETTER_VALUE = 26;

    private static final String PASSWORD_SYMBOL_STRENGTH = "[^0-9a-zA-Z]";
    private static final int PASSWORD_SYMBOL_VALUE = 33;

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 20;

    private static final int WEAK_PASSWORD_SD_UPPER_BOUND = 150;
    private static final int OKAY_PASSWORD_SD_UPPER_BOUND = 350;
    private static final int STRONG_PASSWORD_SD_UPPER_BOUND = 660;

    private String password;
    private int passwordLength, spaceDepth;
    private long searchSpaceSize;
    private double estimatedTimeToBreakOnlineAttack,
            estimatedTimeToBreakOfflineFastAttack, estimatedTimeToBreakMassiveCrackingAttack;
    private PasswordStrength strength;


    public DifficultyCalculator(String password) {
        this.password = password;
        passwordLength = password.length();
        spaceDepth = determineSpaceDepth(password);
        searchSpaceSize = determineSearchSpaceSize();
        determineEstimatedTimesToBreak();
        strength = determinePasswordStrength();

    }

    private int determineSpaceDepth(String password) {
        //initialize space depth to
        int spaceDepth = 0;

        //calculate the space depth value of password based on each character
        for (int i = 0; i < password.length(); i++) {
            String charStr = Character.toString(password.charAt(i));

            if (Pattern.matches(PASSWORD_NUMBER_STRENGTH, charStr))
                spaceDepth += PASSWORD_NUMBER_VALUE;
            if (Pattern.matches(PASSWORD_LETTER_STRENGTH, charStr))
                spaceDepth += PASSWORD_LETTER_VALUE;
            if (Pattern.matches(PASSWORD_SYMBOL_STRENGTH, charStr))
                spaceDepth += PASSWORD_SYMBOL_VALUE;
        }

        return spaceDepth;
    }

    private PasswordStrength determinePasswordStrength() {
        //initialize strength of password to weak
        PasswordStrength passwordStrength = PasswordStrength.WEAK;

        if (spaceDepth < WEAK_PASSWORD_SD_UPPER_BOUND)
            passwordStrength = PasswordStrength.WEAK;
        else if (spaceDepth >= WEAK_PASSWORD_SD_UPPER_BOUND && spaceDepth < OKAY_PASSWORD_SD_UPPER_BOUND)
            passwordStrength = PasswordStrength.OKAY;
        else if (spaceDepth >= OKAY_PASSWORD_SD_UPPER_BOUND && spaceDepth <= STRONG_PASSWORD_SD_UPPER_BOUND)
            passwordStrength = PasswordStrength.STRONG;

        return passwordStrength;
    }

    private int passwordMaxSpace() {
        return MAX_PASSWORD_LENGTH * PASSWORD_SYMBOL_VALUE;
    }

    private int passwordMinSpace() {
        return MIN_PASSWORD_LENGTH * PASSWORD_NUMBER_VALUE;
    }

    private long determineSearchSpaceSize() {
        long searchSpace = 0;

        for (int i = 0; i < passwordLength; i++) {
            searchSpace += Math.pow(spaceDepth, passwordLength - i);
        }

        return searchSpace;
    }

    private void determineEstimatedTimesToBreak() {
        estimatedTimeToBreakOnlineAttack = spaceDepth / 1000;
        estimatedTimeToBreakOfflineFastAttack = spaceDepth / Math.pow(10,9);
        estimatedTimeToBreakMassiveCrackingAttack = spaceDepth/ Math.pow(10,12);
    }


    public static int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }

    public static int getMaxPasswordLength() {
    }

    public static String getPasswordNumberStrength() {
        return PASSWORD_NUMBER_STRENGTH;
    }

    public int getSpaceDepth() {
        return spaceDepth;
    }

    public long getSearchSpaceSize() {
        return searchSpaceSize;
    }

    public double getEstimatedTimeToBreakOnlineAttack() {
        return estimatedTimeToBreakOnlineAttack;
    }

    public double getEstimatedTimeToBreakOfflineFastAttack() {
        return estimatedTimeToBreakOfflineFastAttack;
    }

    public double getEstimatedTimeToBreakMassiveCrackingAttack() {
        return estimatedTimeToBreakMassiveCrackingAttack;
    }

    public PasswordStrength getStrength() {
        return strength;
    }

}




