package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.converter.VotoConverter;
import br.pereira.votous.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotoService {

    private VotoRepository repository;

    private VotoConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

    private static final String MSG_ERRO_VOTAR = "Ocorreu erro ao votar.";

    public VotoOutputDTO votar(VotoInputDTO dto) throws BusinessException {
        try {
            return converter.toVotoOutputDTO(
                    repository.save(converter.toEntity(dto.getCdPauta(), dto.getCpf())));
        } catch (Exception e) {
            LOGGER.error(MSG_ERRO_VOTAR + " Erro: " + e.getMessage());
            throw new BusinessException(MSG_ERRO_VOTAR);
        }
    }

}