package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario implements Serializable {

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String obra;

    @JoinColumn
    @ManyToMany(mappedBy = "clientes")
    private List<Obra> obras;

}
