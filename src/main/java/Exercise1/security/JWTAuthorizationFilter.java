package Exercise1.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Exercise1.repository.JwtTokenJPARepository;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	
	@Autowired
	JwtTokenJPARepository jr;
	
	@Autowired
	SecurityUserDetailsService us;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authorizationHeader = request.getHeader("Authorization");
		String bearerToken="";
		
		
		if(authorizationHeader.startsWith("Basic ")) {
			
		        String encodedCredentials = authorizationHeader.replace("Basic ", "");
		        String[] credentials = new String(Base64.getDecoder().decode(encodedCredentials)).split(":");
		        String username = credentials[0];
		        String password = credentials[1];
		        SecurityUser userDetails = (SecurityUser) us.loadUserByUsername(username);
				SimpleGrantedAuthority authority = (SimpleGrantedAuthority) userDetails.getAuthorities().toArray()[0];
		        bearerToken= TokenUtil.createToken( username,password,authority.getAuthority());
		      
		    } 
		else if(authorizationHeader.startsWith("Bearer ")) {
			bearerToken= request.getHeader("Authorization");
		}
		else
			throw new BadCredentialsException("Wrong format");
		
		 
		
		
		
		if(bearerToken!=null) {
			
				String token= bearerToken.replace("Bearer ","");
				UsernamePasswordAuthenticationToken usernamePat = TokenUtil.getAuthentication(token);
				UserDetails user = us.loadUserByUsername(usernamePat.getName());
				if(!jr.findById(token).isPresent()) {
					SecurityContextHolder.getContext().setAuthentication(usernamePat);
				}
				else
					throw new BadCredentialsException("Invalid Token");
			
		}
		
		
		filterChain.doFilter(request, response);
	}

	
	
	
	
	
}
