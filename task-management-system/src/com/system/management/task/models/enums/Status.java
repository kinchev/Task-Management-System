package com.system.management.task.models.enums;

public enum Status {
    ACTIVE,
    FIXED,
    NEW,
    UNSCHEDULED,
    SCHEDULED,
    DONE,
    NOT_DONE,
    IN_PROGRESS;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            case NOT_DONE:
                return "Not_done";
            case IN_PROGRESS:
                return "In_progress";
            default:
                return "";
        }
    }
}
