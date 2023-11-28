package Exercise1.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Section {
	
	@Id
	private int id;
	
	@Column(nullable=false)
	private float size;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
	private ProductType productType;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy= "section")
	private List<Section_Product> products;
	
	
	
	
	
	

}
