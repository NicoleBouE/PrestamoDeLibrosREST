package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class RoleTypeWritingConverter implements Converter<User.Role, String> {
    @Override
    public String convert(User.Role source) {
        return source.name(); // Convierte el Enum a su nombre "USER"
    }
}