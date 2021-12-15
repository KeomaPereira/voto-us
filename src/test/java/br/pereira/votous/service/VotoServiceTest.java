package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.entity.Voto;
import br.pereira.votous.infrastructure.client.HerokuClient;
import br.pereira.votous.infrastructure.converter.VotoConverter;
import br.pereira.votous.repository.VotoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.MockUtils;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService service;

    @Mock
    private VotoRepository repository;

    @Mock
    private VotoConverter converter;

    @Mock
    private HerokuClient client;

    @Mock
    private SessaoService sessaoService;

    @Test
    void deveSalvarVotoComSucesso() throws BusinessException {
        Voto voto = MockUtils.gerarVoto();
        VotoOutputDTO votoOutputDTO = MockUtils.gerarVotoOutputDTO();
        VotoInputDTO votoInputDTO = MockUtils.gerarVotoInputDTO();

        Mockito.when(sessaoService.buscar(Mockito.any())).thenReturn(MockUtils.gerarSessaoOutputDTO());
        Mockito.when(repository.findAllByCdSessaoAndCpf(Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.when(client.buscarStatus(Mockito.any())).thenReturn(MockUtils.gerarStatusCpfDTO());
        Mockito.when(repository.save(Mockito.any())).thenReturn(voto);
        Mockito.when(converter.toVotoOutputDTO(voto)).thenReturn(votoOutputDTO);

        VotoOutputDTO dtoBuscado = service.votar(votoInputDTO);
        Assert.assertEquals(votoOutputDTO, dtoBuscado);
    }

}