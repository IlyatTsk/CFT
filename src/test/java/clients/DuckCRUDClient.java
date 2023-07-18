package clients;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import models.Duck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import tests.EndpointConfig;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckCRUDClient {

    @Autowired
    protected HttpClient duckService;

    public void duckCreate(TestCaseRunner runner, Duck duck) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDuck = objectMapper.writeValueAsString(duck);

        runner.run(http().client(duckService)
                .send()
                .post(EndpointConfig.CREATE)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(jsonDuck));
    }

    @Description("Валидация полученного ответа String'ой метода Create /api/duck/create")
    public void validateResponseWithString(TestCaseRunner runner, String expectedFile) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body("{\n" + //проверка тела ответа
                        " \"color\": \"yellow\",\n" +
                        " \"height\": 10,\n" +
                        " \"material\": \"rubber\",\n" +
                        " \"sound\": \"quack-quack\",\n" +
                        " \"wingsState\": \"ACTIVE\"\n" +
                        "}"));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки resources метода Create /api/duck/create")
    public void validateResponseWithFileFromResources(TestCaseRunner runner, String expectedFile) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(expectedFile)));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки payloads метода Create /api/duck/create")
    public void validateResponseBodyFromPayloads(TestCaseRunner runner, Object expectedPayload) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }

    public void duckDelete(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .delete(EndpointConfig.DELETE)
                .queryParam(id));
    }

    public void duckGetAllIds(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .get(EndpointConfig.GET_ALL_IDS)
                .queryParam(id));
    }

    public void duckUpdate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        runner.run(http().client(duckService)
                .send()
                .put(EndpointConfig.UPDATE)
                .queryParam("color", color)
                .queryParam("height", height)
                .queryParam("id", id)
                .queryParam("material", material)
                .queryParam("sound", sound)
                .queryParam("wingsState", wingsState));
    }
}
