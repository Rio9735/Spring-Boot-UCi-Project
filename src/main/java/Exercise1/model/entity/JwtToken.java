package Exercise1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JwtToken {
	
	@Id
	private String token;
	
	
}
