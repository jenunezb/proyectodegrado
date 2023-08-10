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

public class Usuario implements Serializable {

    @Id
    private int id;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ciudad ciudad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToMany
    private List<Obra> obras;

    @Column(nullable = false)
    private String nombre;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String rol;

    @Column(nullable = false)
    private  String telefono;

    public Usuario(Integer id, String nombre, @Email String email, String password, String direccion, String telefono, Ciudad ciudad, Estado estado, String rol) {
        this.id=id;
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.estado = estado;
        this.rol=rol;
    }
}
