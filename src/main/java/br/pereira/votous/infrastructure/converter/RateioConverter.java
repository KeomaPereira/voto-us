package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.entity.RateioEntity;
import br.pereira.votous.entity.SessaoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RateioConverter {

    public RateioEntity toEntity(SessaoEntity sessao, Integer votosSim, Integer votosNao) {
        RateioEntity entity = new RateioEntity();
        entity.setCdPauta(sessao.getCdPauta());
        entity.setCdSessao(sessao.getCdSessao());
        entity.setDataFim(LocalDateTime.now());
        entity.setQtdNao(votosNao);
        entity.setQtdSim(votosSim);
        return entity;
    }

    public RateioOutputDTO toDTO (RateioEntity entity) {
        RateioOutputDTO dto = new RateioOutputDTO();
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdSessao(entity.getCdSessao());
        dto.setDataFim(entity.getDataFim());
        dto.setQtdNao(entity.getQtdNao());
        dto.setQtdSim(entity.getQtdSim());
        return dto;
    }

    public List<RateioOutputDTO> toListDTO(List<RateioEntity> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

}