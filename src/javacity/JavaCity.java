package javacity;
import javacity.lib.Component;
import javacity.world.City;
import javacity.ui.StdOutput;
import javacity.ui.StdInput;
import java.util.ArrayList;
import javacity.game.component.Population;
import javacity.game.component.Workplace;
import javacity.game.observer.TileCost;
import javacity.ui.Gui;

/**
 * The main game class?
 * @author Tom
 */
public class JavaCity 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        City city = new City(20,15);
        System.out.println(city.getXSize() + ", "+ city.getYSize());
        
        //handle observer game components
        city.registerTileObserver(new TileCost());
        
        //handle per-cycle game components
        ArrayList<Component> components = new ArrayList<Component>();
        components.add(new StdInput(city));

        components.add(new Population(city));
        components.add(new Workplace(city));
        
        Gui gui = new Gui(city);
        gui.updateCanvas();
        
        //The Loop
        
        while (true) {
            
            for (Component component : components) {
                component.tick();
            }
            
            gui.updateCanvas();
        }
    }
}
