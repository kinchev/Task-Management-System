package com.system.management.task.models;

import com.system.management.task.models.contracts.Activity;
import com.system.management.task.models.contracts.Board;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Team;
import com.system.management.task.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.models.ModelConstants.*;

public class TeamImpl implements Team {
    private String name;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<Activity> history = new ArrayList<>();

    public TeamImpl(String name) {
        validateName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    @Override
    public void addMember(Member member) {
        this.members.add(member);
        Activity activity = new ActivityImpl(String.format("Member [%s] was successfully added to team [%s]!", member.getName(), this.getName()));
        history.add(activity);
    }

    @Override
    public void addBoard(Board board) {
        this.boards.add(board);
        Activity activity = new ActivityImpl(String.format("Board [%s] was successfully added to team [%s]!", board.getName(), this.getName()));
        history.add(activity);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Member> getMembers() {
        return this.members;
    }

    @Override
    public List<Board> getBoards() {
        return this.boards;
    }

    @Override
    public List<Activity> getHistory() {
        return this.history;
    }

    @Override
    public String printBoards() {
        StringBuilder sb = new StringBuilder();
        boards.forEach(board -> sb.append(String.format("%s\n", board.getName())));
        return sb.toString().trim();
    }

    private void validateName(String name) {
        ValidationHelpers.validateIntRange(name.length(), TEAM_NAME_LEN_MIN, TEAM_NAME_LEN_MAX, TEAM_NAME_LEN_ERR);
        this.name = name;
    }
}
