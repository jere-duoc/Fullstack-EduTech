package EduTech.Servicio.Usuario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import EduTech.Servicio.Usuario.Model.Usuario;
import EduTech.Servicio.Usuario.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
    }

    public List<Usuario>findAll(){
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(long id){
        return (Usuario)this.usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario){
        return(Usuario)this.usuarioRepository.save(usuario);
    }

    public void delete (Long id){
        this.usuarioRepository.deleteById(id);
    } 

}
