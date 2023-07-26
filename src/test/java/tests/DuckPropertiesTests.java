package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckPropertiesTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка корректных значений")
    public void successfulProperties(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckShowProperties(runner, "${duckId}");

        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, "PropertiesTests/successfulResponse.json");

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка корректных значений")
    public void successfulPropertiesWoodenDuck(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowWoodenActiveDuck.json");
        extractId(runner);

        duckShowProperties(runner, "${duckId}");

        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, "PropertiesTests/successfulResponseWoodenDuck.json");

        duckDelete(runner, "${duckId}");
    }
}
