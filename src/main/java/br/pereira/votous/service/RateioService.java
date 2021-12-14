package br.pereira.votous.service;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.api.v1.enums.VotoEnum;
import br.pereira.votous.entity.Rateio;
import br.pereira.votous.entity.Sessao;
import br.pereira.votous.infrastructure.converter.RateioConverter;
import br.pereira.votous.repository.RateioRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Component
public class RateioService {

    private final SessaoService sessaoService;
    private final VotoService votoService;
    private final RateioRepository rateioRepository;
    private final RateioConverter converter;

    private static final Logger LOGGER = LoggerFactory.getLogger(RateioService.class);
    private static final String MSG_ERRO_RATEAR = "Ocorreu erro ao realizar o rateio dos votos.";

    @Scheduled(cron = "${rateio.scheduler.cron}")
    private void processar() {
        try {
            sessaoService.buscarNaoProcessados().stream().forEach(sessao -> {
                salvar(gerarEntity(sessao));
                sessaoService.encerrarSessao(sessao);
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