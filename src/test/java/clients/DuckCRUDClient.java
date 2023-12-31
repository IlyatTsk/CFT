package clients;

import com.consol.citrus.TestCaseRunner;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import tests.BaseTest;
import tests.EndpointConfig;

public class DuckCRUDClient extends BaseTest {

    @Step("Выполнение запроса метода /api/duck/create с помощью файла")
    public void duckCreate(TestCaseRunner runner, String expectedFile) {
        sendPostRequestFromFile(runner, EndpointConfig.CREATE, expectedFile);
    }

    @Step("Выполнение запроса метода /api/duck/create с помощью файла")
    public void duckCreateWithPayload(TestCaseRunner runner, Object body) {
        sendPostRequestWithPayloadBase(runner, EndpointConfig.CREATE, body);
    }

    @Step("Выполнение запроса метода /api/duck/delete")
    public void duckDelete(TestCaseRunner runner, String id) {
        sendDeleteRequest(runner, EndpointConfig.DELETE, "id", id);
    }

    @Step("Выполнение запроса метода /api/duck//api/duck/getAllIds")
    public void duckGetAllIds(TestCaseRunner runner) {
        sendGetRequestWithoutQueryParams(runner, EndpointConfig.GET_ALL_IDS);
    }

    @Step("Выполнение запроса метода /api/duck/update")
    public void duckUpdate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        sendPutRequest(runner, EndpointConfig.UPDATE, color, height, id, material, sound, wingsState);
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

    @Step("Выполнение запроса метода /api/duck/action/properties")
    public void duckShowProperties(TestCaseRunner runner, String id) {
        sendGetRequest(runner, EndpointConfig.PROPERTIES, "id", id);
    }

    @Step("Экстракт id")
    public void extractId(TestCaseRunner runner) {
        extractIdFromResponse(runner);
    }

    @Step("Очищение БД")
    public void clearDataBase(TestCaseRunner runner, String sql) {
        sendDatabaseUpdate(runner, sql);
    }

    @Step("Валидация данных из БД")
    public void validateCreateDuck(TestCaseRunner runner, String id, String color, String height, String material, String sound, String wingsState) {
        validateDuckInDatabase(runner, id, color, height, material, sound, wingsState);
    }
}
