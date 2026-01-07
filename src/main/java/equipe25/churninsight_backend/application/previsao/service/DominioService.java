package equipe25.churninsight_backend.application.previsao.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import equipe25.churninsight_backend.application.previsao.dto.Dominio;
import equipe25.churninsight_backend.model.genero.enums.GeneroEnum;
import equipe25.churninsight_backend.model.pais.enums.PaisEnum;

@Service
public class DominioService {

        public List<Dominio> listarPaises() {
                return Arrays.stream(PaisEnum.values())
                                .map(p -> new Dominio(
                                                p.getId(),
                                                p.getApiValue(),
                                                p.getLabel()))
                                .toList();
        }

        public List<Dominio> listarGeneros() {
                return Arrays.stream(GeneroEnum.values())
                                .map(g -> new Dominio(
                                                g.getId(),
                                                g.getApiValue(),
                                                g.getLabel()))
                                .toList();
        }

}
