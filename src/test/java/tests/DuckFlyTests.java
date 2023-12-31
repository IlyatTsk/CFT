package tests;

import clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Flaky;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тестовый класс проверок полетов уточек")
public class DuckFlyTests extends DuckActionsClient {

    @CitrusTest
    @Test(description = "Проверка того, что уточка полетела")
    @Flaky
    public void successfulFly(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);

        duckFly(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsActive.json");
        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка того, что уточка не полетела")
    @Flaky
    public void notSuccessfulFly(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberFixedDuck.json");
        extractId(runner);

        duckFly(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsFixed.json");
        duckDelete(runner, "${duckId}");
    }

    @CitrusTest
    @Test(description = "Проверка того, что уточка полетела с помощью запроса к БД")
    @Flaky
    public void SuccessfulFlyDataBase(@Optional @CitrusResource TestCaseRunner runner) {
        clearDataBase(runner, "DataSource/ClearDataBase.sql");
        runner.variable("duckId", "1234567");
        insertDuckIntoDataBase(runner, "DataSource/InsertDuckIntoDataBase.sql");

        duckFly(runner, "${duckId}");

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "FlyTests/ResponseDuckFlyWingsStateIsActive.json");
        deleteDuckFinally(runner, "DELETE FROM DUCK WHERE ID=${duckId}");
    }
}