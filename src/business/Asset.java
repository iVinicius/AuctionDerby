/**
 * 
 */
package business;

/**
 * @author Vinicius
 *
 */
public class Asset {
	
	private Long id;

	private String briefDescription;
	
	private String fullDescription;
	
	private Category category;

	public Asset(String briefDescription, String fullDescription, Category category) {
		super();
		this.briefDescription = briefDescription;
		this.fullDescription = fullDescription;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
