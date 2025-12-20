package equipe25.churninsight_backend.application.previsao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import equipe25.churninsight_backend.model.previsao.Previsao;

public interface PredictRepository extends JpaRepository<Previsao, Long> {
}
