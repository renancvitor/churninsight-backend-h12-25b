package equipe25.churninsight_backend.repository;

import equipe25.churninsight_backend.model.PredicaoChurnEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictRepository extends JpaRepository<PredicaoChurnEntidade, Long> {
}
