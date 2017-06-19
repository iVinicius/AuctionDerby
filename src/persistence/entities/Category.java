/**
 * 
 */
package persistence.entities;

/**
 * @author Vinicius
 *
 */
public enum Category {

	INFORMATICA(1L, "Informatica"), ELETRODOMESTICOS(2L, "Eletrodomesticos"), 
	ELETROELETRONICOS(3L, "Eletroeletronicos"), OBJETOS_VARIADOS(4L, "ObjetosVariados"), 
	CONSTRUCAO(5L, "Construcao"), VEICULO(6L, "Veiculo"), MAQUINARIO(7L, "Maquinario"), 
	LOTEAMENTO(8L, "Loteamento"), VESTIMENTAS(9L, "Vestimentas"), IMOVEL(10L, "Imovel");
	
	private Long id;
	
	private String type;
	
	private Category(Long id, String type){
		this.id = id;
		this.type = type;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public String getType(){
		return this.type;
	}
	
	public static Category getCategory(Long catId){		
		switch(catId.toString()){
		case "1":
			return INFORMATICA;
			//TODO:
			default:
				return null;
		}
		
	}
}