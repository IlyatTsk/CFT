package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckFlyTests extends DuckActionsClient {

//    private String duckId;
//
//    @BeforeTest
//    public void setup(@CitrusResource TestRunner runner) {
//        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
//        extractId(runner);
//    }
//
//    @Test(description = "Проверка того, что уточка полетела")
//    public void successfulFly(@CitrusResource TestRunner runner) {
//        duckFly(runner, "${duckId}");
//
//        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsActive.json");
//    }
//
//    @AfterTest
//    public void tearDown(@CitrusResource TestRunner runner) {
//        duckDelete(runner, "${duckId}");
//    }
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