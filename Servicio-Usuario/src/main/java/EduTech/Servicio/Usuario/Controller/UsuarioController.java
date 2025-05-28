package EduTech.Servicio.Usuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.Servicio.Usuario.Model.Usuario;
import EduTech.Servicio.Usuario.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(){
    }

    @GetMapping ResponseEntity<List<Usuario>>listar(){
        List<Usuario> Usuario = this.usuarioService.findAll();
        return Usuario.isEmpty()?ResponseEntity.noContent().build() : ResponseEntity.ok(Usuario);
    }
}
