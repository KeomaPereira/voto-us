package br.pereira.votous.service;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.SessaoOutputDTO;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.infrastructure.converter.SessaoConverter;
import br.pereira.votous.repository.SessaoRepository;
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
class SessaoServiceTest {

    @InjectMocks
    private SessaoService service;

    @Mock
    private SessaoRepository repository;

    @Mock
    private SessaoConverter converter;

    @Mock
    private PautaService pautaService;

    @Test
    void deveSalvarSessaoComSucesso() throws BusinessException {
        Sessao sessao = MockUtils.gerarSessao();
        SessaoOutputDTO sessaoOutputDTO = MockUtils.gerarSessaoOutputDTO();

        Mockito.when(pautaService.buscar(Mockito.any())).thenReturn(MockUtils.gerarPautaOutputDTO());
        Mockito.when(repository.save(Mockito.any())).thenReturn(sessao);
        Mockito.when(converter.toDTO(sessao)).thenReturn(sessaoOutputDTO);

        SessaoOutputDTO dtoBuscado = service.criar(1l);
        Assert.assertEquals(sessaoOutputDTO, dtoBuscado);
    }

    @Test
    void deveBuscarSessoesComSucesso() throws BusinessException {
        List<Sessao> sessoes = List.of(MockUtils.gerarSessao());
        List<SessaoOutputDTO> listaSessaoOutputDTO = List.of(MockUtils.gerarSessaoOutputDTO());

        Mockito.when(repository.findAll()).thenReturn(sessoes);
        Mockito.when(converter.toListDTO(sessoes)).thenReturn(listaSessaoOutputDTO);

        List<SessaoOutputDTO> listaBuscada = service.buscar();
        Assert.assertEquals(listaSessaoOutputDTO, listaBuscada);
    }

    @Test
    void deveBuscarSessaoComSucesso() throws BusinessException {
        Sessao sessao = MockUtils.gerarSessao();
        SessaoOutputDTO sessaoOutputDTO = MockUtils.gerarSessaoOutputDTO();

        Mockito.when(repository.findById(1l)).thenReturn(Optional.of(sessao));
        Mockito.when(converter.toDTO(sessao)).thenReturn(sessaoOutputDTO);

        SessaoOutputDTO sessaoBuscada = service.buscar(1l);
        Assert.assertEquals(sessaoOutputDTO, sessaoBuscada);
    }

}