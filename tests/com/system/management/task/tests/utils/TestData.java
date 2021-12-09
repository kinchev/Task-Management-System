package com.system.management.task.tests.utils;

import com.system.management.task.models.ModelConstants;
import com.system.management.task.models.StoryImpl;
import com.system.management.task.models.TeamImpl;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;

import static com.system.management.task.models.ModelConstants.*;

public class TestData {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_BOARD = 2;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_MEMBER = 2;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_STORY = 7;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS_FEEDBACK = 6;





    public static class TaskBase {
        public static final String VALID_TITLE = TestUtilities.initializeStringWithSize(TASK_TITLE_LEN_MIN + 1);
        public static final String VALID_DESCRIPTION = TestUtilities.initializeStringWithSize(TASK_DESCRIPTION_LEN_MIN + 1);

    }

    public static class TeamImpl {
        public static final String VALID_TEAM_NAME = TestUtilities.initializeStringWithSize(TEAM_NAME_LEN_MIN + 1);

    }



public static class BoardImpl {
    public static final String VALID_BOARD_NAME = TestUtilities.initializeStringWithSize(BOARD_NAME_LEN_MIN + 1);

}
    public static class MemberImpl {
        public static final String VALID_MEMBER_NAME = TestUtilities.initializeStringWithSize(MEMBER_NAME_LEN_MIN + 1);

    }
    public static class CreateBugCommand{
//  public static final int EXPECTED_NUMBER_OF_ARGUMENTS_BUG=7;
//        public static final int VALID_NUMBER_OF_ARGUMENTS_BUG =TestUtilities.initializeStringWithSize(EXPECTED_NUMBER_OF_ARGUMENTS_BUG+1);
    }
//    public static class Bug {
//        public static final String VALID_TITLE = TestUtilities.initializeStringWithSize(TASK_TITLE_LEN_MIN + 1);
//        public static final String VALID_DESCRIPTION = TestUtilities.initializeStringWithSize(TASK_DESCRIPTION_LEN_MIN + 1);
//
//    }
//
//    public static class Story {
//        public static final String VALID_TITLE = TestUtilities.initializeStringWithSize(TASK_TITLE_LEN_MIN + 1);
//        public static final String VALID_DESCRIPTION = TestUtilities.initializeStringWithSize(TASK_DESCRIPTION_LEN_MIN + 1);
//
//    }
//
//    public static void Feedback {
//        public static final String VALID_TITLE = TestUtilities.initializeStringWithSize(TASK_TITLE_LEN_MIN + 1);
//        public static final String VALID_DESCRIPTION = TestUtilities.initializeStringWithSize(TASK_DESCRIPTION_LEN_MIN + 1);
//    }

}