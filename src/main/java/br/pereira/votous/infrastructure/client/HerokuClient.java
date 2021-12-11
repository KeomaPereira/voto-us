package br.pereira.votous.infrastructure.client;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.infrastructure.client.dto.StatusCpfDTO;
import br.pereira.votous.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HerokuClient {

    @Value("${user-info.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    private static final String MSG_ERRO_VALIDAR_CPF = "Ocorreu erro validar CPF.";
    private static final String MSG_CPF_INVALIDO = "CPF invalido.";
    private static final Integer STATUS_NOT_FOUND = 404;
    private static final Logger LOGGER = LoggerFactory.getLogger(VotoService.class);

    public HerokuClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StatusCpfDTO buscarStatus(String cpf) throws BusinessException {
        try {
            return restTemplate.getForObject(baseUrl + cpf, StatusCpfDTO.class);
        } catch (Exception e) {
            throw new BusinessException(buscarMsgException(e));
        }
    }

    private String buscarMsgException(Exception e) {
        if (((HttpClientErrorException.NotFound) e).getRawStatusCode() == STATUS_NOT_FOUND) {
            LOGGER.error(MSG_CPF_INVALIDO + " Erro: " + e.getMessage());
            return MSG_CPF_INVALIDO;
        }
        LOGGER.error(MSG_ERRO_VALIDAR_CPF + " Erro: " + e.getMessage());
        return MSG_ERRO_VALIDAR_CPF;
    }

}