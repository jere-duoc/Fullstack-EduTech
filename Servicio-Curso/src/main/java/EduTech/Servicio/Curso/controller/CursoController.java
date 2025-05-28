package EduTech.Servicio.Curso.controller;

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

import EduTech.Servicio.Curso.entity.Curso;
import EduTech.Servicio.Curso.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping // get
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listaCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);

    } 

    @PostMapping // crear 
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevCurso = cursoService.crearCurso(curso);
        if (nuevCurso != null){
            return new ResponseEntity<>(nuevCurso, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}") // actualizar
    public ResponseEntity<Curso> editarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            Curso curs = cursoService.obtenerCursoPorId(id);
            curs.setIdCurso(id);
            curs.setNombreCurso(curso.getNombreCurso());
            cursoService.crearCurso(curs);
            return ResponseEntity.ok(curs);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            cursoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
