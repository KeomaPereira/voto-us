package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.entity.VotoEntity;
import br.pereira.votous.infrastructure.client.HerokuClient;
import br.pereira.votous.infrastructure.client.enums.StatusCpfEnum;
import br.pereira.votous.infrastructure.converter.VotoConverter;
import br.pereira.votous.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VotoService {

    private VotoRepository repository;
    private VotoConverter converter;
    private HerokuClient herokuClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);
    private static final String MSG_ERRO_VOTAR = "Ocorreu erro ao votar.";
    private static final String MSG_NAO_PERMITIDO = "CPF nao permite votacao.";

    public VotoOutputDTO votar(VotoInputDTO dto) throws BusinessException {
        try {
            validarCpf(dto.getCpf());
            return converter.toVotoOutputDTO(repository.save(converter.toEntity(dto)));
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO_VOTAR + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO_VOTAR + " Erro: " + e.getMessage());
        }
    }

    public void validarCpf(String cpf) throws BusinessException {
        if (herokuClient.buscarStatus(cpf).getStatus().equals(StatusCpfEnum.UNABLE_TO_VOTE)) {
            LOGGER.error(MSG_NAO_PERMITIDO + "CPF: " + cpf);
            throw new BusinessException(MSG_NAO_PERMITIDO);
        }
    }

    public List<VotoEntity> buscar(Long cdPauta) {
        return repository.findAllByCdPauta(cdPauta);
    }

}