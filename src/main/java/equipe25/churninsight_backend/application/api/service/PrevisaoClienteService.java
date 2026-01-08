package equipe25.churninsight_backend.application.api.service;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import equipe25.churninsight_backend.application.api.dto.BatchJobResponse;
import equipe25.churninsight_backend.application.api.dto.BatchStatusResponse;
import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrevisaoClienteService {

    private final RestTemplate restTemplate;

    private static final String PYTHON_API_URL = "https://churn-hackathon.onrender.com";

    public ClienteResponse prever(ClienteRequest request) {
        return restTemplate.postForObject(
                PYTHON_API_URL + "/previsao",
                request,
                ClienteResponse.class);
    }

    public BatchJobResponse enviarBatch(Resource file) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(
                PYTHON_API_URL + "/previsao-lote",
                request,
                BatchJobResponse.class);
    }

    public BatchStatusResponse consultarStatus(String jobId) {
        return restTemplate.getForObject(
                PYTHON_API_URL + "/previsao-lote/status/" + jobId,
                BatchStatusResponse.class);
    }

    public Resource baixarResultado(String jobId) {
        return restTemplate.getForObject(
                PYTHON_API_URL + "/previsao-lote/download/" + jobId,
                Resource.class);
    }

}
