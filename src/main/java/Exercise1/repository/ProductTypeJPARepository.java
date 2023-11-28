package Exercise1.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Exercise1.model.entity.ProductType;

@Repository
public interface ProductTypeJPARepository extends JpaRepository<ProductType,Serializable> {

}
