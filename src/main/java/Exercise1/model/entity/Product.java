package Exercise1.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Float size;
	
	@Column(nullable=false)
	private String color;
	
	@Column(nullable=false)
	private Float prize;
	
	@Column(nullable=false)
	private String currency;
	
	@Column(nullable=false)
	private Boolean fragile;
	
	@Column(nullable=false)
	private String batch;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
	private ProductType productType;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
	private ContainerType container;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy= "product")
	private List<Section_Product> sections;
	
	
	
	
	
}
