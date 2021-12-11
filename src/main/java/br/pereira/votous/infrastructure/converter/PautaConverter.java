package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.PautaInputDTO;
import br.pereira.votous.api.v1.dto.PautaOutputDTO;
import br.pereira.votous.entity.PautaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaConverter {

    public PautaEntity toEntity (PautaInputDTO dto) {
        PautaEntity entity = new PautaEntity();
        entity.setDescricao(dto.getDescicao());
        entity.setTitulo(dto.getTitulo());
        return entity;
    }

    public PautaOutputDTO toOutputDTO (PautaEntity entity) {
        PautaOutputDTO dto = new PautaOutputDTO();
        dto.setCdPauta(entity.getCdPauta());
        dto.setDescicao(entity.getDescricao());
        dto.setTitulo(entity.getTitulo());
        return dto;
    }

    public List<PautaOutputDTO> toListaOutputDTO (List<PautaEntity> entities) {
        return entities.stream().map(entity -> toOutputDTO(entity)).collect(Collectors.toList());
    }

}