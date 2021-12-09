package com.system.management.task.tests.core;

import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.exceptions.AlreadyExistsException;
import com.system.management.task.exceptions.ElementNotFoundException;
import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.MemberImpl.VALID_MEMBER_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;


public class RepositoryHelper_Tests {
    private RepositoryHelper repoHelper;

    @BeforeEach
    void init() {
        this.repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
    }

    @Test
    public void removeMember_Should_RemoveMember() {
        repoHelper.createTeam("teamName");
        repoHelper.createMember("memberName", "teamName");
        repoHelper.removeMember("teamName", "memberName");
        Team team = repoHelper.findTeamByName("teamName");
        int size = team.getMembers().size();
        Assertions.assertEquals(0, size);
    }

    @Test
    public void createTeam_Should_CreateTeam_When_ArgumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> repoHelper.createTeam(VALID_TEAM_NAME));
    }

    @Test
    public void createTeam_Should_ThrowException_When_TeamnameExists() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repoHelper.createTeam(VALID_TEAM_NAME));
    }

    @Test
    public void storyNameExists_Should_ReturnTrue_When_StoryNameExists() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM));
    }

    @Test
    public void teamNameExists_Should_ReturnTrue_When_TeamNameExists() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repoHelper.createTeam(VALID_TEAM_NAME));
    }

    @Test
    public void findMemberByName_Should_ReturnMember_When_MemberNameExists() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createMember(VALID_MEMBER_NAME, VALID_TEAM_NAME);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repoHelper.createMember(VALID_MEMBER_NAME, VALID_TEAM_NAME));
    }

    @Test
    public void findBoardByName_Should_ReturnBoard_When_BoardNameExists() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        Assertions.assertThrows(AlreadyExistsException.class, () -> repoHelper.createBoard(VALID_BOARD_NAME, VALID_TEAM_NAME));
    }

    @Test
    public void getBugs_Should_getBugs() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createBug(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.HIGH, Severity.CRITICAL);
        Assertions.assertEquals(1, repoHelper.getBugs().size());
    }

    @Test
    public void getStories_Should_getStories() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        Assertions.assertEquals(1, repoHelper.getStories().size());
    }

    @Test
    public void getFeedbacks_Should_getFeedbacks() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createFeedback(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, 12);
        Assertions.assertEquals(1, repoHelper.getFeedbacks().size());
    }

    @Test
    public void getWorkItems_Should_getWorkItems() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        repoHelper.createFeedback(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, 12);
        Assertions.assertEquals(2, repoHelper.getItems().size());
    }

    @Test
    public void getMembers_Should_getMembers() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createMember(VALID_MEMBER_NAME, VALID_TEAM_NAME);
        Assertions.assertEquals(1, repoHelper.getMembers().size());
    }

    @Test
    public void getAssignables_Should_getAssignables() {
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        Bug b = repoHelper.createBug(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.ACTIVE, Priority.HIGH, Severity.CRITICAL);
        Story s = repoHelper.createStory(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        List<Assignable> expected = new ArrayList<>();
        expected.add(b);
        expected.add(s);
        Assertions.assertTrue(expected.equals(repoHelper.getAssignables()));
    }
}