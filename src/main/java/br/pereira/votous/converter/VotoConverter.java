package br.pereira.votous.converter;

import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.entity.VotoEntity;
import org.springframework.stereotype.Component;

@Component
public class VotoConverter {

    public VotoEntity toEntity (Integer cdPauta, String cpf) {
        VotoEntity entity = new VotoEntity();
        entity.setCdPauta(cdPauta);
        entity.setCpf(cpf);
        return entity;
    }

    public VotoOutputDTO toVotoOutputDTO (VotoEntity entity) {
        VotoOutputDTO dto = new VotoOutputDTO();
        dto.setCdPauta(entity.getCdPauta());
        dto.setCdVoto(entity.getCdVoto());
        dto.setCpf(entity.getCpf());
        return dto;
    }

}