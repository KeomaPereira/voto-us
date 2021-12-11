package br.pereira.votous.api.v1.dto;

import br.pereira.votous.api.v1.enums.VotoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaInputDTO {

    private String titulo;
    private String descicao;
}
