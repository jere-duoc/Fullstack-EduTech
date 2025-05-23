package EduTech.Servicio.Usuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.Servicio.Usuario.ServicioUsuario;

@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    private ServicioUsuario UsuarioController;

    public UsuarioController(){
    }

    @GetMapping ResponseEntity<List<Usuario>>listar(){
        List<Usuario> Usuario = this.ServicioUsuario.findAll();
        return Usuario.isEmpty()?ResponseEntity.noContent().build() : ResponseEntity.ok(Usuario);
    }
}
