package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Obra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 70)
    private String telefono;

    @ManyToMany(mappedBy = "obras")
    private List<Usuario> usuarios;

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private Date fecha_inicio;

    @Column
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

}
