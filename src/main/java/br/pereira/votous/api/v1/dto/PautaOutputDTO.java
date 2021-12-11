package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaOutputDTO {

    private Long cdPauta;
    private String titulo;
    private String descicao;
}
