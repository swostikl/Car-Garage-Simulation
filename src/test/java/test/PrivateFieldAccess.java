package test;

import java.lang.reflect.Field;

public class PrivateFieldAccess {
    public static <T> T getPrivateField(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        T result = (T) field.get(target);
        return  result;
    }
}
