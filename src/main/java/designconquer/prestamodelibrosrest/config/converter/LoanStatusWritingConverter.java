package designconquer.prestamodelibrosrest.config.converter;

import designconquer.prestamodelibrosrest.data.Loan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LoanStatusWritingConverter implements Converter<Loan.Status, String> {
    @Override
    public String convert(Loan.Status source) {
        return source.name(); // Retorna "PENDING" o "PAID"
    }
}