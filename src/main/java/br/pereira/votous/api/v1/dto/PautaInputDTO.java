package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PautaInputDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descicao;
}
