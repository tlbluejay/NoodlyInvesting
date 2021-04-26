package lewis.trenton.NoodlyUserService;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> favoritedCompanies;

	private String fullName;

	@Size(min = 6)
	private String password;

	@Id
	private long id;
	
	private String email;

	private String phoneNumber;

	public List<String> getFavoritedCompanies() {
		return favoritedCompanies;
	}

	public void setFavoritedCompanies(List<String> favoritedCompanies) {
		this.favoritedCompanies = favoritedCompanies;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
