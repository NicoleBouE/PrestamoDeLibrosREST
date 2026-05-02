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
@Table("loan")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @Column("idloan")
    private Long idLoan;

    @Column("date")
    private LocalDate date;

    @Column("status")
    private Status status;

    @Column("charge")
    private Long charge;

    // Relacion con libro y cliente
    @Column("idbook")
    private Long idBook;

    @Column("idclient")
    private Long idClient;

    public enum Status {
        PENDING,
        PAID
    }
}
