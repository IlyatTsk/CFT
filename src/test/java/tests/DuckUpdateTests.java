package tests;

import clients.DuckCRUDClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckUpdateTests extends DuckCRUDClient {

    @CitrusTest
    @Test(description = "Проверка успешного обновления данных уточки")
    public void successfulUpdate(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckUpdate(runner, "yellow", "0.01", "1", "rubber", "quack", "ACTIVE");

        validateCreateDuck(runner, "${duckId}", "yellow", "0.0", "rubber", "quack", "ACTIVE");
        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка успешного обновления данных уточки со звуком meow")
    public void successfulUpdateWithSoundIsMeow(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckUpdate(runner, "yellow", "0.01", "1", "rubber", "meow", "ACTIVE");

        validateCreateDuck(runner, "${duckId}", "yellow", "0.0", "rubber", "meow", "ACTIVE");
        duckDelete(runner, "${duckId}");
    }
}
