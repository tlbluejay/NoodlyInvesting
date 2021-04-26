package lewis.trenton.NoodlyUserService;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
