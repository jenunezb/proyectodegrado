package laboratorio.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Digitador extends Usuario implements Serializable {

    @JoinColumn
    @ManyToMany(mappedBy = "digitadores")
    private List<Obra> obras;

}