package escola.api.repository;

import escola.api.model.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Page<Matricula> findAllBycursoId(Long id, Pageable pageable);
}
