package br.pereira.votous.repository;

import br.pereira.votous.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository
        extends JpaRepository<VotoEntity, Long> {

}