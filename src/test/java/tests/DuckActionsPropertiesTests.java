package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckActionsPropertiesTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка корректных значений")
    public void successfulProperties(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);
        duckShowProperties(runner, "${duckId}");

        String expectedFile = "PropertiesTests/successfulResponse.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);
        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка корректных значений")
    public void successfulPropertiesWoodenDuck(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowWoodenActiveDuck.json");
        extractId(runner);
        duckShowProperties(runner, "${duckId}");

        String expectedFile = "PropertiesTests/successfulResponseWoodenDuck.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);
        duckDelete(runner, "${duckId}");
    }
}
