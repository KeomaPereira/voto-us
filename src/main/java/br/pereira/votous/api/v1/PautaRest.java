package br.pereira.votous.api.v1;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.PautaInputDTO;
import br.pereira.votous.api.v1.dto.PautaOutputDTO;
import br.pereira.votous.service.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/pautas")
@AllArgsConstructor
class PautaRest {

    private PautaService service;

    @ApiOperation(value = "Criar uma pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @PostMapping
    public ResponseEntity<PautaOutputDTO> criar(@Valid @RequestBody PautaInputDTO dto) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @ApiOperation(value = "buscar pautas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada"),
            @ApiResponse(code = 404, message = "Erro ao buscar"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @GetMapping
    public ResponseEntity<List<PautaOutputDTO>> buscar() throws BusinessException {

        List<PautaOutputDTO> lista = service.buscar();
        return (!lista.isEmpty()) ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "buscar uma pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada"),
            @ApiResponse(code = 404, message = "Erro ao buscar"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<PautaOutputDTO> buscar(@PathVariable("codigo") Long codigo) throws BusinessException {

        PautaOutputDTO dto = service.buscar(codigo);
        return (Objects.nonNull(dto)) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

}