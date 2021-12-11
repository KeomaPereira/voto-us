package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoOutputDTO {

    private Long cdVoto;
    private Integer cdPauta;
    private String cpf;
}