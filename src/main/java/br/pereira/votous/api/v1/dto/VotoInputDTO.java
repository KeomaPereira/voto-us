package br.pereira.votous.api.v1.dto;

import br.pereira.votous.api.v1.enums.VotoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInputDTO {

    private Integer cdPauta;
    private Integer cdSessao;
    private String cpf;
    private VotoEnum voto;
}