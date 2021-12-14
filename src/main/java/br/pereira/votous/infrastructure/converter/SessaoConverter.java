package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.SessaoOutputDTO;
import br.pereira.votous.entity.Sessao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessaoConverter {

    public Sessao toEntity (Long cdPauta) {
        Sessao entity = new Sessao();
        entity.setCdPauta(cdPauta);
        entity.setDataInicio(LocalDateTime.now());
        return entity;
    }

    public SessaoOutputDTO toDTO (Sessao entity) {
        SessaoOutputDTO dto = new SessaoOutputDTO();
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdSessao(entity.getCdSessao());
        dto.setDataFim(entity.getDataFim());
        dto.setDataInicio(entity.getDataInicio());
        return dto;
    }

    public List<SessaoOutputDTO> toListDTO (List<Sessao> entities) {
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

}