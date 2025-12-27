package equipe25.churninsight_backend.application.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoClienteService {

    private final RestTemplate restTemplate;

    private static final String PYTHON_API_URL = "https://churn-hackathon.onrender.com/previsao";

    public ClienteResponse prever(ClienteRequest request) {
        return restTemplate.postForObject(
                PYTHON_API_URL,
                request,
                ClienteResponse.class);
    }
}
