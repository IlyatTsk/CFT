package tests;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;

public class EndpointConfig {

    public static final String CREATE = "/api/duck/create";
    public static final String DELETE = "/api/duck/delete";
    public static final String GET_ALL_IDS = "/api/duck//api/duck/getAllIds";
    public static final String UPDATE = "/api/duck/update";

    public static final String FLY = "/api/duck/action/fly";
    public static final String PROPERTIES = "/api/duck/action/properties";
    public static final String QUACK = "/api/duck/action/quack";
    public static final String SWIM = "/api/duck/action/swim";

    @Bean
    public HttpClient duckService (){
        return new HttpClientBuilder()
                .requestUrl("http://localhost:2222")
                .build();
    }
}
