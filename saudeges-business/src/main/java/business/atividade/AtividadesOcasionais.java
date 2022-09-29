package business.atividade;

public abstract class AtividadesOcasionais {

	public static boolean nrSessoesValido(int nrSessoes) {
		return nrSessoes >= 1 && nrSessoes <= 20;
	}

}
