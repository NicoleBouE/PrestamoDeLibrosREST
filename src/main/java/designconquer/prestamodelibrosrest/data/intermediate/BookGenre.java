package designconquer.prestamodelibrosrest.data.intermediate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bookgenre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenre {

    @Column("idgenre")
    @NonNull
    private Long idGenre;

    @Column("idbook")
    @NonNull
    private Long idBook;
}