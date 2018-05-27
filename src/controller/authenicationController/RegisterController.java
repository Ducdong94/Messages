/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authenicationController;

import entity.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import validator.AuthenticationValidate;
import view.MainThread;

/**
 *
 * @author dongvu
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField registerPhone, registerFullName, registerEmail, registerCapcha, registerUsername, registerPass, registerRePass;
    @FXML
    private RadioButton registerGenterMale, registerGenterFemale, registerGenterOther;
    @FXML
    private ComboBox<String> registerComboDay, registerComboMonth, registerComboYear;
    @FXML
    private Label lblUsernameErr, lblPasswordErr, lblRepassErr;
    ObjectModel<User> model = new ObjectModel<>(User.class);
    @FXML
    private Label lblErrorsMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        registerGenterFemale.setToggleGroup(group);
        registerGenterMale.setToggleGroup(group);
        registerGenterOther.setToggleGroup(group);

        for (int i = 1; i < 32; i++) {
            registerComboDay.getItems().add(String.valueOf(i));
            if (i < 13) {
                registerComboMonth.getItems().add(String.valueOf(i));
            }
        }
        for (int i = 1970; i <= 2018; i++) {
            registerComboYear.getItems().add(String.valueOf(i));
        }
    }

    @FXML
    private void registerHandle() throws IOException {
        lblUsernameErr.setText("");
        lblPasswordErr.setText("");
        lblRepassErr.setText("");

        String username = registerUsername.getText();
        String password = registerPass.getText();
        String phone = registerPhone.getText();
        String fullname = registerFullName.getText();
        String email = registerEmail.getText();
        String birthday = registerComboDay.getSelectionModel().getSelectedItem() + "-"
                + registerComboMonth.getSelectionModel().getSelectedItem() + "-"
                + registerComboYear.getSelectionModel().getSelectedItem();
        String gender = "";
        if (registerGenterMale.isSelected()) {
            gender = registerGenterMale.getText();
        }
        if (registerGenterFemale.isSelected()) {
            gender = registerGenterFemale.getText();
        }
        if (registerGenterOther.isSelected()) {
            gender = registerGenterOther.getText();
        }
        User user = new User(username, password, phone, fullname, email, birthday, gender);
        HashMap<String, String> errors = AuthenticationValidate.vailidateRegisterForm(username, password, registerRePass.getText());
        if (errors.size() > 0) {
            lblErrorsMessage.setText("Please check the errors and try again");
            if (errors.containsKey("username")) {
                lblUsernameErr.setText(errors.get("username"));
            }
            if (errors.containsKey("password")) {
                lblPasswordErr.setText(errors.get("password"));
            }
            if (errors.containsKey("rePassword")) {
                lblRepassErr.setText(errors.get("rePassword"));
            }
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Register Dialog");
        alert.setHeaderText(null);

        if (model.Insert(user)) {
            alert.setContentText("Account register sucessfuly ");
            Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/LoginScene.fxml"));
            Scene scene = new Scene(root);
            MainThread.getStage().setScene(scene);
        } else {
            alert.setContentText("Account register failed ");
        }
        alert.showAndWait();
    }

    @FXML
    private void switchToSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authenication/LoginScene.fxml"));
        Scene scene = new Scene(root);
        MainThread.getStage().setScene(scene);
    }
}
