package br.pereira.votous.infrastructure.client.dto;

import br.pereira.votous.infrastructure.client.enums.StatusCpfEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StatusCpfDTO {

    private StatusCpfEnum status;

}