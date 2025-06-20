package laboratorio.modelo.ensayo.aceros;

import jakarta.persistence.*;
import laboratorio.modelo.Obra;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MuestraAcero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(nullable = false, length = 150)
    private String localizacion;

    @Column(nullable = false, length = 150)
    private String fabricante;

    @Column(nullable = false, length = 150)
    private String descripcion;

    @Column(nullable = false, length = 150)
    private String designacion;

    private LocalDate fechaToma;

    @Column(nullable = false)
    private LocalDate fechaRecibido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Obra obra;
}
