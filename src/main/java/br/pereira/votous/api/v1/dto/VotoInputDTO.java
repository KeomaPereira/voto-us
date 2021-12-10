package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInputDTO {

    private Integer cdPauta;

    private String cpf;
}