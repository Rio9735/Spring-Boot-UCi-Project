package Exercise1.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class UserAccount {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Column(unique= true)
	private String name;
	
	private String password;
	
	private String role;
	
}
