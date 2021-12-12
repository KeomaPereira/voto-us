package br.pereira.votous.repository;

import br.pereira.votous.entity.RateioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateioRepository
        extends JpaRepository<RateioEntity, Long> {

}