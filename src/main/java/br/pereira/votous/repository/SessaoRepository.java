package br.pereira.votous.repository;

import br.pereira.votous.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository
        extends JpaRepository<Sessao, Long> {

    @Query(value = "SELECT * FROM sessao s WHERE s.data_fim IS NULL",  nativeQuery = true)
    List<Sessao> findAllNaoProcessados();

}