package br.pereira.votous.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "lancamento")
public class VotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_voto")
    private Long cdVoto;

    @Column(name = "cpf")
    @NotNull
    private String cpf;

    @Column(name = "cd_pauta")
    @NotNull
    private Integer cdPauta;
}