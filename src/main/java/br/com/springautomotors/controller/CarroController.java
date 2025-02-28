package br.com.springautomotors.controller;


import br.com.springautomotors.dto.CarroDto;
import br.com.springautomotors.exception.PlacaUnicaViolationException;
import br.com.springautomotors.mapper.CarroMapper;
import br.com.springautomotors.model.Carro;
import br.com.springautomotors.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "Operações relacionadas a carros")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carro")
public class CarroController {

    private final CarroService carroService;

    @Operation(
            summary = "Obter todos os carros cadastrados", description = "Recurso responsável para obter todos os carros!",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Carros encontrados!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Carro.class
                                    )
                            )
                    )
            }
    )

    @GetMapping
    public ResponseEntity<List<Carro>> getAll(){

        return ResponseEntity.status(HttpStatus.OK).body(carroService.findAll());

    }

    @PostMapping
    public ResponseEntity<CarroDto> save(@RequestBody @Valid CarroDto carroDto){


            var carro = carroService.saveCar(CarroMapper.toCarro(carroDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(CarroMapper.toDto(carro));


    }


}
