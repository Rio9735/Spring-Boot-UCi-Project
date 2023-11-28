package Exercise1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Exercise1.model.entity.ContainerType;
import Exercise1.model.entity.ProductType;
import Exercise1.model.entity.Section;
import Exercise1.model.entity.UserAccount;
import Exercise1.repository.ContainerJPARepository;
import Exercise1.repository.ProductJPARepository;
import Exercise1.repository.ProductTypeJPARepository;
import Exercise1.repository.SectionJPARepository;
import Exercise1.repository.UserAccountJPARepository;

@SpringBootApplication
public class Exercise1Application implements CommandLineRunner {

	
	
	@Autowired
	ContainerJPARepository cr;
	
	@Autowired
	ProductJPARepository pr;
	
	@Autowired
	ProductTypeJPARepository ptr;
	
	@Autowired
	SectionJPARepository sr;
	
	@Autowired
	UserAccountJPARepository ur;
	
	@Autowired
	PasswordEncoder bc;
	
	public static void main(String[] args) {
		SpringApplication.run(Exercise1Application.class, args);
	}
	
	
	
	
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		UserAccount u1 = new UserAccount();
		u1.setName("admin");
		u1.setPassword(bc.encode("password"));
		u1.setRole("ROLE_ADMIN");
		ur.save(u1);
		
		
		UserAccount u2 = new UserAccount();
		u2.setName("operator");
		u2.setPassword(bc.encode("password"));
		u2.setRole("ROLE_OPERATOR");
		ur.save(u2);
		
		
		
		
		//Creando Tipos de Productos
		ProductType pt1 = new ProductType();
		pt1.setName("Electrodomesticos");
		ptr.saveAndFlush(pt1);
		
		ProductType pt2 = new ProductType();
		pt2.setName("Cárnicos");
		ptr.saveAndFlush(pt2);
		
		ProductType pt3 = new ProductType();
		pt3.setName("Ropa");
		ptr.saveAndFlush(pt3);
		
		ProductType pt4 = new ProductType();
		pt4.setName("Aseo");
		ptr.saveAndFlush(pt4);
		
		
		//Creando Tipos de Contenedor
		ContainerType c1 = new ContainerType();
		c1.setName("Cartón");
		cr.saveAndFlush(c1);
		
		ContainerType c2 = new ContainerType();
		c2.setName("Plástico");
		cr.saveAndFlush(c2);
		
		ContainerType c3 = new ContainerType();
		c3.setName("Cristal");
		cr.saveAndFlush(c3);
		
		ContainerType c4 = new ContainerType();
		c4.setName("Nylon");
		cr.saveAndFlush(c4);
		
		
		Section s1 = new Section();
		s1.setId(1);
		s1.setSize(10);
		s1.setProductType(pt1);
		sr.save(s1);
		
		
		Section s2 = new Section();
		s2.setId(2);
		s2.setSize(10);
		s2.setProductType(pt2);
		sr.save(s2);
		
		
	}

}
