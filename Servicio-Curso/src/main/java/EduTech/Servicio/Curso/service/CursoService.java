package EduTech.Servicio.Curso.service;

import EduTech.Servicio.Curso.entity.Curso;
import EduTech.Servicio.Curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso editarCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public List<Curso> listaCursos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id).get();
    }

    public void delete(long id){
        cursoRepository.deleteById(id);
    }
}
