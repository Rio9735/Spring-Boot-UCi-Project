package Exercise1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Exercise1.repository.UserAccountJPARepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	UserAccountJPARepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		try {
			return new SecurityUser(ur.findByName(name).get());
		
		}catch(Exception e) {
		 throw new UsernameNotFoundException("No se ha encontrado al usuario");
		}
		
		
		
	}

}
