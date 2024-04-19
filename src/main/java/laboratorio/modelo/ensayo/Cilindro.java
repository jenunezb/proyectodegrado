package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import laboratorio.dto.FormaFalla;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cilindro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CompresionCilindros compresionCilindros;

    @Column
    float peso;

    @Column
    float carga;

    @Enumerated
    FormaFalla formaFalla;

    @Column
    int edad;

    @Column
    LocalDate fechaFalla;
}