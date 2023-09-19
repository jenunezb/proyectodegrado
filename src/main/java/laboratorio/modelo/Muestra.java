package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Muestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(nullable = false, length = 150)
    private String material;

    private String descripcion;

    private String localizacion;

    private String cantera;

    private LocalDate fechaToma;

    private LocalDate fechaRecibido;

}
