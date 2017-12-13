import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class CatchDk extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Go and Get Dekhoda spaceship!" );

        Group root = new Group();
        Scene scene = new Scene( root );
        theStage.setScene( scene );

        Canvas canvas = new Canvas( 600, 600 );
        root.getChildren().add( canvas );

        StackPane holder = new StackPane();

        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        holder.setStyle("-fx-background-color: black");
        theStage.show();
		

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });

        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.YELLOW );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);
    
        Sprite character = new Sprite();
        character.setImage("character.png");
        character.setPosition(280, 560);
        
        ArrayList<Sprite> monsterList = new ArrayList<Sprite>();
        
        for (int i = 0; i < 30; i++)
        {
            Sprite monster = new Sprite();
            monster.setImage("monster.jpg");
            double px = 450 * Math.random() + 50;
            double py = 450 * Math.random() + 50;          
            monster.setPosition(px,py);
            monsterList.add( monster );
        }
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 100000000.0;
                lastNanoTime.value = currentNanoTime;
                
                
                character.setVelocity(0,0);
                if (input.contains("LEFT"))
                    character.addVelocity(-50,0);
                if (input.contains("RIGHT"))
                    character.addVelocity(50,0);
                if (input.contains("UP"))
                    character.addVelocity(0,-50);
                if (input.contains("DOWN"))
                    character.addVelocity(0,50);
                    
                character.update(elapsedTime);
                
                // collision detection
                
                Iterator<Sprite> monsterIter = monsterList.iterator();
                while ( monsterIter.hasNext() )
                {
                    Sprite monster = monsterIter.next();
                    if ( character.intersects(monster) )
                    {
                        monsterIter.remove();
                        score.value++;
                    }
                }
                
                // render
                gc.clearRect(0, 0, 600,600);
                character.render( gc );
                
                for (Sprite monster : monsterList )
                    monster.render( gc );

                String pointsText = "Score : " + (100 * score.value);
                gc.fillText( pointsText, 455, 585);
                gc.strokeText( pointsText, 455, 585 );
            }
        }.start();

        theStage.show();
    }
	
}

class IntValue
{
    public int value;
    
    public IntValue(int i)
    {
        value = i;
    }
}
class LongValue
{
    public long value;
    
    public LongValue(long i)
    {
        value = i;
    }
}