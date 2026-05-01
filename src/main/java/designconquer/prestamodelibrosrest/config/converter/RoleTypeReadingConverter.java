package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class RoleTypeReadingConverter implements Converter<String, User.Role> {
    @Override
    public User.Role convert(String source) {
        if (source == null) return null;
        try {
            return User.Role.valueOf(source.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

