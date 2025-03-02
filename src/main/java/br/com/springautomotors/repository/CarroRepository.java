package br.com.springautomotors.repository;

import br.com.springautomotors.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    @Query("SELECT c.placa FROM Carro c")
    List<String> findAllPlaca();

}