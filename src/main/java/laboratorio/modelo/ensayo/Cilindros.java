package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import laboratorio.dto.FormaFalla;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cilindros  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @ManyToOne
    @JoinColumn
    private CompresionCilindros compresionCilindros;

    @Column
    float peso;

    @Column
    float carga;

    @Enumerated
    FormaFalla formaFalla;
}