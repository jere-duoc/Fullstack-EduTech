package EduTech.Servicio.Curso.service;

import EduTech.Servicio.Curso.entity.Curso;
import EduTech.Servicio.Curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso editarCurso(Long id, Curso cursoActualizado){
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        
        if (cursoExistente.isPresent()){
            Curso curso = cursoExistente.get();
            curso.setNombreCurso(cursoActualizado.getNombreCurso());

            return cursoRepository.save(curso);
        }
        return null;
    }

    public List<Curso> listaCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

}
