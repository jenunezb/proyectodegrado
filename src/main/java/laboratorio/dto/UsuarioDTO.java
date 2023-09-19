package laboratorio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import laboratorio.modelo.Ciudad;
import laboratorio.modelo.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    private Integer cedula;

    @NotNull
    @NotBlank
    @Length(max = 150, message = "El nombre debe tener máximo 100 caracteres")
    private String nombre;

    @NotNull
    @NotBlank
    @Length(max = 100, message = "El correo debe tener máximo 100 caracteres")
    private String email;

    @NotNull
    @NotBlank
    @Length(max = 50, message = "La contraseña debe tener máximo 50 caracteres")
    private String password;

    @NotNull
    @NotBlank
    @Length(max = 100, message = "La dirección debe tener máximo 100 caracteres")
    private String direccion;

    @NotNull
    @NotBlank
    @Length(max = 12, message = "El teléfono debe tener máximo 12 caracteres")
    private String telefono;

    private String rol;

    private Estado estado;

}
