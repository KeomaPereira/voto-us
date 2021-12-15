package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.PautaInputDTO;
import br.pereira.votous.api.v1.dto.PautaOutputDTO;
import br.pereira.votous.entity.Pauta;
import br.pereira.votous.infrastructure.converter.PautaConverter;
import br.pereira.votous.repository.PautaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.MockUtils;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Mock
    private PautaConverter converter;

    @Test
    void deveSalvarPautaComSucesso() throws BusinessException {
        Pauta pauta = MockUtils.gerarPauta();
        PautaInputDTO pautaInputDTO = MockUtils.gerarPautaInputDTO();
        PautaOutputDTO pautaOutputDTO = MockUtils.gerarPautaOutputDTO();

        Mockito.when(repository.save(Mockito.any())).thenReturn(pauta);
        Mockito.when(converter.toEntity(pautaInputDTO)).thenReturn(pauta);
        Mockito.when(converter.toOutputDTO(pauta)).thenReturn(pautaOutputDTO);

        PautaOutputDTO dtoBuscado = service.criar(pautaInputDTO);
        Assert.assertEquals(pautaOutputDTO, dtoBuscado);
    }

    @Test
    void deveBuscarPautasComSucesso() throws BusinessException {
        List<Pauta> pautas = List.of(MockUtils.gerarPauta());
        List<PautaOutputDTO> listaPautaOutputDTO = List.of(MockUtils.gerarPautaOutputDTO());

        Mockito.when(repository.findAll()).thenReturn(pautas);
        Mockito.when(converter.toListOutputDTO(pautas)).thenReturn(listaPautaOutputDTO);

        List<PautaOutputDTO> listaBuscada = service.buscar();
        Assert.assertEquals(listaPautaOutputDTO, listaBuscada);
    }

    @Test
    void deveBuscarPautaComSucesso() throws BusinessException {
        Pauta pauta = MockUtils.gerarPauta();
        PautaOutputDTO pautaOutputDTO = MockUtils.gerarPautaOutputDTO();

        Mockito.when(repository.findById(1l)).thenReturn(Optional.of(pauta));
        Mockito.when(converter.toOutputDTO(pauta)).thenReturn(pautaOutputDTO);

        PautaOutputDTO pautaBuscada = service.buscar(1l);
        Assert.assertEquals(pautaOutputDTO, pautaBuscada);
    }

}