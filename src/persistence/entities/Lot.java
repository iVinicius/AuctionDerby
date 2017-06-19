/**
 * 
 */
package persistence.entities;

import java.util.ArrayList;

/**
 * @author Vinicius
 *
 */
public class Lot {
	
	private Long id;

	private ArrayList<Asset> assets;
	
	private Long value;
	
	private boolean isValueMax;

	public Lot(ArrayList<Asset> assets, Long value, boolean isValueMax) {
		super();
		this.assets = assets;
		this.value = value;
		this.isValueMax = isValueMax;
	}
	
	public Lot(Long value, boolean isValueMax) {
		super();
		this.value = value;
		this.isValueMax = isValueMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Asset> getAssets() {
		return assets;
	}

	public void setAssets(ArrayList<Asset> assets) {
		this.assets = assets;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public boolean isValueMax() {
		return isValueMax;
	}

	public void setValueMax(boolean isValueMax) {
		this.isValueMax = isValueMax;
	}
	
}
