package utils;

import br.pereira.votous.api.v1.dto.*;
import br.pereira.votous.api.v1.enums.VotoEnum;
import br.pereira.votous.entity.Pauta;
import br.pereira.votous.entity.Rateio;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.entity.Voto;
import br.pereira.votous.infrastructure.client.dto.StatusCpfDTO;
import br.pereira.votous.infrastructure.client.enums.StatusCpfEnum;

import java.time.LocalDateTime;

public class MockUtils {

    public static final String TEXTO = "Teste";
    public static final Long CODIGO = 1L;
    public static final Integer QUANTIDADE = 1;
    public static final String MSG_ERRO_VOTO_JA_REALIZADO = "Ocorreu erro ao votar. Erro: Voto ja realizado nesta sessao.";

    public static Pauta gerarPauta() {
        Pauta pauta = new Pauta();
        pauta.setCdPauta(CODIGO);
        pauta.setDescricao(TEXTO);
        pauta.setTitulo(TEXTO);
        return pauta;
    }

    public static PautaInputDTO gerarPautaInputDTO() {
        PautaInputDTO pauta = new PautaInputDTO();
        pauta.setDescicao(TEXTO);
        pauta.setTitulo(TEXTO);
        return pauta;
    }

    public static PautaOutputDTO gerarPautaOutputDTO() {
        PautaOutputDTO pauta = new PautaOutputDTO();
        pauta.setCdPauta(CODIGO);
        pauta.setDescicao(TEXTO);
        pauta.setTitulo(TEXTO);
        return pauta;
    }

    public static Sessao gerarSessao() {
        Sessao sessao = new Sessao();
        sessao.setCdPauta(CODIGO);
        sessao.setCdSessao(CODIGO);
        sessao.setDataFim(LocalDateTime.now());
        sessao.setDataInicio(LocalDateTime.now());
        return sessao;
    }

    public static SessaoOutputDTO gerarSessaoOutputDTO() {
        SessaoOutputDTO sessao = new SessaoOutputDTO();
        sessao.setCdPauta(CODIGO);
        sessao.setCdSessao(CODIGO);
        sessao.setDataFim(LocalDateTime.now());
        sessao.setDataInicio(LocalDateTime.now());
        return sessao;
    }

    public static Voto gerarVoto() {
        Voto voto = new Voto();
        voto.setCdSessao(CODIGO);
        voto.setCdVoto(CODIGO);
        voto.setVoto(VotoEnum.SIM);
        voto.setCpf(TEXTO);
        return voto;
    }

    public static VotoOutputDTO gerarVotoOutputDTO() {
        VotoOutputDTO voto = new VotoOutputDTO();
        voto.setCdSessao(CODIGO);
        voto.setCdVoto(CODIGO);
        voto.setVoto(VotoEnum.SIM);
        voto.setCpf(TEXTO);
        return voto;
    }

    public static VotoInputDTO gerarVotoInputDTO() {
        VotoInputDTO voto = new VotoInputDTO();
        voto.setCdSessao(CODIGO);
        voto.setVoto(VotoEnum.SIM);
        voto.setCpf(TEXTO);
        return voto;
    }

    public static StatusCpfDTO gerarStatusCpfDTO() {
        StatusCpfDTO statusCpfDTO = new StatusCpfDTO();
        statusCpfDTO.setStatus(StatusCpfEnum.ABLE_TO_VOTE);
        return statusCpfDTO;
    }


    public static Rateio gerarRateio() {
        Rateio rateio = new Rateio();
        rateio.setCdPauta(CODIGO);
        rateio.setCdRateio(CODIGO);
        rateio.setCdSessao(CODIGO);
        rateio.setDataFim(LocalDateTime.now());
        rateio.setQtdNao(QUANTIDADE);
        rateio.setQtdSim(QUANTIDADE);
        return rateio;
    }

}