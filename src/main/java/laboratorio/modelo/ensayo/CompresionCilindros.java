package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import laboratorio.modelo.Obra;
import laboratorio.modelo.TipoMuestraCilindro;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CompresionCilindros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Obra obra;

    @Enumerated(EnumType.STRING)
    private TipoMuestraCilindro ensayo;


    @Column(unique = true)
    String numeroMuestra;

    @Column
    LocalDate fechaToma;

    @Column
    int resistencia;

    @Column
    int cantMuestras;

    @Column
    String descripcion;

    @Column
    String observaciones;

}
