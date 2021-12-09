package com.system.management.task.core;

import com.system.management.task.core.contracts.TaskManagementRepository;
import com.system.management.task.models.*;
import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private final List<Team> teams;
    private final List<Member> members;
    private final List<Bug> bugs;
    private final List<Story> stories;
    private final List<Feedback> feedbacks;
    private int id;


    public TaskManagementRepositoryImpl() {
        this.id = 0;
        this.teams = new ArrayList<>();
        this.members = new ArrayList<>();
        this.bugs = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    public List<WorkItem> getItems() {
        List<WorkItem> list = new ArrayList<>();
        list.addAll(bugs);
        list.addAll(feedbacks);
        list.addAll(stories);
        return list;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }


    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }

    @Override
    public Member createMember(Team team, String name) {
        Member member = new MemberImpl(name);
        team.getMembers().add(member);
        members.add(member);
        return member;
    }

    @Override
    public Story createStory(Board board, String title, String description, Status status, Priority priority, Size size) {
        Story story = new StoryImpl(++id, title, description, status, priority, size);
        board.addWorkItem(story);
        this.stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String team, String board, String title, String description, Status status, int rating) {
        Feedback feedback = new FeedbackImpl(++id, title, description, status, rating);
        this.feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Bug createBug(Board board, String title, String description, Status status, Priority priority, Severity severity) {
        Bug bug = new BugImpl(++id, title, description, status, priority, severity);
        board.addWorkItem(bug);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Board createBoard(Team team, String boardName) {
        Board board = new BoardImpl(boardName);
        team.getBoards().add(board);
        return board;
    }
}