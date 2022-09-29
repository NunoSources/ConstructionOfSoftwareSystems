package business.atividade;

public abstract class AtividadesRegulares {

	public static boolean nrSessoesValido(int nrSessoes) {
		return nrSessoes >= 1 && nrSessoes <= 5;
	}

}
