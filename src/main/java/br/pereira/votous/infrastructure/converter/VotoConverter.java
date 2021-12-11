package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.entity.VotoEntity;
import org.springframework.stereotype.Component;

@Component
public class VotoConverter {

    public VotoEntity toEntity (VotoInputDTO dto) {
        VotoEntity entity = new VotoEntity();
        entity.setCdPauta(dto.getCdPauta());
        entity.setCpf(dto.getCpf());
        entity.setVoto(dto.getVoto());
        entity.setCdSessao(dto.getCdSessao());
        return entity;
    }

    public VotoOutputDTO toVotoOutputDTO (VotoEntity entity) {
        VotoOutputDTO dto = new VotoOutputDTO();
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdVoto(entity.getCdVoto());
        dto.setCpf(entity.getCpf());
        dto.setVoto(entity.getVoto());
        dto.setCdSessao(entity.getCdSessao());
        return dto;
    }

}