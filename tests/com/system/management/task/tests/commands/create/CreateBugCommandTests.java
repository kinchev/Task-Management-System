package com.system.management.task.tests.commands.create;

import com.system.management.task.commands.contracts.Command;
import com.system.management.task.commands.creation.CreateBugCommand;
import com.system.management.task.core.RepositoryHelper;
import com.system.management.task.core.TaskManagementRepositoryImpl;
import com.system.management.task.core.contracts.TaskManagementRepository;
import com.system.management.task.exceptions.InvalidSeverityException;
import com.system.management.task.models.BugImpl;
import com.system.management.task.models.contracts.Bug;
import com.system.management.task.models.enums.Priority;
import com.system.management.task.models.enums.Severity;
import com.system.management.task.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.system.management.task.commands.creation.CreateBugCommand.EXPECTED_NUMBER_OF_ARGUMENTS_BUG;
import static com.system.management.task.tests.utils.TestData.BoardImpl.VALID_BOARD_NAME;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_DESCRIPTION;
import static com.system.management.task.tests.utils.TestData.TaskBase.VALID_TITLE;
import static com.system.management.task.tests.utils.TestData.TeamImpl.VALID_TEAM_NAME;
import static com.system.management.task.tests.utils.TestUtilities.initializeListWithSize;

public class CreateBugCommandTests {
    private List<String> parameters;
    private TaskManagementRepository taskManagementRepository;
    private RepositoryHelper repositoryHelper;
    private Command command;
    private Bug b;

    @BeforeEach
    public void Before() {
        this.parameters = new ArrayList<String>();
        this.repositoryHelper = new RepositoryHelper(taskManagementRepository);
        this.command = new CreateBugCommand(repositoryHelper);
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        b = new BugImpl(1, VALID_TITLE, "descriptionbug", Status.ACTIVE, Priority.HIGH, Severity.CRITICAL);
    }


    @Test
    public void execute_should_throwException_when_missingParameters() {
        parameters = initializeListWithSize(EXPECTED_NUMBER_OF_ARGUMENTS_BUG - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }


    @Test
    public void execute_should_throwException_when_passedUnparsableSeverity() {

        parameters = List.of(VALID_TEAM_NAME, VALID_BOARD_NAME, VALID_TITLE, VALID_DESCRIPTION, String.valueOf(Status.ACTIVE), String.valueOf(Priority.LOW), "12");


        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(parameters));
    }

    @Test
    public void changeSeverity_Should_ThrowException_When_SeverityIsInvalid() {
        Assertions.assertThrows(InvalidSeverityException.class,
                () -> b.changeSeverity(Severity.CRITICAL));
    }
}

