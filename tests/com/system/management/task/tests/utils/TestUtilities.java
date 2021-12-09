package com.system.management.task.tests.utils;

import com.system.management.task.models.BugImpl;
import com.system.management.task.models.contracts.Bug;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;

import java.util.Arrays;
import java.util.List;

import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;


public class TestUtilities {
        /**
         * Returns a new String with size equal to wantedSize.
         * Useful when you do not care what the contents of a String are,
         * for example when testing if a String of given size
         * would cause an exception being thrown.
         *
         * @param wantedSize the size of the String to be returned.
         * @return a new String with size equal to wantedSize
         */
        public static List<String> initializeListWithSize(int wantedSize) {
            return Arrays.asList(new String[wantedSize]);
        }

        public static String initializeStringWithSize(int wantedSize) {
            return "x".repeat(wantedSize);
        }

        public static Bug initializeTestBug() {
            return new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.FIXED, Priority.LOW, Severity.MAJOR);
        }
}