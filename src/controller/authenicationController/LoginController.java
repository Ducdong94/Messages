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
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import validator.AuthenticationValidate;

/**
 * FXML Controller class
 *
 * @author dongvu
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblUsernameErr, lblPasswordErr, lblErrorsMessage;
    @FXML
    private Hyperlink forgotPassword;
    @FXML
    private Hyperlink switchToRegister;
    ObjectModel<User> model = new ObjectModel<>(User.class);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void landingStartHandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/LoginScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }

    @FXML
    private void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/RegisterScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }

    @FXML
    private void switchToForgotpassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/ForgotPasswordScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }

    @FXML
    private void signinHandle() throws IOException {
        resetErrorLabels();
        HashMap<String, String> errors = AuthenticationValidate.validateLoginForm(txtUsername.getText(), txtPassword.getText());
        if (errors.size() > 0) {
            showErrors(errors);
            return;
        }

        Filter filter = new Filter();
        Filter.Conditions cond = filter.new Conditions();
        cond.setCompare(CompareOperator.EQUAL);
        cond.setCompareValue(txtUsername.getText());
        filter.addField("username", cond);

        boolean userExist = model.checkObjectExist(new User(), filter);
        if (!userExist) {
            lblErrorsMessage.setText("username does not exist !!");
            return;
        }

        Filter.Conditions cond2 = filter.new Conditions();
        cond2.setCompare(CompareOperator.EQUAL);
        cond2.setCompareValue(txtPassword.getText());
        filter.addField("password", cond2);
        User user = model.getObject(new User(), filter);

        if (user == null) {
            lblErrorsMessage.setText("Account or Password is invalid");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("/view/mainView/MainAppScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }

    private void resetErrorLabels() {
        lblErrorsMessage.setText("");
        lblPasswordErr.setText("");
        lblUsernameErr.setText("");
    }

    private void showErrors(HashMap<String, String> errors) {
        lblErrorsMessage.setText("Please check the errors and try again");
        if (errors.containsKey("username")) {
            lblUsernameErr.setText(errors.get("username"));
        }
        if (errors.containsKey("password")) {
            lblPasswordErr.setText(errors.get("password"));
        }
    }
}
