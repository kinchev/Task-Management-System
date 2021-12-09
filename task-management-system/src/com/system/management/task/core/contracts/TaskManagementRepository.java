package com.system.management.task.core.contracts;

import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;

import java.util.List;

public interface TaskManagementRepository {

    Member createMember(Team team, String memberName);

    Team createTeam(String name);

    Story createStory(Board board, String title, String description, Status status, Priority priority, Size size);

    Bug createBug(Board board, String title, String description, Status status, Priority priority, Severity severity);

    Feedback createFeedback(String team, String board, String title, String description, Status status, int rating);

    Board createBoard(Team team, String boardName);

    List<Member> getMembers();

    List<Team> getTeams();

    List<Bug> getBugs();

    List<WorkItem> getItems();

    List<Story> getStories();

    List<Feedback> getFeedbacks();
}


