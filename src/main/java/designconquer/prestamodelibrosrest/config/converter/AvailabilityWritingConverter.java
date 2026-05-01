package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AvailabilityWritingConverter implements Converter<Book.Availability, String> {
    @Override
    public String convert(Book.Availability source) {
        return source.name(); // Convierte el Enum a su nombre "AVAILABLE"
    }
}