package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.SessaoOutputDTO;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.infrastructure.converter.SessaoConverter;
import br.pereira.votous.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessaoService {

    private SessaoRepository repository;
    private SessaoConverter converter;
    private PautaService pautaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);
    private static final String MSG_ERRO = "Ocorreu erro ao criar sessao.";
    private static final String MSG_ERRO_PAUTA_NAO_CADASTRADA = "Nao existe uma pauta cadastrada.";

    public SessaoOutputDTO criar(Long cdPauta) throws BusinessException {
        try {
            if (Objects.isNull(pautaService.buscar(cdPauta))) {
                throw new BusinessException(MSG_ERRO_PAUTA_NAO_CADASTRADA);
            }
            return converter.toDTO(repository.save(converter.toEntity(cdPauta)));
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
            Optional<Sessao> entity = repository.findById(codigo);
            return (entity.isPresent() ? converter.toDTO(entity.get()) : null);
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO + " Erro: " + e.getMessage());
        }
    }

    public List<Sessao> buscarNaoProcessados() {
        return repository.findAllNaoProcessados();
    }

    public void encerrarSessao(Sessao sessao) {
        sessao.setDataFim(LocalDateTime.now());
        repository.save(sessao);
    }

}