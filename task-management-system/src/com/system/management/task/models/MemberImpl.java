package com.system.management.task.models;

import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Task;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.models.ModelConstants.*;

public class MemberImpl implements Member {

    private String name;
    private final List<Task> tasks;
    private final List<Activity> history;

    public MemberImpl(String name) {
        validateName(name);
        this.tasks = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addTask(Task task) {
        Activity activity = new ActivityImpl(String.format("Task with %d was assigned to %s.\n", task.getId(), this.name));
        this.history.add(activity);
        this.tasks.add(task);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public List<Activity> getHistory() {
        return this.history;
    }

    @Override
    public String getActivityHistory() {
        if (history.size() == 0) {
            return String.format(MEMBER_HAS_NO_ACTIVITY, name);
        }
        StringBuilder sb = new StringBuilder();
        history.forEach(act -> sb.append(String.format("%s", act.viewInfo())));
        return sb.toString().trim();
    }

    private void validateName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MEMBER_NAME_LEN_MIN, MEMBER_NAME_LEN_MAX, MEMBER_NAME_LEN_ERR);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Member))
            return false;
        Member other = (Member) o;
        return this.name.equals(other.getName())
                && this.tasks.equals(other.getTasks())
                && this.history.equals(other.getHistory());
    }
}

