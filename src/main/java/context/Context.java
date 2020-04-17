package context;

import reflections.ValueAnnotationScan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Context {

    private List<Class<?>> list = new ArrayList<>();

    private static class ContextLoader{
        private static final Context INSTANCE = new Context();
    }

    private Context() {
        if(ContextLoader.INSTANCE != null){
            throw new IllegalArgumentException("Already exists");
        }
    }

    public static Context getInstance(){
        return ContextLoader.INSTANCE;
    }

    public void add(Class<?> clazz){
        list.add(clazz);
    }

    public Optional<?> get(Class<?> clazz){
        try {
            Optional<?> obj = Optional.of(clazz.getConstructor().newInstance());
            ValueAnnotationScan valueAnnotationScan = new ValueAnnotationScan("");
            valueAnnotationScan.initValueToFields(obj.orElseThrow(Exception::new));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
