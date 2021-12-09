package com.system.management.task.models;

import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Board;
import com.system.management.task.models.contracts.WorkItem;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.models.ModelConstants.*;

public class BoardImpl implements Board {
    private String name;
    private final List<WorkItem> items;
    private final List<Activity> history;

    public BoardImpl(String name) {
        setName(name);
        this.items = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    private void validateName(String name) {
        ValidationHelpers.validateIntRange(name.length(), BOARD_NAME_LEN_MIN, BOARD_NAME_LEN_MAX, BOARD_NAME_LEN_ERR);
        this.name = name;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addWorkItem(WorkItem item) {
        this.items.add(item);
        Activity activity = new ActivityImpl(String.format("%s [%s] was successfully added to board %s!", item.toString(), item.getTitle(), this.getName()));
        history.add(activity);
    }

    @Override
    public void removeWorkItem(WorkItem item) {
        this.items.remove(item);
        Activity activity = new ActivityImpl(String.format("%s [%s] was successfully deleed from board %s!", item.toString(), item.getTitle(), this.getName()));
        this.history.add(activity);
    }

    @Override
    public List<WorkItem> getWorkItems() {
        return this.items;
    }

    @Override
    public List<Activity> getActivityHistory() {
        return this.history;
    }

    @Override
    public String viewBoardActivity() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            sb.append(history.get(i).viewInfo()).append("\n");
        }
        return sb.toString().trim();
    }

}