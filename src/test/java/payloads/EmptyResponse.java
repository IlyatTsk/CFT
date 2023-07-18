package payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmptyResponse {
}
