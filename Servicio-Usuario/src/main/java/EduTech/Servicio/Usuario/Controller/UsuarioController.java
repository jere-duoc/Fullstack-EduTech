package EduTech.Servicio.Usuario.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EduTech.Servicio.Usuario.Model.Usuario;
import EduTech.Servicio.Usuario.Service.UsuarioService;


class CredencialesDTO {
    private String run;
    private String password;

    public String getRun() {
        return run;
    }
    public void setRun(String run) {
        this.run = run;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

class UsuarioResponseDTO {
    private Integer id;
    private String run;
    private String nombre;
    private Integer edad;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.run = usuario.getRun();
        this.nombre = usuario.getNombre();
        this.edad = usuario.getEdad();
    }

    public Integer getId() {
        return id;
    }

    public String getRun() {
        return run;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    
}

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity <?> registrarUsuario(@RequestBody Usuario usuario) { 
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(new UsuarioResponseDTO(nuevoUsuario), HttpStatus.CREATED); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/validar-credenciales")
    public ResponseEntity <?> validarCredenciales(@RequestBody CredencialesDTO credenciales) {
        Optional<Usuario> usuarioOpt = usuarioService.validarCredenciales(credenciales.getRun(),credenciales.getPassword());
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuarioOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Run o contraseña incorrectos.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>>listar(){
        List<Usuario> usuarios = this.usuarioService.findAll();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuarioOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        

        Optional<Usuario> usuarioOpt = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuarioOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id + " para actualizar.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
          
            return ResponseEntity.noContent().build(); 
        } else {
          
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }
}
