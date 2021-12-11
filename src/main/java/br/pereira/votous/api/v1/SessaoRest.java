package br.pereira.votous.api.v1;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.SessaoOutputDTO;
import br.pereira.votous.service.SessaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/sessoes")
@AllArgsConstructor
class SessaoRest {

    private SessaoService service;

    @ApiOperation(value = "Criar uma sessao")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessao criada")
    })
    @PostMapping("/{codigoPauta}")
    public ResponseEntity<Long> criar(@PathVariable("codigoPauta") Long codigoPauta) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(codigoPauta));
    }

    @ApiOperation(value = "buscar sessoes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessao"),
            @ApiResponse(code = 404, message = "Sessao nao encontrada"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @GetMapping
    public ResponseEntity<List<SessaoOutputDTO>> buscar() throws BusinessException {

        List<SessaoOutputDTO> lista = service.buscar();
        return (!lista.isEmpty()) ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "buscar uma sessao")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessao"),
            @ApiResponse(code = 404, message = "Sessao nao encontrada"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<SessaoOutputDTO> buscar(@PathVariable("codigo") Long codigo) throws BusinessException {

        SessaoOutputDTO dto = service.buscar(codigo);
        return (Objects.nonNull(dto)) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

}