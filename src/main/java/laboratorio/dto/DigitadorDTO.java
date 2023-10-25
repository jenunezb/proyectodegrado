package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record DigitadorDTO(
String cedula,
String nombre,
int ciudad,
String telefono,
String password,
String correo
) {
}