package Main;
import Layouts.Controller;
import Layouts.MainGame.MainGame;
import Layouts.Menu.MainMenu;
import Layouts.Model;
import Layouts.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        try {
            Controller controller = new Controller(model);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
