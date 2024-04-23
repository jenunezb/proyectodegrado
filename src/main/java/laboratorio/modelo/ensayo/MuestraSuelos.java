package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import laboratorio.modelo.Obra;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MuestraSuelos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(nullable = false, length = 150)
    private String material;

    private String descripcion;

    private String localizacion;

    @Column(nullable = false)
    private String cantera;
    @Column(nullable = false)
    private LocalDate fechaToma;
    @Column(nullable = false)
    private LocalDate fechaRecibido;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Obra obra;

}
