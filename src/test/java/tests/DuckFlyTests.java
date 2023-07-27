package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.TestRunner;
import org.testng.annotations.*;

public class DuckFlyTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка того, что уточка полетела")
    public void successfulFly(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckFly(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsActive.json");
        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка того, что уточка не полетела")
    public void notSuccessfulFly(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberFixedDuck.json");
        extractId(runner);

        duckFly(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsFixed.json");
        duckDelete(runner, "${duckId}");
    }
}