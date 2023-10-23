package laboratorio.modelo.ensayo.enums;

public enum Ciudad {
    BOGOTA("Bogotá"),
    MEDELLIN("Medellín"),
    CALI("Cali"),
    BARRANQUILLA("Barranquilla"),
    ARMENIA("Armenia"),
    PEREIRA("Pereira"),
    CALARCA("Calarcá"),
    MANIZALES("Manizales");

    private final String nombreLegible;

    Ciudad(String nombreLegible) {
        this.nombreLegible = nombreLegible;
    }

    public String getNombreLegible() {
        return nombreLegible;
    }
}
