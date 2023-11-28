package Exercise1.model.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContainerType {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy= "container")
	private List<Product> products;

}
