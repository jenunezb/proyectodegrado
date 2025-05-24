package laboratorio.modelo;

import jakarta.persistence.*;
import laboratorio.modelo.ensayo.MuestraSuelos;
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
public class Obra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 150)
    private String CR;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 70)
    private String telefono;


    @ManyToMany
    @JoinColumn
    private List<Cliente> clientes;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @Column(nullable = false)
    private LocalDate fecha_inicio;

    @Column
    private LocalDate fecha_fin;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

    @OneToMany
    @JoinColumn
    private List<MuestraSuelos> muestraSuelos;

    @ManyToMany
    @JoinColumn
    private List<Ingeniero> ingenieros;

    @ManyToMany
    @JoinColumn
    private List<Digitador> digitadores;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Sede sede;

}
