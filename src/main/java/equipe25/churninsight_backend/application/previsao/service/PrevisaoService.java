package equipe25.churninsight_backend.application.previsao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import equipe25.churninsight_backend.application.api.dto.ClientRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.application.previsao.repository.PredictRepository;
import equipe25.churninsight_backend.model.previsao.Previsao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrevisaoService {
    @Autowired
    private PredictRepository repository;

    private final WebClient webClient;

    public ClienteResponse prever(ClientRequest request) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClienteResponse.class)
                .block();
    }

    public Previsao salvar(Previsao prediction) {
        return repository.save(prediction);
    }

    public List<Previsao> listar() {
        return repository.findAll();
    }

    public Previsao listarPorId(Long id) {
        var existe = repository.findById(id);
        if (existe.isPresent())
            return existe.get();
        throw new RuntimeException();
    }

}
