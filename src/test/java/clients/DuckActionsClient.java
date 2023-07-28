package clients;

import com.consol.citrus.TestCaseRunner;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import tests.BaseTest;
import tests.EndpointConfig;

public class DuckActionsClient extends BaseTest {

    @Step("Выполнение запроса метода /api/duck/action/fly")
    public void duckFly(TestCaseRunner runner, String id) {
        sendGetRequest(runner, EndpointConfig.FLY, "id", id);
    }

    @Step("Выполнение запроса метода /api/duck/action/properties")
    public void duckShowProperties(TestCaseRunner runner, String id) {
        sendGetRequest(runner, EndpointConfig.PROPERTIES, "id", id);
    }

    @Step("Выполнение запроса метода /api/duck/action/quack")
    public void duckQuack(TestCaseRunner runner, String id, String repetitionCount, String soundCount) {
        sendGetRequestQuack(runner, EndpointConfig.QUACK, id, repetitionCount, soundCount);
    }

    @Step("Выполнение запроса метода /api/duck/action/swim")
    public void duckSwim(TestCaseRunner runner, String id) {
        sendGetRequest(runner, EndpointConfig.SWIM, "id", id);
    }

    @Step("Выполнение запроса метода /api/duck/create с помощью файла")
    public void duckCreate(TestCaseRunner runner, String expectedFile) {
        sendPostRequestFromFile(runner, EndpointConfig.CREATE, expectedFile);
    }

    @Step("Выполнение запроса метода /api/duck/delete")
    public void duckDelete(TestCaseRunner runner, String id) {
        sendDeleteRequest(runner, EndpointConfig.DELETE, "id", id);
    }

    @Step("Валидация полученного ответа String'ой")
    public void validateResponseWithString(TestCaseRunner runner, HttpStatus statusCode, String expectedString) {
        validateResponseWithStringBase(runner, statusCode, expectedString);
    }

    @Step("Валидация полученного ответа с передачей ответа из папки resources")
    public void validateResponseWithFileFromResources(TestCaseRunner runner, HttpStatus statusCode, String expectedFile) {
        validateResponseWithFileFromResourcesBase(runner, statusCode, expectedFile);
    }

    @Step("Валидация полученного ответа с передачей ответа из папки payloads")
    public void validateResponseBodyFromPayloads(TestCaseRunner runner, HttpStatus statusCode, Object expectedPayload) {
        validateResponseBodyFromPayloadsBase(runner, statusCode, expectedPayload);
    }

    @Step("Экстракт id")
    public void extractId(TestCaseRunner runner) {
        extractIdFromResponse(runner);
    }

    @Step("Удаление уточки из БД")
    public void deleteDuckFinally(TestCaseRunner runner, String sql) {
        sendDeleteDuckFinally(runner, sql);
    }

    @Step("Добавление уточки в БД")
    public void insertDuckIntoDataBase(TestCaseRunner runner, String sql) {
        sendDatabaseUpdate(runner, sql);
    }

    @Step("Очищение БД")
    public void clearDataBase(TestCaseRunner runner, String sql) {
        sendDatabaseUpdate(runner, sql);
    }
}
