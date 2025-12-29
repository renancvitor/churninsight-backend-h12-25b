package equipe25.churninsight_backend.utils;

import equipe25.churninsight_backend.application.api.dto.ClienteRequest;
import equipe25.churninsight_backend.application.api.dto.ClienteResponse;
import equipe25.churninsight_backend.model.genero.GeneroEntidade;
import equipe25.churninsight_backend.model.genero.enums.GeneroEnum;
import equipe25.churninsight_backend.model.nivelrisco.NivelRiscoEntidade;
import equipe25.churninsight_backend.model.nivelrisco.enums.NivelRiscoEnum;
import equipe25.churninsight_backend.model.pais.PaisEntidade;
import equipe25.churninsight_backend.model.pais.enums.PaisEnum;
import equipe25.churninsight_backend.model.previsao.Previsao;
import equipe25.churninsight_backend.model.tipoprevisao.TipoPrevisaoEntidade;
import equipe25.churninsight_backend.model.tipoprevisao.enums.TipoPrevisaoEnum;

public class FabricaObjetosTeste {

    public static GeneroEntidade criarGeneroMale() {
        return GeneroEntidade.fromEnum(GeneroEnum.MALE);
    }

    public static GeneroEnum generoMaleEnum() {
        return GeneroEnum.MALE;
    }

    public static NivelRiscoEntidade criarNivelBaixo() {
        return NivelRiscoEntidade.fromEnum(NivelRiscoEnum.BAIXO);
    }

    public static NivelRiscoEnum nivelRiscoEnumBaixo() {
        return NivelRiscoEnum.BAIXO;
    }

    public static PaisEntidade criarPaisFrance() {
        return PaisEntidade.fromEnum(PaisEnum.FRANCE);
    }

    public static PaisEnum paisEnumFrance() {
        return PaisEnum.FRANCE;
    }

    public static TipoPrevisaoEntidade criarPrevisaoVaiContinuar() {
        return TipoPrevisaoEntidade.fromEnum(TipoPrevisaoEnum.VAI_CONTINUAR);
    }

    public static TipoPrevisaoEnum previsaoEnumVaiContinuar() {
        return TipoPrevisaoEnum.VAI_CONTINUAR;
    }

    public static Previsao criarPrevisao() {
        Previsao previsao = new Previsao();
        previsao.setId(1L);
        previsao.setPrevisao(criarPrevisaoVaiContinuar());
        previsao.setNivelRisco(criarNivelBaixo());
        previsao.setProbabilidade(7.12);
        previsao.setRecomendacao("Recomendação.");

        return previsao;
    }

    public static ClienteResponse criarResponse() {
        Previsao previsao = criarPrevisao();

        ClienteResponse response = new ClienteResponse(
                TipoPrevisaoEnum.fromJson(previsao.getPrevisao().getTipoPrevisao()),
                previsao.getProbabilidade(),
                NivelRiscoEnum.fromJson(previsao.getNivelRisco().getNivelRiscoNome()),
                previsao.getRecomendacao());

        return response;
    }

    public static ClienteRequest criarRequest() {
        ClienteRequest request = new ClienteRequest(
                12,
                paisEnumFrance(),
                generoMaleEnum(),
                29,
                21,
                7.07,
                2000.0);

        return request;
    }

}
