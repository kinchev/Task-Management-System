package com.system.management.task.core;

import com.system.management.task.core.contracts.TaskManagementRepository;
import com.system.management.task.exceptions.AlreadyExistsException;
import com.system.management.task.exceptions.ElementNotFoundException;
import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.core.RepositoryConstants.*;

public class RepositoryHelper {
    private final TaskManagementRepository repo;

    public RepositoryHelper(TaskManagementRepository repo) {
        this.repo = repo;
    }

    public void removeMember(String teamName, String memberName) {
        Team team = findTeamByName(teamName);
        Member member = findMemberByName(memberName);
        member.getTasks()
                .forEach(task -> task.setAssignee(null));
        team.getMembers().remove(member);
    }

    public boolean memberNameExists(String name) {
        return repo.getMembers()
                .stream()
                .anyMatch(m -> m.getName().equals(name));
    }

    public boolean teamNameExists(String name) {
        return repo.getTeams()
                .stream()
                .anyMatch(team -> team.getName().equals(name));
    }

    public boolean boardNameExists(String teamName, String name) {
        return findTeamByName(teamName).getBoards()
                .stream()
                .anyMatch(board -> board.getName().equals(name));
    }

    public Member findMemberByName(String name) {
        return repo.getMembers()
                .stream()
                .filter(member -> member.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(NO_SUCH_MEMBER, name)));
    }

    public Team findTeamByName(String name) {

        return repo.getTeams()
                .stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(NO_SUCH_TEAM, name)));

    }

    public Board findBoardByName(String teamName, String name) {
        return findTeamByName(teamName).getBoards()
                .stream()
                .filter(b -> b.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(NO_SUCH_BOARD, name)));
    }

    public WorkItem findWorkItemById(int id) {
        return repo.getItems().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(NO_SUCH_TASK, id)));
    }


    public <T extends Identifiable> T findElementById(int id, List<T> elements) {
        return elements
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, id)));
    }

    public List<Assignable> getAssignables() {
        List<Assignable> res = new ArrayList<>();
        res.addAll(repo.getBugs());
        res.addAll(repo.getStories());
        return res;
    }

    public boolean storyNameExists(String story) {
        return repo.getStories()
                .stream()
                .anyMatch(s -> s.getTitle().equals(story));
    }

    public Bug createBug(String team, String board, String title, String description, Status status, Priority priority, Severity severity) {
        findTeamByName(team);
        Board boardObj = findBoardByName(team, board);
        return repo.createBug(boardObj, title, description, status, priority, severity);
    }

    public void createBoard(String teamName, String boardName) {
        if (!teamNameExists(teamName) || boardNameExists(teamName, boardName)) {
            throw new AlreadyExistsException(String.format(BOARD_NAME_ALREADY_EXISTS, boardName));
        }
        Team team = findTeamByName(teamName);
        repo.createBoard(team, boardName);
    }

    public void createTeam(String name) {
        if (teamNameExists(name)) {
            throw new AlreadyExistsException(TEAM_NAME_ALREADY_EXISTS);
        }
        repo.createTeam(name);
    }

    public void createMember(String memberName, String teamName) {
        if (memberNameExists(memberName)) {
            throw new AlreadyExistsException(MEMBER_NAME_ALREADY_EXISTS);
        }
        Team team = findTeamByName(teamName);
        repo.createMember(team, memberName);
    }

    public Story createStory(String team, String board, String title, String description, Status status, Priority priority, Size size) {
        if (storyNameExists(title)) throw new AlreadyExistsException("Story already exists");
        findTeamByName(team);
        Board b = findBoardByName(team, board);
        return repo.createStory(b, title, description, status, priority, size);
    }

    public void createFeedback(String team, String board, String title, String description, Status status, int rating) {
        repo.createFeedback(team, board, title, description, status, rating);
    }

    public List<Member> getMembers() {
        return repo.getMembers();
    }

    public List<Bug> getBugs() {
        return repo.getBugs();
    }

    public List<Story> getStories() {
        return repo.getStories();
    }

    public List<Feedback> getFeedbacks() {
        return repo.getFeedbacks();
    }

    public List<WorkItem> getItems() {
        return repo.getItems();
    }
}
