package facade.dtos;

import java.io.Serializable;
import java.util.List;

public class HorarioPrecoDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<HorarioDTO> horario;
    private float preco;
    
    public HorarioPrecoDTO(List<HorarioDTO> horario, float preco) {
    	this.horario = horario;
    	this.preco = preco;
    }
    
    public float getPreco() {
    	return preco;
    }
    
    public List<HorarioDTO> getHorario() {
    	return horario;
    }
}
