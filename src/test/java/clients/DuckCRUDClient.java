package clients;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Duck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.MultiValueMap;
import tests.EndpointConfig;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckCRUDClient {

    @Autowired
    protected HttpClient duckService;

    public void duckCreate(TestCaseRunner runner,Duck duck) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonDuck = objectMapper.writeValueAsString(duck);

        runner.run(http().client(duckService)
                .send()
                .post(EndpointConfig.CREATE)
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(jsonDuck));
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
