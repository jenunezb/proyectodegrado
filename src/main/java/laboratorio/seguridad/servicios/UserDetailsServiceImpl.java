package laboratorio.seguridad.servicios;

import laboratorio.modelo.Usuario;
import laboratorio.repositorios.UsuarioRepo;
import laboratorio.seguridad.modelo.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
//        if(usuario.isEmpty()){
//            Optional<Moderador> admin = adminRepo.findByEmail(email);
//            if(admin.isEmpty())
//                throw new UsernameNotFoundException("El usuario no existe");
//            return UserDetailsImpl.build(admin.get());
//        }else{
//            return UserDetailsImpl.build(usuario.get());
//        }
//    }
        return UserDetailsImpl.build(usuario.get());
}
}