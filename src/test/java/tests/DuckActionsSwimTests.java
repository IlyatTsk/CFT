package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckActionsSwimTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка того, что уточка поплыла")
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckSwim(runner, "${duckId}");

        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, "SwimTests/successfulSwim.json");

        duckDelete(runner, "${duckId}");
    }
}
