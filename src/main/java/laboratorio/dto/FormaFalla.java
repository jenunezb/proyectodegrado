package laboratorio.dto;

import lombok.Getter;

@Getter
public enum FormaFalla {
    UNO(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6);

    private final int valor;

    FormaFalla(int valor) {
        this.valor = valor;
    }
}