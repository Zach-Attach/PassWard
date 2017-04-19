package sample;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DifficultyCalculator {
    private static final String PASSWORD_NUMBER_STRENGTH = "[0-9]";
    private static final int PASSWORD_NUMBER_VALUE = 10;

    private static final String PASSWORD_LOWER_STRENGTH = "[a-z]";
    private static final int PASSWORD_LOWER_VALUE = 26;

    private static final String PASSWORD_UPPER_STRENGTH = "[A-Z]";
    private static final int PASSWORD_UPPER_VALUE = 26;

    private static final String PASSWORD_SYMBOL_STRENGTH = "[^0-9a-zA-Z]";
    private static final int PASSWORD_SYMBOL_VALUE = 33;

    /**
     * I don't think we need this part
     */
//    private static final int MIN_PASSWORD_LENGTH = 6;
//    private static final int MAX_PASSWORD_LENGTH = 20;
/*
    private static final int WEAK_PASSWORD_SD_UPPER_BOUND = 150;
    private static final int OKAY_PASSWORD_SD_UPPER_BOUND = 350;
    private static final int STRONG_PASSWORD_SD_UPPER_BOUND = 660; */


    private boolean missingNum = true, missingLower = true, missingUpper = true, missingSym = true;
    private String strength;
    private int passwordLength, spaceDepth;
    private double searchSpaceSize, time_OnlineAttack, time_FastAttack, time_MassAttack;

    /**
     * changed this to a String; realized it was unnecessary to be an enum
     */
//    private PasswordStrength strength;
    public DifficultyCalculator(String password) {

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
        for (char c : password.toCharArray()) {
            String charStr = Character.toString(c);

            if (missingNum && Pattern.matches(PASSWORD_NUMBER_STRENGTH, charStr)) {
                spaceDepth += PASSWORD_NUMBER_VALUE;
                missingNum = false;
            } else if (missingLower && Pattern.matches(PASSWORD_LOWER_STRENGTH, charStr)) {
                spaceDepth += PASSWORD_LOWER_VALUE;
                missingLower = false;
            } else if (missingUpper && Pattern.matches(PASSWORD_UPPER_STRENGTH, charStr)) {
                spaceDepth += PASSWORD_UPPER_VALUE;
                missingUpper = false;
            } else if (missingSym && Pattern.matches(PASSWORD_SYMBOL_STRENGTH, charStr)) {
                spaceDepth += PASSWORD_SYMBOL_VALUE;
                missingSym = false;
            }
        }

        return spaceDepth;
    }

    private String determinePasswordStrength() {
        //initialize strength of password to weak
        String passwordStrength;

        if (time_MassAttack >= 3600)
            passwordStrength = "STRONG";
        else if (time_FastAttack >= 3600)
            passwordStrength = "GOOD";
        else if (time_OnlineAttack >= 3600)
            passwordStrength = "OKAY";
        else
            passwordStrength = "WEAK";

        return passwordStrength;
    }

    /**
     * I don't know what the purpose of this is.
     */
/*
   private int passwordMaxSpace() {
        return MAX_PASSWORD_LENGTH * PASSWORD_SYMBOL_VALUE;
    }

    private int passwordMinSpace() {
        return MIN_PASSWORD_LENGTH * PASSWORD_NUMBER_VALUE;
    }
    */
    private long determineSearchSpaceSize() {
        long searchSpace = 0;

        for (int i = 0; i < passwordLength; i++) {
            searchSpace += Math.pow(spaceDepth, passwordLength - i);
        }

        return searchSpace;
    }

    private void determineEstimatedTimesToBreak() {
        time_OnlineAttack = searchSpaceSize / 1000;
        time_FastAttack = searchSpaceSize / Math.pow(10, 9);
        time_MassAttack = searchSpaceSize / Math.pow(10, 12);
    }

    public String[] determineEstimatedTimesAsStrings() {

        String[] returnArr = new String[3];

        int counter = 0;

        double[] secondsArr = {
                Math.pow(3.1536, 16), //# of seconds in an eon
                Math.pow(3.1536, 9), //# of seconds in a century
                Math.pow(3.1536, 8), //# of seconds in a decade
                Math.pow(3.1536, 7), //# of seconds in a year
                604800, //# of seconds in a week
                86400,  //# of seconds in a day
                3600, //# of seconds in an hour
                60, //# of seconds in a minute
                1, //a second
                .001 //a millisecond
        };

        String[] measureArr = {
                "eon", "century", "decade", "year", "week", "day", "hour", "minute", "second", "millisecond"
        };


        for (double tm : new double[]{time_OnlineAttack, time_FastAttack, time_MassAttack}) {

            if (tm >= Double.MAX_VALUE) {
                returnArr[counter] = "a really long time";
            } else if (tm < .001) {
                returnArr[counter] = "instantly";
            } else {
                ArrayList<String> lineList = new ArrayList<>();

                for (int i = 0; i < secondsArr.length; i++) {

                    if (tm >= secondsArr[i]) {

                        long num = (long) (tm / secondsArr[i]);

                        lineList.add(String.format("%s%d %s%s",
                                (i == secondsArr.length - 1 && lineList.size() > 0) ? "and " : "",
                                num,
                                measureArr[i],
                                (num > 1) ? "s" : ""));

                        tm %= secondsArr[i];
                    }
                }

                returnArr[counter] = String.join(", ", lineList);
            }
            counter++;
        }

        return returnArr;

    }


/*    public static int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }

    public static int getMaxPasswordLength() {
        return Integer.MAX_VALUE; //TODO: Is this ok?
    }*/

/*
    public static String getPasswordNumberStrength() {
        return PASSWORD_NUMBER_STRENGTH;
    }
*/

    public int getSpaceDepth() {
        return spaceDepth;
    }

    public double getSearchSpaceSize() {
        return searchSpaceSize;
    }

    public double getEstimatedTimeToBreakOnlineAttack() {
        return time_OnlineAttack;
    }

    public double getEstimatedTimeToBreakOfflineFastAttack() {
        return time_FastAttack;
    }

    public double getEstimatedTimeToBreakMassiveCrackingAttack() {
        return time_MassAttack;
    }

    public String getStrength() {
        return strength;
    }

    public boolean isMissingNum() {
        return missingNum;
    }

    public boolean isMissingLower() {
        return missingLower;
    }

    public boolean isMissingUpper() {
        return missingUpper;
    }

    public boolean isMissingSym() {
        return missingSym;
    }

}




