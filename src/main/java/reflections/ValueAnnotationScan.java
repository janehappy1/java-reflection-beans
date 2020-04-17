package reflections;

import annotations.IntValue;
import annotations.StringValue;
import context.Context;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;

public class ValueAnnotationScan {

    private String prefix = "";
    private Reflections reflections;
    private Context context;

    public ValueAnnotationScan(String pathPrefix) {
        this();
        this.prefix = pathPrefix;
    }

    private ValueAnnotationScan() {
        initFields();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void initValueToFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(StringValue.class)){
                StringValue stringValueAnn = field.getAnnotation(StringValue.class);
                try {
                    field.setAccessible(true);
                    field.set(obj, stringValueAnn.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(field.isAnnotationPresent(IntValue.class)){
                IntValue intValueAnn = field.getAnnotation(IntValue.class);
                try {
                    field.setAccessible(true);
                    field.set(obj, intValueAnn.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initFields() {
        reflections = new Reflections(
                new ConfigurationBuilder()
                        .setScanners(new FieldAnnotationsScanner())
                        .setUrls(ClasspathHelper.forPackage(prefix))
                        .setExecutorService(Executors.newFixedThreadPool(2))
        );

        context = Context.getInstance();
    }

}
