package Exercise1.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Exercise1.model.entity.Section;

@Repository
public interface SectionJPARepository extends JpaRepository<Section,Serializable>{

}
