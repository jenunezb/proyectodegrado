package laboratorio.dto;


import laboratorio.modelo.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioGetDTO {

    private int codigo;

    private String nombre;

    private String email;

    private String telefono;

}
