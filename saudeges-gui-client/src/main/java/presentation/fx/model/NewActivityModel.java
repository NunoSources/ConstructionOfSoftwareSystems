package presentation.fx.model;

import facade.dtos.EspecialidadeDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewActivityModel {
	
	private ObjectProperty<String> especialidadeTipo;
	private StringProperty designacao;
	private BooleanProperty isRegular;
	private IntegerProperty nrSessoes;
	private FloatProperty preco;
	private IntegerProperty maxParticipantes;	
	private IntegerProperty duracao;
	private ObservableList<String> especialidades;

	public NewActivityModel(IServicoAtividadeRemote as) {
		especialidadeTipo = new SimpleObjectProperty<>(null);
		designacao = new SimpleStringProperty();
		isRegular = new SimpleBooleanProperty();
		nrSessoes = new SimpleIntegerProperty();
		preco = new SimpleFloatProperty();
		maxParticipantes = new SimpleIntegerProperty();
		duracao = new SimpleIntegerProperty();
		
		this.especialidades = FXCollections.observableArrayList();
		try {
			as.getEspecialidades().forEach(d -> especialidades.add(d.getNome()));
		} catch (ApplicationException e) {
			// no especialçidades added
		}
	}

	

	/**
	 * @return the especialidadeTipo
	 */
	public ObjectProperty<String> getEspecialidade() {
		return especialidadeTipo;
	}



	/**
	 * @param especialidadeTipo the especialidadeTipo to set
	 */
	public void setEspecialidade(ObjectProperty<String> especialidadeTipo) {
		this.especialidadeTipo = especialidadeTipo;
	}



	/**
	 * @return the designacao
	 */
	public StringProperty getDesignacao() {
		return designacao;
	}



	/**
	 * @param designacao the designacao to set
	 */
	public void setDesignacao(StringProperty designacao) {
		this.designacao = designacao;
	}



	/**
	 * @return the isRegular
	 */
	public BooleanProperty getIsRegular() {
		return isRegular;
	}



	/**
	 * @param isRegular the isRegular to set
	 */
	public void setIsRegular(BooleanProperty isRegular) {
		this.isRegular = isRegular;
	}



	/**
	 * @return the nrSessoes
	 */
	public IntegerProperty getNrSessoes() {
		return nrSessoes;
	}



	/**
	 * @param nrSessoes the nrSessoes to set
	 */
	public void setNrSessoes(IntegerProperty nrSessoes) {
		this.nrSessoes = nrSessoes;
	}



	/**
	 * @return the preco
	 */
	public FloatProperty getPreco() {
		return preco;
	}



	/**
	 * @param preco the preco to set
	 */
	public void setPreco(FloatProperty preco) {
		this.preco = preco;
	}



	/**
	 * @return the maxParticipantes
	 */
	public IntegerProperty getMaxParticipantes() {
		return maxParticipantes;
	}



	/**
	 * @param maxParticipantes the maxParticipantes to set
	 */
	public void setMaxParticipantes(IntegerProperty maxParticipantes) {
		this.maxParticipantes = maxParticipantes;
	}



	/**
	 * @return the duracao
	 */
	public IntegerProperty getDuracao() {
		return duracao;
	}



	/**
	 * @param duracao the duracao to set
	 */
	public void setDuracao(IntegerProperty duracao) {
		this.duracao = duracao;
	}



	/**
	 * @return the especialidades
	 */
	public ObservableList<String> getEspecialidades() {
		return especialidades;
	}



	/**
	 * @param especialidades the especialidades to set
	 */
	public void setEspecialidades(ObservableList<String> especialidades) {
		this.especialidades = especialidades;
	}



	public void clearProperties() {
		especialidadeTipo.set(null);
		designacao.set("");
		isRegular.set(true);
		nrSessoes.set(0);
		preco.set(0);
		maxParticipantes.set(0);
		duracao.set(0);
	}
}
