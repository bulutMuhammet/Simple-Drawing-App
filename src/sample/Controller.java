package sample;

import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;

import javax.imageio.ImageIO;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Controller implements Initializable{
    GraphicsContext graphicsContext;
    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider bsize;

    @FXML
    private ToggleButton isBrush;

    @FXML
    private ToggleButton isEraser;

    private int currentTool=0;

    public void clickBrush(){
        currentTool=0;
        isBrush.setSelected(true);
        isEraser.setSelected(false);

    }

    public void clickEraser(){
        currentTool=1;
        isBrush.setSelected(false);
        isEraser.setSelected(true);
    }

    public void clearEverything(){
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.setValue(Color.BLACK);

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());



        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if (currentTool==0){
                            graphicsContext.setStroke(colorPicker.getValue());
                        }else{
                            graphicsContext.setStroke(Color.WHITE);
                        }


                        graphicsContext.setLineWidth(bsize.getValue());
                        graphicsContext.beginPath();
                        graphicsContext.moveTo(event.getX(), event.getY());
                        graphicsContext.stroke();

                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if (currentTool==0){
                            graphicsContext.setStroke(colorPicker.getValue());
                        }else{
                            graphicsContext.setStroke(Color.WHITE);
                        }
                        graphicsContext.setLineWidth(bsize.getValue());
                        graphicsContext.lineTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                        graphicsContext.closePath();
                        graphicsContext.beginPath();
                        graphicsContext.moveTo(event.getX(), event.getY());
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if (currentTool==0){
                            graphicsContext.setStroke(colorPicker.getValue());
                        }else{
                            graphicsContext.setStroke(Color.WHITE);
                        }
                        graphicsContext.setLineWidth(bsize.getValue());

                        graphicsContext.lineTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                        graphicsContext.closePath();
                    }
                });
    }

    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }


}
