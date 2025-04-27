package laboratorio.modelo.ensayo.Aceros;

import jakarta.persistence.*;
import laboratorio.modelo.Obra;
import laboratorio.modelo.ensayo.Muestra;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "resaltes")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resalte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @Column
    private String norma;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_medicion")
    private Date fechaMedicion;

    @Column
    private double altura;

    @Column
    private double espaciamiento;

    @Column(name = "espaciamiento_longitudinal_grafiles")
    private boolean espaciamientoLongitudinalGrafiles;

    @Column
    private double separacion;

    @Column
    private int angulo;

    @Column(name = "resaltes_direccion_inversa")
    private boolean resaltesDireccionInversa;

    @Column(name = "longitud_probeta")
    private double longitudProbeta;

    @Column(name = "peso_probeta")
    private double pesoProbeta;

    // Relaciones

    @ManyToOne(optional = false)
    @JoinColumn(name = "muestra_id", nullable = false)
    private Muestra muestra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "obra_id", nullable = false)
    private Obra obra;
}