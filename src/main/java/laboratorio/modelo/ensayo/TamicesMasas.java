package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TamicesMasas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne
    @JoinColumn(name = "gradacion_codigo", nullable = false)
    private Gradacion gradacion;

    @Column
    private String tamiz;

    @Column
    private double masaRetenida;

}
