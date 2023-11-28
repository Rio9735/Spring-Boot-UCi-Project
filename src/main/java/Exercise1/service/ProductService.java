package Exercise1.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import Exercise1.model.entity.ContainerType;
import Exercise1.model.entity.Product;
import Exercise1.model.entity.ProductType;
import Exercise1.model.entity.Section;
import Exercise1.model.entity.Section_Product;
import Exercise1.model.entity.embeddedId.Section_ProductId;
import Exercise1.repository.ProductJPARepository;
import Exercise1.repository.ProductTypeJPARepository;
import Exercise1.repository.SectionJPARepository;
import Exercise1.repository.Section_ProductJPARepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class ProductService {

	@Autowired
	ProductTypeJPARepository ptypeRepo;
	
	@Autowired
	ProductJPARepository productRepo;
	
	@Autowired
	SectionJPARepository sectionRepo;
	
	@Autowired
	Section_ProductJPARepository spRepo;
	
	
	public void createOrUpdateProduct( Product product, int sectionId, int cant) throws Exception {
		
		List<Product> existingProducts = findProductsByCriteria(product);
		Section section = sectionRepo.findById(sectionId).get();
		Section_Product newSectionProduct;
		
		if(section.getProductType().getId()!=product.getProductType().getId())
			throw new Exception("Different Product Type");
			
			
		if(existingProducts.size()>0) {
			product= existingProducts.get(0);
			
			Section_ProductId id = new Section_ProductId();
			id.setIdProduct(product.getId());
			id.setIdSection(sectionId);
			
			Optional<Section_Product> existingSectionProduct= spRepo.findById(id);
			
			
			if(existingSectionProduct.isPresent()) {
				newSectionProduct= existingSectionProduct.get();
				newSectionProduct.setCant(newSectionProduct.getCant()+ cant);
				spRepo.save(newSectionProduct);
			}
			else {
				newSectionProduct=new Section_Product(product,section,cant);
				spRepo.save(newSectionProduct);
			}
			
		}
		else {
			
			
			productRepo.saveAndFlush(product);
			newSectionProduct= new Section_Product(product,section,cant);
			spRepo.save(newSectionProduct);
			
		}
		
		
	}
	
	
	
	
	
	@PersistenceContext
	private EntityManager em;

		
	public List<Product> findProductsByCriteria(Product product) {
		
		
		
		
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Product> cq = cb.createQuery(Product.class);
	    
		
		Root<Product> productRoot = cq.from(Product.class);


		List<Predicate> predicates = new ArrayList<>();
		

		if(product.getSections()!=null) {
			
			Section section = sectionRepo.findById(product.getSections().get(0).getId().getIdSection()).get();
			
			Join<Product, Section> sectionJoin = productRoot.join("sections");
			predicates.add(cb.equal(sectionJoin.get("section"), section ));
			
		}
		if (product.getName() != null) {
		    predicates.add(cb.like(productRoot.get("name"), "%" + product.getName() + "%"));
		}
		if (product.getSize() != null) {
		    predicates.add(cb.equal(productRoot.get("size"), product.getSize()));
		}
		if (product.getColor() != null) {
		    predicates.add(cb.equal(productRoot.get("color"), product.getColor()));
		}
		if (product.getPrize() != null) {
		    predicates.add(cb.equal(productRoot.get("prize"), product.getPrize()));
		}
		if (product.getCurrency() != null) {
		    predicates.add(cb.equal(productRoot.get("currency"), product.getCurrency()));
		}
		if (product.getFragile() != null) {
		    predicates.add(cb.equal(productRoot.get("fragile"), product.getFragile()));
		}
		if (product.getBatch() != null) {
		    predicates.add(cb.equal(productRoot.get("batch"), product.getBatch()));
		}
		if (product.getProductType() != null) {
		    predicates.add(cb.equal(productRoot.get("productType"), product.getProductType()));
		}
		if (product.getContainer() != null) {
		    predicates.add(cb.equal(productRoot.get("container"), product.getContainer()));
		}
		
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		
		TypedQuery<Product> query = em.createQuery(cq);
		return query.getResultList();
	}
	
}
