package laboratorio.dto;

public record ClienteGetDTO(
        String cedula,
        String nombre,
        String ciudad,
        String telefono,
        String correo,
        String cargo
) {
}
