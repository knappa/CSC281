import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created on 3/2/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class JFXHelloWorld extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    BorderPane topLevelPane = new BorderPane();

    topLevelPane.setBottom(new Label("bottom"));
    topLevelPane.setTop(new Label("top"));
    topLevelPane.setLeft(new Label("left"));
    topLevelPane.setRight(new Label("right"));

    GridPane centerPane = new GridPane();
    topLevelPane.setCenter(centerPane);

    final TextField textField = new TextField();
    centerPane.add(textField, 0, 0);

    Button buttonOne = new Button("Clear");
    centerPane.add(buttonOne, 1, 0);

    buttonOne.setOnMouseClicked(mouseEvent -> textField.clear());

    Button buttonTwo = new Button("he" +
                                    "'llo");
    buttonTwo.setStyle("-fx-base: #0000FF");
    centerPane.add(buttonTwo, 0, 1);

    buttonTwo.setOnMouseClicked(mouseEvent -> {
      System.out.println(mouseEvent);
      textField.setText("Hi there!");
    });

    RadioButton buttonThree = new RadioButton("radio!");
    centerPane.add(buttonThree, 1, 1);

    stage.setScene(new Scene(topLevelPane, 300, 300));

    stage.setTitle("Hello World!");
    stage.show();
  }
}
