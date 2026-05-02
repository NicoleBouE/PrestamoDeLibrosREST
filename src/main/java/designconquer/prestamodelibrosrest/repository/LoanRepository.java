package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.Loan;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends ListCrudRepository<Loan, Long> {

    Loan findByIdLoan(long id);
    List<Loan> findAllByStatus(Loan.Status status);

    @Query("SELECT Loan.* FROM Loan " +
            "JOIN Client ON Loan.idClient = Client.idClient " +
            "JOIN Book ON Loan.idBook = idBook " +
            "WHERE Client.name LIKE :name " +
            "AND Book.title LIKE :titulo")
    List<Loan> findByClientOrBook(
            @Param("name") String name,
            @Param("title") String title
    );
}