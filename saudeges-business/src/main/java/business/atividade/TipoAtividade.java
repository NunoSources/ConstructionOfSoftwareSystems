package business.atividade;

public enum TipoAtividade {
	REGULAR, OCASIONAL;
	
	
	public static String getTipoString(TipoAtividade tipo) {
		if (tipo == REGULAR) {
			return "regular";
		} else
			return "ocasional";
	}

}
