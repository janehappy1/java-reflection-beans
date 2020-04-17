import context.Context;
import objects.Cat;
import objects.Dog;
import objects.interfaces.Playable;
import reflections.ComponentScan;

public class Main {

    public static void main(String[] args) throws Exception {
        ComponentScan.initComponents();
        Context context = Context.getInstance();

        Playable cat = (Playable) context.get(Cat.class).orElseThrow(Exception::new);
        Playable dog = (Playable) context.get(Dog.class).orElseThrow(Exception::new);

        System.out.println(cat + "\n");
        System.out.println(dog + "\n");

        cat.play();
        dog.play();

    }

}
