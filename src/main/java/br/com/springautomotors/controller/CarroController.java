package br.com.springautomotors.controller;


import br.com.springautomotors.dto.CarroDto;
import br.com.springautomotors.exception.PlacaUnicaViolationException;
import br.com.springautomotors.mapper.CarroMapper;
import br.com.springautomotors.model.Carro;
import br.com.springautomotors.service.CarroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carro")
public class CarroController {

    private final CarroService carroService;

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
