package presentation.fx.inputcontroller;

import java.util.function.UnaryOperator;

import facade.dtos.EspecialidadeDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.NewActivityModel;

public class CreateActivityController extends BaseController {

	@FXML
	private ComboBox<String> especialidadeComboBox;
	
	@FXML
	private TextField designationTextField;
	
	@FXML
	private ComboBox<Boolean> isRegularComboBox;

	@FXML
	private TextField sessionNumberTextField;

	@FXML
	private TextField priceTextField;
	
	@FXML
	private TextField durationTextField;
	
	@FXML
	private TextField participantsTextField;
	

	private NewActivityModel model;

	private IServicoAtividadeRemote activityService;

	public void setactivityService(IServicoAtividadeRemote activityService) {
		this.activityService = activityService;
	}

	public void setModel(NewActivityModel model) {
		this.model = model;
		designationTextField.textProperty().bindBidirectional(model.getDesignacao());
		especialidadeComboBox.setItems(model.getEspecialidades());
		especialidadeComboBox.setValue(model.getEspecialidade().get());
		
		
		ObservableList<Boolean> values = FXCollections.observableArrayList();
		values.add(true);
		values.add(false);
		
		isRegularComboBox.setItems(values);
		isRegularComboBox.setValue(model.getIsRegular().get());

		sessionNumberTextField.textProperty().bindBidirectional(model.getNrSessoes(), new NumberStringConverter());

		priceTextField.textProperty().bindBidirectional(model.getPreco(), new NumberStringConverter());
		
		durationTextField.textProperty().bindBidirectional(model.getDuracao(), new NumberStringConverter());
		
		participantsTextField.textProperty().bindBidirectional(model.getMaxParticipantes(), new NumberStringConverter());

	}

	@FXML
	private void initialize() {
		UnaryOperator<Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("[0-9]*")) {
				return change;
			}
			return null;
		};
		
		UnaryOperator<Change> floatFilter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("[0-9]*.[0-9]*")) {
				return change;
			}
			return null;
		};
		
		sessionNumberTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));

		priceTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, floatFilter));
		
		durationTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
		
		participantsTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
	}

	@FXML
	void createActivityAction(ActionEvent event) {
		String errorMessages = validateInput();

		if (errorMessages.length() == 0) {
			try {
				activityService.addAtividade(model.getEspecialidade().get(), model.getDesignacao().get(),
						model.getIsRegular().get(), model.getNrSessoes().get(),
						model.getDuracao().get(), model.getPreco().get(),
						model.getMaxParticipantes().get());

				model.clearProperties();
				showInfo(i18nBundle.getString("new.activity.success"));
			} catch (ApplicationException e) {
				showError(i18nBundle.getString("new.activity.error.adding") + ": " + e.getMessage());
			}
		} else
			showError(i18nBundle.getString("new.activity.error.validating") + ":\n" + errorMessages);
	}

	private String validateInput() {
		StringBuilder sb = new StringBuilder();
		String designation = model.getDesignacao().get();
		if (designation == null || designation.length() == 0)
			sb.append(i18nBundle.getString("new.activity.invalid.designation"));
		
		int sessionNumber = model.getNrSessoes().get();
		if (sessionNumber == 0)
			sb.append(i18nBundle.getString("new.activity.invalid.sessionNumber"));
		
		float price = model.getPreco().get();
		if (price == 0)
			sb.append(i18nBundle.getString("new.activity.invalid.price"));
		
		int duracao = model.getDuracao().get();
		if (duracao == 0)
			sb.append(i18nBundle.getString("new.activity.invalid.duracao"));
		
		int participantes = model.getMaxParticipantes().get();
		if (participantes == 0)
			sb.append(i18nBundle.getString("new.activity.invalid.participantes"));
		
		
		
		
		if (model.getIsRegular() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("new.activity.invalid.isregular"));
		}
		if (model.getEspecialidade() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("new.activity.invalid.especialidade"));
		}

		return sb.toString();
	}

	@FXML
	void especialidadeTypeSelected(ActionEvent event) {
		model.getEspecialidade().set(especialidadeComboBox.getValue());
	}
	
	@FXML
	void isRegularSelected(ActionEvent event) {
		model.getIsRegular().set(isRegularComboBox.getValue());
	}

}
