package br.pereira.votous.entity;

import br.pereira.votous.api.v1.enums.VotoEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_voto")
    private Long cdVoto;

    @Column(name = "cpf")
    @NotNull
    private String cpf;

    @Column(name = "cd_sessao")
    @NotNull
    private Long cdSessao;

    @Column(name = "voto")
    @NotNull
    private VotoEnum voto;
}