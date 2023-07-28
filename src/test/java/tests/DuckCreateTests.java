package tests;

import clients.DuckCRUDClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import org.springframework.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import payloads.DuckProperties;

public class DuckCreateTests extends DuckCRUDClient {

    DuckProperties firstDuck = new DuckProperties().color("yellow").height(1).sound("quack").material("rubber").wingsState("ACTIVE");
    DuckProperties secondDuck = new DuckProperties().color("yellow").height(2).sound("quack").material("rubber").wingsState("ACTIVE");
    DuckProperties thirdDuck = new DuckProperties().color("yellow").height(3).sound("quack").material("rubber").wingsState("ACTIVE");
    DuckProperties fourthDuck = new DuckProperties().color("yellow").height(4).sound("quack").material("rubber").wingsState("ACTIVE");
    DuckProperties fifthDuck = new DuckProperties().color("yellow").height(5).sound("quack").material("rubber").wingsState("ACTIVE");

    @CitrusTest
    @Test(description = "Проверка корректно созданной уточки")
    public void successfulCreate(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckShowProperties(runner, "${duckId}");

        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, "PropertiesTests/successfulResponse.json");

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка создания уточки с полем height = 1")
    public void successfulCreateWithHeightIsOne(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuckWithHeightIsOne.json");
        extractId(runner);

        duckShowProperties(runner, "${duckId}");

        validateResponseWithFileFromResourcesBase(runner, HttpStatus.OK, "PropertiesTests/successfulResponseWithHeightIsOne.json");

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка создания уточки с полем WingState = FIXED")
    public void successfulCreateWithWingStateIsFixed(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberFixedDuck.json");
        extractId(runner);

        duckShowProperties(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "PropertiesTests/successfulResponseWithWingStateIsFixed.json");

        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка создания двух одинаковых уточек")
    public void successfulCreateSameDuck(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "PropertiesTests/successfulTwoYellowRubberActiveDuck.json");
    }

    @CitrusTest
    @Test(dataProvider = "duckList")
    @CitrusParameters({"body", "response", "runner"})
    public void successfulCreateFiveDucks(Object body, String response, @Optional @CitrusResource TestCaseRunner runner) {
        duckCreateWithPayload(runner, body);

        validateResponseWithFileFromResources(runner, HttpStatus.OK, response);
    }

    @DataProvider(name = "duckList")
    public Object[][] DuckProvider() {
        return new Object[][]{
                {firstDuck, "ParamCreateTests/firstDuck.json", null},
                {secondDuck, "ParamCreateTests/secondDuck.json", null},
                {thirdDuck, "ParamCreateTests/thirdDuck.json", null},
                {fourthDuck, "ParamCreateTests/fourthDuck.json", null},
                {fifthDuck, "ParamCreateTests/fifthDuck.json", null}
        };
    }

    @CitrusTest
    @Test(description = "Создание уточки с проверкой данных в БД")
    public void successfulCreateAndChekFromDataBase(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        validateCreateDuck(runner, "${duckId}", "yellow", "0.0", "rubber", "quack", "ACTIVE");
        duckDelete(runner, "${duckId}");
    }
}