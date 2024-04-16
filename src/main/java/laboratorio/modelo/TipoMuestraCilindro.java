package laboratorio.modelo;

public enum TipoMuestraCilindro {
    COMPRESION_2("Compresión de 2\""),
    COMPRESION_3("Compresión de 3\""),
    COMPRESION_4("Compresión de 4\""),
    COMPRESION_6("Compresión de 6\""),
    COMPRESION_CUBOS_2("Compresión de Cubos de 2\""),
    COMPRESION_CUBOS_5x5("Compresión de Cubos de 5x5\""),
    FLEXION("Flexión");

    private final String nombreLegible;

    TipoMuestraCilindro(String nombreLegible) {
        this.nombreLegible = nombreLegible;
    }

    public String getNombreLegible() {
        return nombreLegible;
    }
}

