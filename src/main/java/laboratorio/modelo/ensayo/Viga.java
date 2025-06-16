package laboratorio.modelo.ensayo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Viga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private int codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CompresionCilindros compresionCilindros;

    @Column
    private int edad;

    @Column
    private Float ancho;

    @Column
    private Float fondo;

    @Column
    private float carga; // p(kN) calculado así
    //ejemplo: 51,72 = 51720

    @Column
    private int l;//L= luz entre apoyos

    @Column
    private int a; //distancia desde el apoyo hasta la carga (mm)

    @Column
    private LocalDate fechaFalla;
}
