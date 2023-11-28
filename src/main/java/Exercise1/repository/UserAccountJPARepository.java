package Exercise1.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Exercise1.model.entity.UserAccount;


@Repository
public interface UserAccountJPARepository extends JpaRepository<UserAccount,Serializable>{

	
	public Optional<UserAccount> findByName(String name);
	
}
