package designconquer.prestamodelibrosrest.data;

import designconquer.prestamodelibrosrest.data.intermediate.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("Book")
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @Column("idbook")
    private Long idBook;

    @Column("charge")
    @NonNull
    private long charge;

    @Column("quantity")
    @NonNull
    private int quantity;

    @Column("availability")
    @NonNull
    private Availability availability;

    @Column("title")
    @NonNull
    private String title;

    // Relación con Author
    @Column("idauthor")
    @NonNull
    private Long idAuthor;

    @MappedCollection(idColumn = "idbook")
    private Set<BookGenre> genres;

    public void addGenre(Long idGenre) {
        this.genres.add(new BookGenre(idGenre, idBook));
    }

    public enum Availability {
        AVAILABLE,
        UNAVAILABLE
    }
}