package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.Cmd;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IndexController {
	
	@FXML private TextField tfIP;
    @FXML private ComboBox<String> cbApp;
    @FXML private CheckBox cbScreen;
    @FXML private TextField tfCommand;
    @FXML private Button btnStart;
    private String app;
    private String ip;
    private String command;
    private boolean screen = false;
    ObservableList<String> apps = FXCollections.observableArrayList("CMD");
    private Cmd cmd = new Cmd();
    private String theRegex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
    private boolean match;
    
    @FXML private void initialize() {
        btnStart.setDisable(true);
        cbApp.setItems(apps);
    }
    
	@FXML public void keyReleasedProperty() {
    	ip = tfIP.getText();
    	match = regexChecker(theRegex, ip);
    	command = tfCommand.getText();
    	app = cbApp.getSelectionModel().getSelectedItem();
    	screen = cbScreen.isSelected();
    	
    	System.out.println("match: " + match);
    	System.out.println(command.isEmpty());
    	
    	if (app != null) {
    		boolean isDisabled = (!match) || (command.isEmpty() || command.trim().isEmpty()) || (ip.isEmpty() || ip.trim().isEmpty()) || (app.isEmpty() || app.trim().isEmpty());
    		btnStart.setDisable(isDisabled);
    	}
    }

    @FXML
    void mouseclickedScreen() {
    	screen = cbScreen.isSelected();
    }
    
    @FXML
    void clickedStart() throws InterruptedException, IOException {
    	cmd.setup(ip);
    	cmd.testCmd(command, screen);
    	cmd.tearDown();
    }
    
    public static boolean regexChecker(String theRegex, String str2Check) {
    	boolean match = false;
    	Pattern checkRegex = Pattern.compile(theRegex);
    	Matcher regexMatcher = checkRegex.matcher( str2Check );
    	while ( regexMatcher.find() ){
    		if (regexMatcher.group().length() != 0){
    			System.out.println( regexMatcher.group().trim() );
    			match = true;
    		}
    	}
    	return match;
    }

}
