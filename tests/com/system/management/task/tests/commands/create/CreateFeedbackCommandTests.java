package com.system.management.task.tests.commands.create;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.CreateFeedbackCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.EXPECTED_NUMBER_OF_ARGUMENTS_FEEDBACK;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;
import static com.system.management.task.tests.utils.TestUtilities.initializeListWithSize;


public class CreateFeedbackCommandTests {
    private RepositoryHelper repoHelper;
    private Command command;
    private List<String> parameters;

    @BeforeEach
    public void init() {
        repoHelper = new RepositoryHelper(new TaskManagementRepositoryImpl());
        repoHelper.createTeam(VALID_TEAM_NAME);
        repoHelper.createBoard(VALID_TEAM_NAME, VALID_BOARD_NAME);
        command = new CreateFeedbackCommand(repoHelper);
        this.parameters = new ArrayList<String>();

    }


    @Test
    public void executeCommand_Should_ThrowException_whenInvalidCountParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(Arrays.asList(VALID_TEAM_NAME, VALID_BOARD_NAME)));
    }

    @Test
    public void execute_should_throwException_when_missingParameters() {
        parameters = initializeListWithSize(EXPECTED_NUMBER_OF_ARGUMENTS_FEEDBACK - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }
    @Test
    public void execute_should_throwException_when_passedUnparsableStatus() {

        parameters = List.of(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, "","1");


        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }
    @Test
    public void execute_should_throwException_when_unparsableRating() {

//        taskManagementRepository = new TaskManagementRepositoryImpl();
        parameters.add(VALID_TEAM_NAME);
        parameters.add(VALID_BOARD_NAME);
        parameters.add(VALID_TITLE);
        parameters.add(VALID_DESCRIPTION);
        parameters.add(String.valueOf(Status.NOT_DONE));
        parameters.add("invalid");



        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }

}
