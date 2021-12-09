package com.system.management.task.models.enums;

public enum Size {
    SMALL, MEDIUM, LARGE;

    @Override
    public String toString() {
        switch (this) {
            case LARGE:
                return "Large";
            case MEDIUM:
                return "Medium";
            case SMALL:
                return "Small";
            default:
                return "";
        }
    }
}
