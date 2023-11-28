package Exercise1.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Exercise1.model.entity.ContainerType;
import Exercise1.model.entity.Product;
import Exercise1.model.entity.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Repository
public interface ProductJPARepository extends JpaRepository<Product,Serializable> {

	public Optional<Product> findByNameAndBatch(String name, String batch);
	
	
}
