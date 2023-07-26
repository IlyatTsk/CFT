package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckQuackTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка того, что уточка крякает 1 раз")
    public void successfulQuack(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);
        duckQuack(runner, "${duckId}", "1", "1");

        String expectedFile = "QuackTests/successfulSoundOneTime.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка того, что уточка крякает 5 раз")
    public void successfulQuackRepetitionCountFiveTimes(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);
        duckQuack(runner, "${duckId}", "5", "1");

        String expectedFile = "QuackTests/successfulQuackRepetitionCountFiveTimes.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка того, что количество звуков у уточки равно 5")
    public void successfulQuackSoundCountFiveTimes(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);
        duckQuack(runner, "${duckId}", "1", "5");

        String expectedFile = "QuackTests/successfulQuackSoundCountFiveTimes.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка пустого значения поля sound")
    public void successfulQuackWithoutSound(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuckWithoutSound.json");
        extractId(runner);
        duckQuack(runner, "${duckId}", "1", "1");

        String expectedFile = "QuackTests/successfulQuackWithoutSound.json";
        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, expectedFile);

        duckDelete(runner, "${duckId}");

    }
}
