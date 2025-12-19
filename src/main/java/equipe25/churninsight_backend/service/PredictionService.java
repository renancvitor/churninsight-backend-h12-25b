package equipe25.churninsight_backend.service;

import equipe25.churninsight_backend.model.PredicaoChurnEntity;
import equipe25.churninsight_backend.repository.PredictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import equipe25.churninsight_backend.dto.DataPredictResposta;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {
    @Autowired
    private PredictRepository repository;

    private final WebClient webClient;

    public DataPredictResposta prever(PredictionRequest request) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DataPredictResposta.class)
                .block();
    }

    public PredicaoChurnEntity salvar(PredicaoChurnEntity prediction){
        return repository.save(prediction);
    }

    public List<PredicaoChurnEntity> listar(){
        return repository.findAll();
    }

    public PredicaoChurnEntity listarPorId(Long id){
        var existe = repository.findById(id);
        if(existe.isPresent())
            return existe.get();
        throw new RuntimeException();
    }

}
