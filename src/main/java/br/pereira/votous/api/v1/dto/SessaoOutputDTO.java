package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessaoOutputDTO {

    private Long cdSessao;
    private Long cdPauta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}