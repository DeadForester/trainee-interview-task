package dev.mysklad.web.DTO;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;

    @NotEmpty(message = "Поле имя не может быть пустым")
    @Size(max = 255, message = "Максимально допустимый размер названия продукта 255 символов")
    private String name;
    @Size(max = 4096, message = "Максимально допустимый размер описания продукта 4096 символов")
    private String description;
    @DecimalMin(value = "0", inclusive = false, message = "цена должна быть больше 0")
    private BigDecimal prise;
    private boolean availability = false;

}
