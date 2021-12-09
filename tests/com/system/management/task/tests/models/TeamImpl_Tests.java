package com.system.management.task.tests.models;

import com.system.management.task.models.BoardImpl;
import com.system.management.task.models.MemberImpl;
import com.system.management.task.models.TeamImpl;
import com.system.management.task.models.contracts.Board;
import com.system.management.task.models.contracts.Member;
import com.system.management.task.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamImpl_Tests {
    private Team t;

    @BeforeEach
    void init() {
        this.t = new TeamImpl("teamName");
    }

    @Test
    public void TeamImpl_Should_ImplementTeamInterface() {
        Assertions.assertTrue(t instanceof Team);
    }

    @Test
    public void constructor_Should_CreateTeam_When_ArgumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new TeamImpl("teamName");
            Assertions.assertEquals("teamName", t.getName());
        });
    }
    @Test
    public void addMember_Should_AddMember() {
        Member m = new MemberImpl("memberName");
        t.addMember(m);
        Assertions.assertEquals(1, t.getMembers().size());
        Assertions.assertEquals(1, t.getHistory().size());
    }
    @Test
    public void getMembers_Should_getMembers() {
        Member m = new MemberImpl("memberName");
        t.addMember(m);
        Assertions.assertEquals(1, t.getMembers().size());
    }
    @Test
    public void getBoards_Should_getBoards() {
        Board b = new BoardImpl("boardname");
        t.addBoard(b);
        Assertions.assertEquals(1, t.getBoards().size());
    }
    @Test
    public void getName_Should_getname() {
        Assertions.assertEquals("teamName", t.getName());
    }
    @Test
    public void printBoards_Should_printBoards() {
        Board b = new BoardImpl("boardname");
        Board b1 = new BoardImpl("boardname1");
        t.addBoard(b);
        t.addBoard(b1);
        String s = "boardname\nboardname1";
        Assertions.assertEquals(s, t.printBoards());
    }

}