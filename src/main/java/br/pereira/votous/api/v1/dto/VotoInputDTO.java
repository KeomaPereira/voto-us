package br.pereira.votous.api.v1.dto;

import br.pereira.votous.api.v1.enums.VotoEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VotoInputDTO {

    @NotNull
    private Long cdSessao;

    @NotBlank
    private String cpf;

    @NotBlank
    private VotoEnum voto;
}