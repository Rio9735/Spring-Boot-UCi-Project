package Exercise1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Exercise1.model.entity.Product;
import Exercise1.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	@GetMapping("search")
	public List<Product> searchProducts(@RequestBody Product product){
		
		return productService.findProductsByCriteria(product);
		
	}
	
	@PostMapping("/addtosection")
	public void addProductToSection(
									@RequestBody Product product,
									@RequestParam(name="sectionId") int sectionId,
									@RequestParam(name="cant") int cant
									) throws Exception{
			productService.createOrUpdateProduct(product, sectionId, cant);
		
			
		}

}
