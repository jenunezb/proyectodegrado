package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Gradacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @OneToOne
    @JoinColumn(nullable = false)
    private MuestraSuelos muestraSuelos;

    @Column
    private LocalDate fechaFalla;

    @OneToMany(mappedBy = "gradacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TamicesMasas> tamicesMasasList;

    @Column
    private double peso1;


}
