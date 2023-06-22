package laboratorio.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)

public class Cliente extends Persona implements Serializable {
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
    private String cargo;


    public Cliente(Integer cedula, String nombre, @Email String email, String password, String direccion, String telefono, Ciudad ciudad, Estado estado, String cargo) {
        super(cedula, nombre, email, password, cargo, telefono);
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cargo=cargo;
    }

}
