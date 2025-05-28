package EduTech.Servicio.Usuario.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import EduTech.Servicio.Usuario.Model.Usuario;
import EduTech.Servicio.Usuario.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return this.usuarioRepository.findById(id);
    }

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        if (usuarioRepository.findByRun(usuario.getRun()).isPresent()) {
            throw new Exception("El RUN '" + usuario.getRun() + "' ya esta registrado."  );
        }
        
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.usuarioRepository.save(usuario);
    }

    public Optional<Usuario> validarCredenciales(String run, String rawPassword) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByRun(run);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(rawPassword, usuario.getPassword())) {
                return usuarioOpt;
            }
        }
        return Optional.empty();
    }
    public void delete (Integer id) {
        this.usuarioRepository.deleteById(id);
    }
    public Usuario save(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

        public Optional<Usuario> actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        
        return usuarioRepository.findById(id)
            .map(usuarioExistente -> {
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
                usuarioExistente.setEdad(usuarioActualizado.getEdad());
                if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                    usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                }
                

                return usuarioRepository.save(usuarioExistente);
            });
    }
    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false; 
    }


} 
