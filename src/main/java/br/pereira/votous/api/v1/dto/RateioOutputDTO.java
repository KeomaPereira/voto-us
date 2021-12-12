package br.pereira.votous.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RateioOutputDTO {

    private Long cdRateio;
    private Long cdSessao;
    private Long cdPauta;
    private Integer qtdSim;
    private Integer qtdNao;
    private LocalDateTime dataFim;
}
