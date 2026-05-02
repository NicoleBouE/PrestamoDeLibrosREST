package designconquer.prestamodelibrosrest.service.dto;

import java.time.LocalDate;
import java.util.Optional;

import designconquer.prestamodelibrosrest.data.Client;
import designconquer.prestamodelibrosrest.data.Loan;
import designconquer.prestamodelibrosrest.data.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Long idLoan;
    private LocalDate date;
    private Loan.Status status;
    private Long charge;
    private Optional<Client> client;
    private Optional<Book> book;
}