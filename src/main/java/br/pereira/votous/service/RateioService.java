package br.pereira.votous.service;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.api.v1.enums.VotoEnum;
import br.pereira.votous.entity.Rateio;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.infrastructure.converter.RateioConverter;
import br.pereira.votous.infrastructure.event.producer.RateioProducer;
import br.pereira.votous.repository.RateioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RateioService {

    private final SessaoService sessaoService;
    private final VotoService votoService;
    private final RateioRepository rateioRepository;
    private final RateioConverter converter;
    private final RateioProducer rateioProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(RateioService.class);
    private static final String MSG_ERRO_RATEAR = "Ocorreu erro ao realizar o rateio dos votos.";

    public RateioService(SessaoService sessaoService, VotoService votoService, RateioRepository rateioRepository, RateioConverter converter, RateioProducer rateioProducer) {
        this.sessaoService = sessaoService;
        this.votoService = votoService;
        this.rateioRepository = rateioRepository;
        this.converter = converter;
        this.rateioProducer = rateioProducer;
    }

    @Scheduled(cron = "${rateio.scheduler.cron}")
    public void processar() {
        try {
            sessaoService.buscarNaoProcessados().stream().forEach(sessao -> {
                Rateio rateio = gerarEntity(sessao);
                salvar(rateio);
                sessaoService.encerrarSessao(sessao);
                rateioProducer.enviar(rateio.toString());
            });
        } catch (Exception e){
            LOGGER.error(MSG_ERRO_RATEAR + " Erro: " + e.getMessage());
        }
    }

    public Rateio gerarEntity(Sessao sessao){
        AtomicReference<Integer> votosSim = new AtomicReference<>(0);
        AtomicReference<Integer> votosNao = new AtomicReference<>(0);
        votoService.buscar(sessao.getCdSessao()).stream().forEach(votoEntity -> {
            if ((votoEntity.getVoto().equals(VotoEnum.SIM))) {
                votosSim.set(votosSim.get() + 1);
            } else {
                votosNao.set(votosNao.get() + 1);
            }
        });
        return converter.toEntity(sessao, votosSim.get(), votosNao.get());
    }

    public void salvar(Rateio entity) {
        rateioRepository.save(entity);
    }

    public List<RateioOutputDTO> buscar() {
        return converter.toListDTO(rateioRepository.findAll());
    }

}