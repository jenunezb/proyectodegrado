package laboratorio.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)

public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String telefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ciudad ciudad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(nullable = false, length = 50)
    private String rol;

    public Usuario(Integer cedula, String nombre, @Email String email, String password, String direccion, String telefono, Ciudad ciudad, Estado estado, String rol) {
        super(cedula, nombre, email, password, rol, telefono);
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.estado = estado;
        this.rol=rol;
    }
}
