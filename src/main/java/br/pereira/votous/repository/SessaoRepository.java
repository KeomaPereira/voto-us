package br.pereira.votous.repository;

import br.pereira.votous.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository
        extends JpaRepository<SessaoEntity, Long> {

}