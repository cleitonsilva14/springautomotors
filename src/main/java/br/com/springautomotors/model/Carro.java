package br.com.springautomotors.model;


import br.com.springautomotors.enums.Cambio;
import br.com.springautomotors.enums.Combustivel;
import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "tb_carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "marca", length = 40, nullable = false)
    private String marca;

    @Column(name = "modelo", length = 40, nullable = false)
    private String modelo;

    @Column(name = "ano", nullable = false)
    private Integer ano;


    @Column(name = "km", nullable = false)
    private Float km;

    @Column(name = "cor", nullable = false)
    private String cor;

    @Column(name = "placa", length = 7, nullable = false, unique = true)
    private String placa;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "motor", nullable = false)
    private Float motor;

    @Enumerated(EnumType.STRING)
    @Column(name = "cambio", nullable = false)
    private Cambio cambio;


    @Column(name = "combustivel", nullable = false)
    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Column(name = "opcinais", nullable = true)
    private List<String> opcionais;

    @ElementCollection
    @CollectionTable(
            name = "tb_fotos_carro",
            joinColumns = @JoinColumn(name = "foto_id")
    )
    @Column(name = "fotos_url")
    private List<String> fotosUrl;


}

