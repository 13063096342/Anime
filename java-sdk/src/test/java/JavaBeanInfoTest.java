import com.java.sdk.model.JavaBeanInfo;
import com.java.sdk.model.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @author chenfh
 * @date 2020-09-01 12:53
 */
public class JavaBeanInfoTest {
    public static void main(String[] args) throws IntrospectionException {
        Person person = new Person();

        BeanInfo beanInfo = Introspector.getBeanInfo(JavaBeanInfo.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(
                x -> {
                    x.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                    x.createPropertyEditor(person);
                }
        );
    }


    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String var1) throws IllegalArgumentException {
            Integer i = Integer.valueOf(var1);
            setValue(i);
        }
    }


}
