/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authenicationController;

import view.MainThread;
import entity.User;
import model.CompareOperator;
import model.Filter;
import model.ObjectModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dongvu
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblEmailErr;
    @FXML
    private Label lblCapchaErr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void switchToSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/LoginScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }
}
