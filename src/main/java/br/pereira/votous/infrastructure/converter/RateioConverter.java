package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.entity.Rateio;
import br.pereira.votous.entity.Sessao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RateioConverter {

    public Rateio toEntity(Sessao sessao, Integer votosSim, Integer votosNao) {
        Rateio entity = new Rateio();
        entity.setCdPauta(sessao.getCdPauta());
        entity.setCdSessao(sessao.getCdSessao());
        entity.setDataFim(LocalDateTime.now());
        entity.setQtdNao(votosNao);
        entity.setQtdSim(votosSim);
        return entity;
    }

    public RateioOutputDTO toDTO (Rateio entity) {
        RateioOutputDTO dto = new RateioOutputDTO();
        dto.setCdRateio(entity.getCdRateio());
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdSessao(entity.getCdSessao());
        dto.setDataFim(entity.getDataFim());
        dto.setQtdNao(entity.getQtdNao());
        dto.setQtdSim(entity.getQtdSim());
        return dto;
    }

    public List<RateioOutputDTO> toListDTO(List<Rateio> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

}