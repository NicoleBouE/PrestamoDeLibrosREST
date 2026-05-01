package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.Loan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class LoanStatusReadingConverter implements Converter<String, Loan.Status> {
    @Override
    public Loan.Status convert(String source) {
        if (source == null) return null;
        try {
            return Loan.Status.valueOf(source.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}