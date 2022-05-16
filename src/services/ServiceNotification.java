/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author oumayma
 */
public class ServiceNotification {
    public boolean check_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }
     public void Notification(String title, String text) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(100))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();

    }

}
