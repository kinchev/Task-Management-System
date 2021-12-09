package com.system.management.task.tests.utils;

import com.system.management.task.models.*;
import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;


public class Factory {

    public static Bug createBug() {
        return new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.HIGH, Severity.MAJOR);
    }

    public static Story createStory() {
        return new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.LOW, Size.LARGE);
    }
    public static Team createTeam(){
        return new TeamImpl(VALID_TEAM_NAME);
    }
//        public static Feedback createFeedback() {
//            return new FeedbackImpl(VALID_MAKE, VALID_MODEL, VALID_PRICE, VALID_CATEGORY);
//        }
////




    public static Board createBoard() {
        return new BoardImpl(VALID_BOARD_NAME);
    }

    public static Member createMember() {
        return new MemberImpl(VALID_MEMBER_NAME);
    }


}


