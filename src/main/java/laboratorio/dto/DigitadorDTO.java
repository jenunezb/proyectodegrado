package laboratorio.dto;

import laboratorio.modelo.ensayo.enums.Ciudad;

public record DigitadorDTO(
String cedula,
String nombre,
Ciudad ciudad,
String telefono,
String password,
String correo
) {
}
