package Exercise1.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Exercise1.model.entity.ContainerType;
import Exercise1.model.entity.Section_Product;

@Repository
public interface Section_ProductJPARepository extends JpaRepository<Section_Product,Serializable>{

}
