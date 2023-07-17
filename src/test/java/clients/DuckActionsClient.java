package clients;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
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
