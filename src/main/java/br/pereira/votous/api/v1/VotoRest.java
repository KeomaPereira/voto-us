package br.pereira.votous.api.v1;

import br.pereira.votous.api.exception.BusinessException;
import br.pereira.votous.api.v1.dto.VotoInputDTO;
import br.pereira.votous.api.v1.dto.VotoOutputDTO;
import br.pereira.votous.service.VotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/votos")
@AllArgsConstructor
class VotoRest {

    private VotoService service;

    @ApiOperation(value = "Votar em uma pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Voto realizado"),
            @ApiResponse(code = 404, message = "CPF invalido")
    })
    @PostMapping
    public ResponseEntity<VotoOutputDTO> votar(@Valid @RequestBody VotoInputDTO dto) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.votar(dto));
    }

}