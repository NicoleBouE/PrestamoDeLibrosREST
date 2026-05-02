package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.*;
import designconquer.prestamodelibrosrest.repository.BookRepository;
import designconquer.prestamodelibrosrest.repository.ClientRepository;
import designconquer.prestamodelibrosrest.repository.LoanRepository;
import designconquer.prestamodelibrosrest.service.dto.BookDTO;
import designconquer.prestamodelibrosrest.service.dto.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public Loan saveLoan(Loan loan) {
        Book book = bookRepository.findById(loan.getIdBook()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));
        Client client = clientRepository.findById(loan.getIdClient()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        if (book.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay stock disponible del libro: " + book.getTitle());
        }

        if (client.getAccountBalance() < book.getCharge()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente del cliente");
        }

        loan.setCharge(book.getCharge());
        loan.setStatus(Loan.Status.PENDING);
        loan.setDate(LocalDate.now());

        handleTransaction(book, client);
        return loanRepository.save(loan);
    }

    public void handleTransaction(Book book, Client client) {
        book.setQuantity(book.getQuantity() - 1);
        Long charge = book.getCharge();

        client.setAccountBalance(client.getAccountBalance() - charge);

        bookRepository.save(book);
        clientRepository.save(client);
    }

    public void repayLoan(Loan loan) {
        Book book = bookRepository.findById(loan.getIdBook()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));
        Client client = clientRepository.findById(loan.getIdClient()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        book.setQuantity(book.getQuantity() + 1);
        Long charge = book.getCharge();

        client.setAccountBalance(client.getAccountBalance() + charge);

        bookRepository.save(book);
        clientRepository.save(client);
    }

    public Iterable<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con el id: " + id));
    }

    @Transactional
    public Loan updateLoan(Long id, Loan updatedLoan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    if (updatedLoan.getStatus() == Loan.Status.PAID && existingLoan.getStatus() == Loan.Status.PENDING) {
                            repayLoan(existingLoan);
                            existingLoan.setStatus(Loan.Status.PAID);
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