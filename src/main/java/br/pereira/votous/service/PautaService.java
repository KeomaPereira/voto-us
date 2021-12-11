package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.PautaInputDTO;
import br.pereira.votous.api.v1.dto.PautaOutputDTO;
import br.pereira.votous.entity.PautaEntity;
import br.pereira.votous.infrastructure.converter.PautaConverter;
import br.pereira.votous.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PautaService {

    private PautaRepository repository;
    private PautaConverter pautaConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);
    private static final String MSG_ERRO = "Ocorreu erro ao criar pauta.";

    public PautaOutputDTO criar(PautaInputDTO dto) throws BusinessException {
        try {
            return pautaConverter.toOutputDTO(repository.save(pautaConverter.toEntity(dto)));
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

    public List<PautaOutputDTO> buscar() throws BusinessException {
        try {
            return pautaConverter.toListOutputDTO(repository.findAll());
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

    public PautaOutputDTO buscar(Long codigo) throws BusinessException {
        try {
            Optional<PautaEntity> entity = repository.findById(codigo);
            return (entity.isPresent() ? pautaConverter.toOutputDTO(entity.get()) : null);
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

}