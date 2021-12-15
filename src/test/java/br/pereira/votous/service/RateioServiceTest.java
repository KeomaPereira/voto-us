package br.pereira.votous.service;

import br.pereira.votous.entity.Rateio;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.entity.Voto;
import br.pereira.votous.infrastructure.converter.RateioConverter;
import br.pereira.votous.repository.RateioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.MockUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RateioServiceTest {

    @InjectMocks
    private RateioService service;

    @Mock
    private RateioRepository repository;

    @Mock
    private RateioConverter converter;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private VotoService votoService;

    @Test
    void deveRatearVotosComSucesso() {
        List<Sessao> sessoes = List.of(MockUtils.gerarSessao());
        List<Voto> votos = List.of(MockUtils.gerarVoto());
        Rateio rateio = MockUtils.gerarRateio();

        Mockito.when(sessaoService.buscarNaoProcessados()).thenReturn(sessoes);
        Mockito.when(votoService.buscar(Mockito.any())).thenReturn(votos);
        Mockito.when(converter.toEntity(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(rateio);

        service.processar();
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

}