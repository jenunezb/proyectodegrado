package laboratorio.servicios.implementacion;

import laboratorio.dto.UsuarioDTO;
import laboratorio.dto.UsuarioGetDTO;
import laboratorio.modelo.Estado;
import laboratorio.modelo.Usuario;
import laboratorio.repositorios.UsuarioRepo;
import laboratorio.servicios.excepciones.AttributeException;
import laboratorio.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    @Override
    public int crearUsuario(UsuarioDTO c) throws Exception {
        if(!estaDisponible(c.getEmail())){
            throw new AttributeException("El correo "+c.getEmail()+" ya está en uso");
        }
        if(!validarId(c.getCedula())){
            throw new AttributeException("La cédula "+c.getCedula()+" ya está en uso");
        }
        Usuario cliente = new Usuario();
        cliente.setId(c.getCedula());
        cliente.setNombre( c.getNombre() );
        cliente.setRol(c.getRol());
        cliente.setPassword(c.getPassword());
        cliente.setEstado(c.getEstado());
        cliente.setEmail( c.getEmail());
        cliente.setDireccion( c.getDireccion() );
        cliente.setTelefono( c.getTelefono() );
        cliente.setCiudad(c.getCiudad());

//        emailServicio.enviarEmail(new EmailDTO(
//                "Creación de cuenta en Unimarket",
//                "Su cuenta ha sido creada exitosamente, debe esperar a que un" +
//                        " moderador acepte su registro en unos minutos podrá " +
//                        "acceder a su cuenta",
//                c.getEmail()));

        return usuarioRepo.save( cliente ).getId();
    }

    @Override
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception{

/*
          TODO Validar que el correo no se repita
*/

        validarExiste(codigoUsuario);

        Usuario usuario = convertir(usuarioDTO);
        usuario.setId(codigoUsuario);

        return convertir( usuarioRepo.save(usuario) );
    }

    @Override
    public int eliminarUsuario(int codigo) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(codigo);
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El código " + codigo + " no está asociado a ningún cliente");
        }
        Usuario usuario = usuarioOptional.get();
        usuarioRepo.delete(usuario);
        return codigo;
    }

    @Override
    public UsuarioGetDTO obtenerUsuario(int codigoUsuario) throws Exception{
        return convertir( obtener(codigoUsuario) );
    }

    @Override
    public int cedulaUsuario(String cedulaUsuario) throws Exception{
        return convertir( obtenerCedula(cedulaUsuario)).getCodigo();
    }

    public Usuario obtener(int codigoUsuario) throws Exception{
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }

    public Usuario obtenerCedula(String cedulaUsuario) throws Exception{
        Optional<Usuario> usuario = usuarioRepo.findByEmail(cedulaUsuario);

        if(usuario.isEmpty() ){
            throw new Exception("El email "+cedulaUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }


    @Override
    public List<UsuarioGetDTO> listarTodos() {
        List<UsuarioGetDTO> convertidos= new ArrayList<>();
        for (Usuario usuario : usuarioRepo.findAll()) {
            convertidos.add(convertir(usuario));
            System.out.println("pasa");
        }
        return convertidos;
    }

    private void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

    }


    private UsuarioGetDTO convertir(Usuario usuario){

        UsuarioGetDTO usuarioDTO = new UsuarioGetDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getCiudad());

        return usuarioDTO;
    }

    private Usuario convertir(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getCedula());
        usuario.setNombre( usuarioDTO.getNombre() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setDireccion( usuarioDTO.getDireccion() );
        usuario.setTelefono( usuarioDTO.getTelefono() );
        usuario.setPassword( usuarioDTO.getPassword() );
        usuario.setCiudad( usuarioDTO.getCiudad());
        usuario.setEstado(Estado.INACTIVO);

        return usuario;
    }

    public boolean estaDisponible(String email) {
        Optional<Usuario> cliente = usuarioRepo.findByEmail(email);
        return cliente.isEmpty();
    }

    public boolean validarId(int id){
        if(usuarioRepo.findById(id).isEmpty()){
            return true;
        }
        return false;
    }

}