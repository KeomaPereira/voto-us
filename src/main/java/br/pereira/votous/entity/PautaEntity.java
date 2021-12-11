package br.pereira.votous.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pauta")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_pauta")
    private Long cdPauta;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

}
