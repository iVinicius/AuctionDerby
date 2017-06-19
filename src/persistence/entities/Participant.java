/**
 * 
 */
package persistence.entities;

/**
 * @author Vinicius
 *
 */
public class Participant {
	
	public Participant(String name, String cpf, String email) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}

	private Long id;
	
	private String name;
	
	private String cpf;
	
	private String email;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}