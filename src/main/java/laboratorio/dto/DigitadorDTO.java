package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record DigitadorDTO(
String cedula,
String nombre,
String ciudad,
String telefono,
String password,
String correo
) {
}