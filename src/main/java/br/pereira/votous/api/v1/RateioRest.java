package br.pereira.votous.api.v1;

import br.pereira.votous.api.v1.dto.RateioOutputDTO;
import br.pereira.votous.service.RateioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rateios")
@AllArgsConstructor
class RateioRest {

    private RateioService service;

    @ApiOperation(value = "Buscar rateio de votos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada"),
            @ApiResponse(code = 404, message = "Erro ao buscar"),
            @ApiResponse(code = 422, message = "Erro ao realizar a operacao")
    })
    @GetMapping
    public ResponseEntity<List<RateioOutputDTO>> buscar() {
        List<RateioOutputDTO> lista = service.buscar();
        return (!lista.isEmpty()) ? ResponseEntity.ok().body(lista) : ResponseEntity.notFound().build();
    }

}