/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainView;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

/**
 * FXML Controller class
 *
 * @author dongvu
 */
public class MainAppController implements Initializable {

    @FXML
    private JFXTextField txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt.setStyle("-fx-focus-color: accent-color;"+"-fx-pref-height:100;");
        
              txt.setBorder(new Border(new BorderStroke[5]));
        //setStyle("-fx-border-color: orangered;"+"-fx-border-width: 3;
    }    
    
    
    
}
