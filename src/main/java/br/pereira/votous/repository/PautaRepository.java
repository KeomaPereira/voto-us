package br.pereira.votous.repository;

import br.pereira.votous.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository
        extends JpaRepository<Pauta, Long> {

}