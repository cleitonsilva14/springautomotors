package br.com.springautomotors.service;

import br.com.springautomotors.exception.PlacaUnicaViolationException;
import br.com.springautomotors.model.Carro;
import br.com.springautomotors.repository.CarroRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional(readOnly = true)
    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro saveCar(Carro carro) {
        try {
            return carroRepository.save(carro);
        } catch (DataIntegrityViolationException exception) {
            throw new PlacaUnicaViolationException(String.format("Placa [%s] já está cadastrada", carro.getPlaca()));
        }
    }
}
