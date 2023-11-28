package Exercise1.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Exercise1.model.entity.embeddedId.Section_ProductId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Section_Product {

	
	@EmbeddedId
	private Section_ProductId id;
	
	@JsonIgnore
	@MapsId("idProduct")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Product product;
	
	@MapsId("idSection")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Section section;
	
	private int cant;

	
	
	public Section_Product(Product product, Section section, int cant) {
		id= new Section_ProductId();
		id.setIdProduct(product.getId());
		id.setIdSection(section.getId());
		this.product = product;
		this.section = section;
		this.cant = cant;
	}
	
	
	
	
}
