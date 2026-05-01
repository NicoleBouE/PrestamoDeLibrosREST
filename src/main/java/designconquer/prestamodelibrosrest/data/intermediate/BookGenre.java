package designconquer.prestamodelibrosrest.data.intermediate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("BookGenre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenre {

    @Column("idGenre")
    @NonNull
    private Long idGenre;

    @Column("idBook")
    @NonNull
    private Long idBook;
}