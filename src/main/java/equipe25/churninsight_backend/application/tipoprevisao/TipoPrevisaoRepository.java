package equipe25.churninsight_backend.application.tipoprevisao;

import org.springframework.data.jpa.repository.JpaRepository;

import equipe25.churninsight_backend.model.tipopre.TipoPrevisaoEntidade;

public interface TipoPrevisaoRepository extends JpaRepository<TipoPrevisaoEntidade, Integer> {

}
