package com.example.structure.support;

import android.graphics.Color;

import com.example.structure.R;

/**
 * Created by Rajesh Pradeep G on 12-12-2019
 */
public enum PasswordStrength {

    WEAK(R.string.weak, Color.RED),
    FAIR(R.string.fair, Color.argb(255, 220, 185, 0)),
    GOOD(R.string.good, Color.GREEN),
    STRONG(R.string.strong, Color.BLUE);

    //--------REQUIREMENTS--------
    static int REQUIRED_LENGTH = 8;
    static int MAXIMUM_LENGTH = 50;
    static boolean REQUIRE_SPECIAL_CHARACTERS = true;
    static boolean REQUIRE_DIGITS = true;
    static boolean REQUIRE_LOWER_CASE = true;
    static boolean REQUIRE_UPPER_CASE = true;

    int resId;
    int color;

    PasswordStrength(int resId, int color) {
        this.resId = resId;
        this.color = color;
    }

    public int getValue() {
        return resId;
    }

    public int getColor() {
        return color;
    }

    public static int calculateStrength(String password) {
        int currentScore = 0;
        boolean sawUpper = false;
        boolean sawLower = false;
        boolean sawDigit = false;
        boolean sawSpecial = false;
        boolean isCharSuccess = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (!sawSpecial && !Character.isLetterOrDigit(c)) {
                currentScore += 1;
                sawSpecial = true;
            } else {
                if (!sawDigit && Character.isDigit(c)) {
                    currentScore += 1;
                    sawDigit = true;
                } else {
                    if (!sawUpper || !sawLower) {
                        if (Character.isUpperCase(c))
                            sawUpper = true;
                        else if (Character.isLowerCase(c))
                            sawLower = true;
                        if (sawUpper && sawLower)
                            currentScore += 1;
                    }
                }
            }
        }

        /*if (password.length() > REQUIRED_LENGTH) {
            if ((REQUIRE_SPECIAL_CHARACTERS && !sawSpecial) || (REQUIRE_UPPER_CASE && !sawUpper) ||
                    (REQUIRE_LOWER_CASE && !sawLower) || (REQUIRE_DIGITS && !sawDigit)) {
                currentScore = 1;
            } else {
                currentScore = 2;
                if (password.length() > MAXIMUM_LENGTH) {
                    currentScore = 3;
                }
            }
        } else {
            currentScore = 0;
        }*/

//        if (currentScore == 0) {
        isCharSuccess = sawUpper && sawLower;
            if (isCharSuccess || (sawSpecial) || (sawDigit)) {
                currentScore = 0;
               if(isCharSuccess && sawDigit && sawSpecial) {
                   currentScore = 2;
                   if (password.length() >= REQUIRED_LENGTH) {
                       currentScore = 3;
                   }
                } else if ((isCharSuccess && sawSpecial)){
                    currentScore = 1;
                } else if (isCharSuccess && sawDigit) {
                    currentScore = 1;
                } else if (sawSpecial && sawDigit) {
                    currentScore = 1;
                }
            } else {
                currentScore = -1;
            }
        /*} else {
            currentScore = -1;
        }*/

        /*switch (currentScore) {
            case 0:
                return WEAK;
            case 1:
                return FAIR;
            case 2:
                return GOOD;
            case 3:
                return STRONG;
            default:
        }

        return STRONG;*/
        return currentScore;
    }

}
