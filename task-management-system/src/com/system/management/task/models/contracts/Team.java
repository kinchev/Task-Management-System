package com.system.management.task.models.contracts;

import java.util.List;

public interface Team {
    void addMember(Member member);

    List<Member> getMembers();

    String getName();

    void addBoard(Board board);

    List<Board> getBoards();

    String printBoards();

    List<Activity> getHistory();
}
