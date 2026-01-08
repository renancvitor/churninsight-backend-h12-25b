package equipe25.churninsight_backend.application.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BatchJobResponse(
        @JsonProperty("job_id") String jobId,
        String status) {
}
