package br.pereira.votous.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rateio")
public class Rateio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_rateio")
    private Long cdRateio;

    @Column(name = "cd_sessao")
    private Long cdSessao;

    @Column(name = "cd_pauta")
    private Long cdPauta;

    @Column(name = "qtd_sim")
    private Integer qtdSim;

    @Column(name = "qtd_nao")
    private Integer qtdNao;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

}
