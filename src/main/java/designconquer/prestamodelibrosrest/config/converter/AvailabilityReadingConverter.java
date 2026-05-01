package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class AvailabilityReadingConverter implements Converter<String, Book.Availability> {
    @Override
    public Book.Availability convert(String source) {
        if (source == null) return null;
        try {
            return Book.Availability.valueOf(source.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Maneja de error si el valor en la BD no coincide con el Enum en Java
            return null;
        }
    }
}