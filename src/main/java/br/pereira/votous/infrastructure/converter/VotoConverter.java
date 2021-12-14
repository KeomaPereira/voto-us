package br.pereira.votous.infrastructure.converter;

import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.entity.Voto;
import org.springframework.stereotype.Component;

@Component
public class VotoConverter {

    public Voto toEntity (VotoInputDTO dto) {
        Voto entity = new Voto();
        entity.setCpf(dto.getCpf());
        entity.setVoto(dto.getVoto());
        entity.setCdSessao(dto.getCdSessao());
        return entity;
    }

    public VotoOutputDTO toVotoOutputDTO (Voto entity) {
        VotoOutputDTO dto = new VotoOutputDTO();
        dto.setCdVoto(entity.getCdVoto());
        dto.setCpf(entity.getCpf());
        dto.setVoto(entity.getVoto());
        dto.setCdSessao(entity.getCdSessao());
        return dto;
    }

}