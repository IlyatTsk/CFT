package clients;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import tests.EndpointConfig;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckActionsClient {
    @Autowired
    protected HttpClient duckService;

    public void duckFly(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .get(EndpointConfig.FLY)
                .queryParam(id));
    }

    @Description("Валидация полученного ответа String'ой метода Fly /api/duck/action/fly")
    public void validateResponseWithString(TestCaseRunner runner, String expectedString) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(expectedString));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки resources метода Fly /api/duck/action/fly")
    public void validateResponseWithFileFromResources(TestCaseRunner runner, String expectedFile) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(expectedFile)));
    }

    @Description("Валидация полученного ответа с передачей ответа из папки payloads метода Fly /api/duck/action/fly")
    public void validateResponseBodyFromPayloads(TestCaseRunner runner, Object expectedPayload) {
        runner.run(http().client(duckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(expectedPayload, new ObjectMapper())));
    }

    public void duckShowProperties(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .get(EndpointConfig.PROPERTIES)
                .queryParam(id));
    }

    public void duckQuack(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .get(EndpointConfig.QUACK)
                .queryParam(id));
    }

    public void duckSwim(TestCaseRunner runner, String id) {
        runner.run(http().client(duckService)
                .send()
                .get(EndpointConfig.SWIM)
                .queryParam(id));
    }
}
