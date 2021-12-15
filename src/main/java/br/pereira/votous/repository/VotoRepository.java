package br.pereira.votous.repository;

import br.pereira.votous.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository
        extends JpaRepository<Voto, Long> {

    List<Voto> findAllByCdSessao(Long cdPauta);

    List<Voto> findAllByCdSessaoAndCpf(Long cdPauta, String cpf);

}