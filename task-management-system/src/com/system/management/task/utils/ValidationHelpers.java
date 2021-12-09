package com.system.management.task.utils;

import java.util.List;

public class ValidationHelpers {
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";
    private static final String ARGUMENTS_COUNT_NOT_EVEN_MAX_4 = "Arguments count not even or more than four. Received: %d.";

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }

    public static void validateArgumentsCountIsEvenAndMax4(List<String> list) {
        if (list.size() % 2 != 0 || list.size() > 4) {
            throw new IllegalArgumentException(
                    String.format(ARGUMENTS_COUNT_NOT_EVEN_MAX_4, list.size())
            );
        }
    }
}
