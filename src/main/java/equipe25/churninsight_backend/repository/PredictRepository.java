package equipe25.churninsight_backend.repository;

import equipe25.churninsight_backend.model.PredicaoChurnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictRepository extends JpaRepository<PredicaoChurnEntity, Long> {
}
