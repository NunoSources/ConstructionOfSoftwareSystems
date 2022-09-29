package presentation.fx;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import facade.services.IServicoAtividadeRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.inputcontroller.CreateActivityController;
import presentation.fx.model.NewActivityModel;

public class Startup extends Application {
    
	private static IServicoAtividadeRemote activityService;

	@Override 
    public void start(Stage stage) throws IOException {
    
		// This line to resolve keys against Bundle.properties
		// ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("en", "UK"));
        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
		
    	FXMLLoader createActivityLoader = new FXMLLoader(getClass().getResource("/fxml/newActivity.fxml"), i18nBundle);
    	Parent root = createActivityLoader.load();
    	CreateActivityController newActivityController = createActivityLoader.getController();    	
    	
    	NewActivityModel newCustomerModel = new NewActivityModel(activityService);
    	newActivityController.setModel(newCustomerModel);
    	newActivityController.setactivityService(activityService);
    	newActivityController.setI18NBundle(i18nBundle);
    	
        Scene scene = new Scene(root, 450, 275);
       
        stage.setTitle(i18nBundle.getString("application.title"));
        stage.setScene(scene);
        stage.show();
    }
	
	public static void startGUI(IServicoAtividadeRemote addActivityHandler) {
		Startup.activityService = addActivityHandler;
        launch();
	}
}
