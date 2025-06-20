package laboratorio.dto;

import lombok.Getter;

@Getter
public enum FormaFalla {
    CERO(0),
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

    public static FormaFalla desdeValor(int valor) {
        for (FormaFalla forma : FormaFalla.values()) {
            if (forma.getValor() == valor) {
                return forma;
            }
        }
        throw new IllegalArgumentException("Valor inválido para FormaFalla: " + valor);
    }
}