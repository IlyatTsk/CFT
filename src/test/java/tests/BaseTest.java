package tests;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.container.FinallySequence.Builder.doFinally;
import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest extends TestNGCitrusSpringSupport {

    @Autowired
    protected HttpClient duckService;

    @Autowired
    protected SingleConnectionDataSource testDb;

    @Description("Выполнение GET запроса")
    protected void sendGetRequest(TestCaseRunner runner, String path, String queName, String queValue) {
        runner.run(http().client(duckService)
                .send()
                .get(path)
                .queryParam(queName, queValue));
    }

    @Description("Выполнение GET запроса без обязательных параметров")
    protected void sendGetRequestWithoutQueryParams(TestCaseRunner runner, String path) {
        runner.run(http().client(duckService)
                .send()
                .get(path));
    }

    @Description("Выполнение GET запроса для метода Quack")
    protected void sendGetRequestQuack(TestCaseRunner runner, String path, String id, String repetitionCount, String soundCount) {
        runner.run(http().client(duckService)
                .send()
                .get(path)
                .queryParam("id", id)
                .queryParam("repetitionCount", repetitionCount)
                .queryParam("soundCount", soundCount));
    }

    @Description("Выполнение POST запроса с помощью String")
    protected void sendPostRequest(TestCaseRunner runner, String path, String body) {
        runner.run(http().client(duckService)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }

    @Description("Выполнение POST запроса с помощью payload")
    protected void sendPostRequestWithPayloadBase(TestCaseRunner runner, String path, Object body) {
        runner.run(http().client(duckService)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ObjectMappingPayloadBuilder(body, new ObjectMapper())));
    }

    @Description("Выполнение POST запроса с помощью файла")
    protected void sendPostRequestFromFile(TestCaseRunner runner, String path, String expectedFile) {
        runner.run(http().client(duckService)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ClassPathResource(expectedFile)));
    }

    @Description("Выполнение DELETE запроса")
    protected void sendDeleteRequest(TestCaseRunner runner, String path, String queName, String queValue) {
        runner.run(http().client(duckService)
                .send()
                .delete(path)
                .queryParam(queName, queValue));
    }

    @Description("Выполнение PUT запроса")
    protected void sendPutRequest(TestCaseRunner runner, String path, String color, String height, String id, String material, String sound, String wingsState) {
        runner.run(http().client(duckService)
                .send()
                .put(path)
                .queryParam("color", color)
                .queryParam("height", height)
                .queryParam("id", id)
                .queryParam("material", material)
                .queryParam("sound", sound)
                .queryParam("wingsState", wingsState));
    }

    @Description("Валидация полученного ответа String'ой")
    protected void validateResponseWithStringBase(TestCaseRunner runner, HttpStatus statusCode, String expectedString) {
        runner.run(http().client(duckService)
                .receive()
                .response(statusCode)
                .message().type(MessageType.JSON)
                .body(expectedString));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки resources")
    protected void validateResponseWithFileFromResourcesBase(TestCaseRunner runner, HttpStatus statusCode, String expectedFile) {
        runner.run(http().client(duckService)
                .receive()
                .response(statusCode)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(expectedFile)));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки payloads")
    protected void validateResponseBodyFromPayloadsBase(TestCaseRunner runner, HttpStatus statusCode, Object expectedPayload) {
        runner.run(http().client(duckService)
                .receive()
                .response(statusCode)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }

    @Description("Экстракт id у созданной уточки")
    protected void extractIdFromResponse(TestCaseRunner runner) {
        runner.run(http().client(duckService)
                .receive()
                .response()
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract(fromBody().expression("$.id", "duckId")));
    }

    @Description("Запрос к БД")
    protected void sendDatabaseUpdate(TestCaseRunner runner, String sql) {
        runner.run(sql(testDb)
                .sqlResource(sql));
    }

    @Description("Удаление уточки из БД")
    public void sendDeleteDuckFinally(TestCaseRunner runner, String sql) {
        runner.run(doFinally().actions(runner.run(sql(testDb)
                .statement(sql))));
    }

    @Description("Валидация данных из БД")
    public void validateDuckInDatabase(TestCaseRunner runner, String id, String color, String height, String material, String sound, String wingsState) {
        runner.run(query(testDb)
                .statement("SELECT * FROM DUCK WHERE ID=" + id)
                .validate("COLOR", color)
                .validate("HEIGHT", height)
                .validate("MATERIAL", material)
                .validate("SOUND", sound)
                .validate("WINGS_STATE", wingsState));
    }
}