/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.ReadFromServerThread;
import controller.socket.ConnectToServer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author dongvu
 */
public class MainAppController implements Initializable {

    private ConnectToServer connect;

    @FXML
    private JFXTextField txtInput;
    @FXML
    private JFXButton btnSend;

    @FXML
    private ListView<String> listViewUser;
    @FXML
    public TextFlow txtFlowContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hello1");
        try {
            // Create a socket
            this.connect = ConnectToServer.getInstance();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        new Thread(() -> {
            while (true) {
                try {
                    String content = connect.getBr().readLine();
                    if (!content.isEmpty()) {
                        System.err.println(connect.getSocket().getInetAddress().getHostName() + " say: " + content);
                        Text text1 = new Text(content);
                        txtFlowContent.getChildren().addAll(text1, new Text("\n"));
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
    }

    @FXML
    private void send(ActionEvent event) {
        try {
            connect.getBw().write(txtInput.getText());
            connect.getBw().newLine();
            connect.getBw().flush();
            txtInput.setText("");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
