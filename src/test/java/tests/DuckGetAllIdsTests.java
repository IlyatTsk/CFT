package tests;

import clients.DuckCRUDClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import io.qameta.allure.Epic;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Тестовый класс проверок метода получения списка всех ID")
@Test(priority = 1)
public class DuckGetAllIdsTests extends DuckCRUDClient {

    @CitrusTest
    @Test(priority = 3, description = "Проверка корректного запроса метода, при создании трех уточек")
    public void successfulGetAllIds(@Optional @CitrusResource TestCaseRunner runner) {
        clearDataBase(runner, "DataSource/ClearDataBase.sql");

        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");

        duckGetAllIds(runner);

        validateResponseWithFileFromResources(runner, HttpStatus.OK, "GetAllIdsTests/successfulGetAllIdsWithThreeDucks.json");
    }

    @CitrusTest
    @Test(priority = 1, description = "Проверка вызова метода без создания уточек")
    public void successfulGetAllIdsWithoutCreateDucks(@Optional @CitrusResource TestCaseRunner runner) {
        clearDataBase(runner, "DataSource/ClearDataBase.sql");

        duckGetAllIds(runner);

        validateResponseWithString(runner, HttpStatus.OK, "[]");
    }

    @CitrusTest
    @Test(priority = 2, description = "Проверка вызова метода при создании одной уточки")
    public void successfulGetAllIdsWithOneDuck(@Optional @CitrusResource TestCaseRunner runner) {
        clearDataBase(runner, "DataSource/ClearDataBase.sql");

        duckCreate(runner, "getDuckPropertiesTest/createYellowRubberActiveDuck.json");
        extractId(runner);
        duckGetAllIds(runner);

        validateResponseWithString(runner, HttpStatus.OK, "[1]");

        duckDelete(runner, "${duckId}");
    }
}

