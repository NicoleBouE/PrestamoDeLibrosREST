package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.*;
import designconquer.prestamodelibrosrest.repository.BookRepository;
import designconquer.prestamodelibrosrest.repository.ClientRepository;
import designconquer.prestamodelibrosrest.repository.LoanRepository;
import designconquer.prestamodelibrosrest.service.dto.BookDTO;
import designconquer.prestamodelibrosrest.service.dto.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public Loan saveLoan(Loan loan) {
        loan.setDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    public Iterable<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Préstamo no encontrado con id: " + id));
    }

    public Loan updateLoan(Long id, Loan updatedLoan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    if (updatedLoan.getStatus() != null) {
                            existingLoan.setStatus(updatedLoan.getStatus());
                    }
                    if (updatedLoan.getCharge() != null && updatedLoan.getCharge() >= 0) {
                        existingLoan.setCharge(updatedLoan.getCharge());
                    }
                    if (updatedLoan.getIdBook() != null && bookRepository.findById(updatedLoan.getIdBook()).isPresent()) {
                        existingLoan.setIdBook(updatedLoan.getIdBook());
                    }
                    if (updatedLoan.getIdClient() != null && clientRepository.findById(updatedLoan.getIdClient()).isPresent()) {
                        existingLoan.setIdClient(updatedLoan.getIdClient());
                    }
                    return loanRepository.save(existingLoan);
                })
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con el id: " + id));
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }


    public LoanDTO getLoanDTO(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        String bookTitle = bookRepository.findById(loan.getIdBook())
                .map(book -> book.getTitle())
                .orElse(null);

        String clientName = clientRepository.findById(loan.getIdClient())
                .map(client -> client.getName())
                .orElse(null);

        return new LoanDTO(
                loan.getIdLoan(),
                loan.getDate(),
                loan.getStatus(),
                loan.getCharge(),
                clientRepository.findById(loan.getIdClient()),
                bookRepository.findById(loan.getIdBook())
        );
    }
}