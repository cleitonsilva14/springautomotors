package br.com.springautomotors.dto;

import br.com.springautomotors.enums.Cambio;
import br.com.springautomotors.enums.Combustivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CarroDto {

    @NotBlank(message = "informar corretamente")
    String marca;

    @NotBlank(message = "informar corretamente")
    String modelo;

    @Min(value = 1970, message = "ano do veículo deve ser maior ou igual à 1970")
    @NotNull(message = "não pode ser NULL")
    @DateTimeFormat(pattern = "YYYY")
    Integer ano;

    @DecimalMin("0.0")
    Float km;

    @NotBlank(message = "informar corretamente")
    String cor;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}$", message = "Placa informada fora do padrão MERCOSUL")
    @NotBlank(message = "informar corretamente")
    String placa;

    @DecimalMin("0.0")
    BigDecimal preco;

    @DecimalMin("1.0")
    Float motor;

    @NotBlank
    String cambio;

    @NotBlank
    String combustivel;

    List<String> opcionais;

    List<String> fotosUrl;
}
