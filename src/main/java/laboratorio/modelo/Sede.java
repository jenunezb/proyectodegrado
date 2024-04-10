package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sede implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 150)
    private String ciudad;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 70)
    private String telefono;
}
