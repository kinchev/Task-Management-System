package com.system.management.task.tests.models;

import com.system.management.task.models.*;
import com.system.management.task.models.contracts.*;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Size;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;

public class BoardImpl_Tests {

    private Board board;

    @BeforeEach
    void init() {
        this.board = new BoardImpl(VALID_BOARD_NAME);
    }

    @Test
    public void BoardImpl_Should_ImplementTaskInterface() {
        BoardImpl board = new BoardImpl(VALID_BOARD_NAME);

        Assertions.assertTrue(board instanceof Board);
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsInvalid() {
        String invalidName = "n".repeat(ModelConstants.BOARD_NAME_LEN_MIN - 1);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TeamImpl(invalidName));
    }

    @Test
    public void constructor_Should_CreateBoard_When_ArgumentsAreValid() {
        Assertions.assertDoesNotThrow(() -> {
            new BoardImpl(VALID_BOARD_NAME);
            Assertions.assertEquals(VALID_BOARD_NAME, board.getName());
        });
    }

    @Test
    public void addWorkItem_Should_AddWorkItem() {
        Story s = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        board.addWorkItem(s);
        Assertions.assertEquals(1, board.getWorkItems().size());
        Assertions.assertEquals(1, board.getActivityHistory().size());
    }

    @Test
    public void removeWorkItem_Should_RemoveWorkItem() {
        Story s = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        board.addWorkItem(s);
        board.removeWorkItem(s);
        Assertions.assertEquals(0, board.getWorkItems().size());
    }

    @Test
    public void getActivityHistory_Should_GetActiviyHistory() {
        Story s = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        board.addWorkItem(s);
        board.removeWorkItem(s);
        Assertions.assertEquals(2, board.getActivityHistory().size());
    }

    @Test
    public void getWorkItems_Should_GetItems() {
        Story s = new StoryImpl(1, VALID_TITLE, VALID_DESCRIPTION, Status.DONE, Priority.HIGH, Size.MEDIUM);
        board.addWorkItem(s);
        Assertions.assertEquals(1, board.getWorkItems().size());
    }
}
