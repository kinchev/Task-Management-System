package com.system.management.task.models;

import static java.lang.String.format;

public class ModelConstants {
    public static final int TEAM_NAME_LEN_MIN = 5;
    public static final int TEAM_NAME_LEN_MAX = 15;
    public static final String TEAM_NAME_LEN_ERR = format(
            "Team name must be between %s and %s characters long!",
            TEAM_NAME_LEN_MIN,
            TEAM_NAME_LEN_MAX);

    public static final int MEMBER_NAME_LEN_MIN = 5;
    public static final int MEMBER_NAME_LEN_MAX = 15;
    public static final String MEMBER_NAME_LEN_ERR = format(
            "Member name must be between %s and %s characters long!",
            MEMBER_NAME_LEN_MIN,
            MEMBER_NAME_LEN_MAX);

    public static final int BOARD_NAME_LEN_MIN = 5;
    public static final int BOARD_NAME_LEN_MAX = 10;
    public static final String BOARD_NAME_LEN_ERR = format(
            "Board name must be between %s and %s characters long!",
            BOARD_NAME_LEN_MIN,
            BOARD_NAME_LEN_MAX);

    public static final int TASK_TITLE_LEN_MIN = 10;
    public static final int TASK_TITLE_LEN_MAX = 50;
    public static final String TASK_TITLE_LEN_ERR = format(
            "Task title must be between %s and %s characters long!",
            TASK_TITLE_LEN_MIN,
            TASK_TITLE_LEN_MAX);


    public static final int TASK_DESCRIPTION_LEN_MIN = 10;
    public static final int TASK_DESCRIPTION_LEN_MAX = 500;
    public static final String TASK_DESCRIPTION_LEN_ERR = format(
            "Task description must be between %s and %s characters long!",
            TASK_DESCRIPTION_LEN_MIN,
            TASK_DESCRIPTION_LEN_MAX);

    public static final String INVALID_FEEDBACK_STATUS_INPUT = "Feedback status must be [Done] / [Unscheduled] / [Scheduled] / [New]";
    public static final String INVALID_STORY_STATUS_INPUT = "Story status must be [Done] / [In_progress] / [Not-done]";
    public static final String INVALID_STORY_SIZE_INPUT = "Story size must be [Large] / [Medium] / [Small]";
    public static final String INVALID_BUG_STATUS_INPUT = "Bug status must be [Active] / [Fixed]";
    public static final String INVALID_BUG_SEVERITY_INPUT = "Bug status must be [Critical] / [Major] / [Minor]";

    public static final String INVALID_PRIORITY_INPUT = "Priority must be [High] or [Medium] or [Low!]";

    public static final String EMPTY_CONTENT = "Content cannot be empty!";

    public static final String NO_STEPS = "No steps to show!";

    public static final String CANNOT_CHANGE_STATUS = "Cannot change status, already at [%s].";
    public static final String STATUS_CHANGED = "Status changed from %s to %s";

    public static final String CANNOT_CHANGE_PRIORITY = "Cannot change priority, already at [%s].";
    public static final String PRIORITY_CHANGED = "Priority changed from %s to %s";

    public static final String CANNOT_CHANGE_SEVERITY = "Cannot change severity, already at [%s].";
    public static final String SEVERITY_CHANGE = "Severity changed from %s to %s";

    public static final String CANNOT_CHANGE_SIZE = "Cannot advance, already at [%s].";
    public static final String SIZE_CHANGED = "Size changed from %s to %s";
    public static final String MEMBER_HAS_NO_ACTIVITY = "Member %s has no activity!";
    public static final String COMMENT_REMOVE = "Comment remove successfully";
}

