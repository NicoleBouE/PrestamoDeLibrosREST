package designconquer.prestamodelibrosrest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("Loan")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    private Long idLoan;

    @Column("date")
    @NonNull
    private LocalDate date;

    @Column("status")
    @NonNull
    private Status status;

    @Column("charge")
    @NonNull
    private Long charge;

    // Relacion con libro y cliente
    @Column("idBook")
    @NonNull
    private Long idBook;

    @Column("idClient")
    @NonNull
    private Long idClient;

    public enum Status {
        PENDING,
        PAID
    }
}
