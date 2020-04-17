package reflections;

import annotations.Component;
import context.Context;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;
import java.util.concurrent.Executors;

public class ComponentScan {

    private ComponentScan() throws IllegalAccessException {
        throw new IllegalAccessException("Private constructor");
    }

    public static void initComponents(String prefix) {
        ComponentIniter componentIniter = new ComponentIniter(prefix);
        componentIniter.initBeans();
    }

    public static void initComponents(){

    }


    private static class ComponentIniter {

        private String prefix = "";
        private Reflections reflections;
        private Context context;

        private ComponentIniter(String prefix){
            this();
            this.prefix = prefix;
        }

        private ComponentIniter(){
            context = Context.getInstance();
            initFields();
        }

        private void initBeans() {
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Component.class);
            classes.forEach(clazz -> context.add(clazz));
        }

        private void initFields() {
            reflections = new Reflections(
                    new ConfigurationBuilder()
                            .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner())
                            .setUrls(ClasspathHelper.forPackage(prefix))
                            .setExecutorService(Executors.newFixedThreadPool(2))
            );
        }

    }

}