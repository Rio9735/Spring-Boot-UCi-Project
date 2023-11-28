package Exercise1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Exercise1.model.entity.Section;
import Exercise1.repository.SectionJPARepository;

@Service
public class SectionService {
	
	@Autowired
	SectionJPARepository sectionRepo;
	
	public List<Section> getAllSection() {
		return sectionRepo.findAll();
	}
	
	public void createOrUpdateSection(Section section) {
		
		sectionRepo.saveAndFlush(section);
		
	}
	
	public void deleteSection(int sectionId) throws Exception {
		
		if(sectionRepo.findById(sectionId).get().getProducts().size()==0)
			sectionRepo.deleteById(sectionId);
		else
			throw new Exception("Section With Products Imposible To Delete");
		
	}
	
	
	
	

}
