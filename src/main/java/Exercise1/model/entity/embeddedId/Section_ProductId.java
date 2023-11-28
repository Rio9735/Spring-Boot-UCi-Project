package Exercise1.model.entity.embeddedId;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Embeddable
public class Section_ProductId implements Serializable{

	private int idProduct;
	
	private int idSection;	
		
}
