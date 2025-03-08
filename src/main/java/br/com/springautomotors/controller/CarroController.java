package br.com.springautomotors.controller;


import br.com.springautomotors.dto.CarroDto;
import br.com.springautomotors.exception.PlacaUnicaViolationException;
import br.com.springautomotors.handler.ApiErrorMessage;
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

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Carros", description = "Operações relacionadas a carros")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carros")
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


    @Operation(
            summary = "Criar um novo carro", description = "Recurso responsável por criar um novo carro",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Criado com sucesso!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Placa já está cadastrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Erro ao tentar processar os dados enviados, pois estão inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CarroDto> save(@RequestBody @Valid CarroDto carroDto){
            var carro = carroService.saveCar(CarroMapper.toCarro(carroDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(CarroMapper.toDto(carro));
    }

    @Operation(
            summary = "Obter todas as placas",
            description = "Recurso responável por buscar todas as placas de veículos que já estão cadastradas",
            responses = {
                    @ApiResponse (
                            responseCode = "200",
                            description = "Placas encontradas",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )

    @GetMapping("/placas")
    public ResponseEntity<List<String>> findAllPlacas(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carroService.findAllPlaca());
    }


    @Operation(
            summary = "Criar novos carros por um lista", description = "Recurso responsável por vários carros de uma vez",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Criado com sucesso!",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Placa já está cadastrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Erro ao tentar processar os dados enviados, pois estão inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class))
                    )
            }
    )
    @PostMapping("/all")
    public ResponseEntity<?> saveAll(@RequestBody @Valid List<CarroDto> carroDtoList){
        //carroDtoList.stream().map((carroDto) -> CarroMapper.toCarro(carroDto)).forEach(System.out::println);

        List<Carro> carrosList = carroDtoList
                .stream()
                .map((carroDto) -> CarroMapper.toCarro(carroDto))
                .toList();

        //log.info("\033[31m{}\033[m", carrosList);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carroService.saveAll(carrosList));
    }


}
