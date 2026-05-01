package designconquer.prestamodelibrosrest.config;

import designconquer.prestamodelibrosrest.config.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.List;

@Configuration
public class MyJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    public List<?> userConverters() {
        return List.of(
                new AvailabilityWritingConverter(),
                new AvailabilityReadingConverter(),
                new LoanStatusWritingConverter(),
                new LoanStatusReadingConverter(),
                new RoleTypeWritingConverter(),
                new RoleTypeReadingConverter()
        );
    }
}