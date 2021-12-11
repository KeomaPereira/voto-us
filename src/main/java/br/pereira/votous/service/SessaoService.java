package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.SessaoOutputDTO;
import br.pereira.votous.entity.SessaoEntity;
import br.pereira.votous.infrastructure.converter.SessaoConverter;
import br.pereira.votous.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessaoService {

    private SessaoRepository repository;
    private SessaoConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);
    private static final String MSG_ERRO = "Ocorreu erro ao criar sessao.";

    public Long criar(Long cdPauta) throws BusinessException {
        try {
            return repository.save(converter.toEntity(cdPauta)).getCdSessao();
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

    public List<SessaoOutputDTO> buscar() throws BusinessException {
        try {
            return converter.toListDTO(repository.findAll());
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

    public SessaoOutputDTO buscar(Long codigo) throws BusinessException {
        try {
            Optional<SessaoEntity> entity = repository.findById(codigo);
            return (entity.isPresent() ? converter.toDTO(entity.get()) : null);
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

}