package br.com.springautomotors.repository;

import br.com.springautomotors.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {
}