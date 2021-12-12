package br.pereira.votous.service;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.api.v1.enums.VotoEnum;
import br.pereira.votous.entity.RateioEntity;
import br.pereira.votous.entity.SessaoEntity;
import br.pereira.votous.infrastructure.converter.RateioConverter;
import br.pereira.votous.repository.RateioRepository;
import lombok.AllArgsConstructor;
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

    @Scheduled(cron = "${sessao-scheduler.tempo}")
    private void processar() {
        sessaoService.buscarNaoProcessados().stream().forEach(sessao -> {
            salvar(gerarRateioEntity(sessao));
            sessaoService.encerrarSessao(sessao);
        });
    }

    public RateioEntity gerarRateioEntity(SessaoEntity sessao){
        AtomicReference<Integer> votosSim = new AtomicReference<>(0);
        AtomicReference<Integer> votosNao = new AtomicReference<>(0);
        votoService.buscar(sessao.getCdPauta()).stream().forEach(votoEntity -> {
            if ((votoEntity.getVoto().equals(VotoEnum.SIM))) {
                votosSim.set(votosSim.get() + 1);
            } else {
                votosNao.set(votosNao.get() + 1);
            }
        });
        return converter.toEntity(sessao, votosSim.get(), votosNao.get());
    }

    public void salvar(RateioEntity entity) {
        rateioRepository.save(entity);
    }

    public List<RateioOutputDTO> buscar() {
        return converter.toListDTO(rateioRepository.findAll());
    }

}