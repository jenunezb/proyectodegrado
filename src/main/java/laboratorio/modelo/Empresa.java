package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Empresa {

    @Id
    //nit de la empresa
    private int nit;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String telefono;

    @OneToMany(mappedBy = "empresa")
    private List<Obra> obras;
}
