package laboratorio.repositorios;

import laboratorio.modelo.Cliente;
import laboratorio.modelo.Digitador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    Cliente findByCorreo(String correo);

    Optional<Cliente> findByCedula(String cedula);

    boolean existsByCedula(String cedula);
}