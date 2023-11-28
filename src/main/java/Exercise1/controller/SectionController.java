package Exercise1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Exercise1.model.entity.Section;
import Exercise1.repository.SectionJPARepository;
import Exercise1.service.SectionService;

@RestController
@RequestMapping("/section")
public class SectionController {

	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	SectionJPARepository sectionRepo;
	
	@GetMapping("/list")
	public List<Section> getSections(){
		return sectionService.getAllSection();
	}
	
	@PostMapping("/createorupdate")
	public void createOrUpdateSection(@RequestBody Section section){
		sectionService.createOrUpdateSection(section);
		
	}
	
	@DeleteMapping("/delete")
	public void deleteSection(@RequestParam(name="sectionId") int sectionId) throws Exception{
		
		sectionService.deleteSection(sectionId);
		
	}
	
	
}
