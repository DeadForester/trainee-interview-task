package dev.mysklad.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;

    @NotEmpty(message = "Поле имя не может быть пустым")
    @Size(max = 255, message = "Максимально допустимый размер названия продукта 255 символов")
    private String name;
    @Size(max = 4096, message = "Максимально допустимый размер описания продукта 4096 символов")
    private String description;
    @DecimalMin(value = "0", inclusive = false, message = "цена должна быть больше 0")
    private BigDecimal prise;

    private boolean availability = false;

    public Product(String name, String description, BigDecimal prise, boolean availability) {
        this.name = name;
        this.description = description;
        this.prise = prise != null ? prise : BigDecimal.ZERO;
        this.availability = availability;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }
}
