package esprit.tn.Services;

import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageSingleton {
    private static ImageSingleton instance;
    private Image capturedImage;
 

    private ImageSingleton() {}

    public static synchronized ImageSingleton getInstance() {
        if (instance == null) {
            instance = new ImageSingleton();
        }
        return instance;
    }

    public void setCapturedImage(Image image) {
        this.capturedImage = image;
    }

    public Image getCapturedImage() {
        return capturedImage;
    }

    public static void setInstance(ImageSingleton instance) {
        ImageSingleton.instance = instance;
    }

    public void setCapturedImage(javafx.scene.image.Image capturedImage) {
    }
}
