package laboratorio.modelo.ensayo.aceros;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tension")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Tension implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name = "long_probeta", precision = 10, scale = 4)
    private BigDecimal longProbeta;

    @Column(name = "peso_probeta", precision = 10, scale = 4)
    private BigDecimal pesoProbeta;

    @Column(name = "diam_inicial", precision = 10, scale = 4)
    private BigDecimal diamInicial;

    @Column(name = "diam_final", precision = 10, scale = 4)
    private BigDecimal diamFinal;

    @Column(name = "area_inicial", precision = 10, scale = 4)
    private BigDecimal areaInicial;

    @Column(name = "area_final", precision = 10, scale = 4)
    private BigDecimal areaFinal;

    @Column(name = "long_calibrada_inicial", precision = 10, scale = 4)
    private BigDecimal longCalibradaInicial;

    @Column(name = "long_calibrada_final", precision = 10, scale = 4)
    private BigDecimal longCalibradaFinal;

    @Column(name = "carga_max", precision = 10, scale = 4)
    private BigDecimal cargaMax;

    @Column(name = "carga_fluencia", precision = 10, scale = 4)
    private BigDecimal cargaFluencia;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MuestraAcero muestraAcero;

    
}
