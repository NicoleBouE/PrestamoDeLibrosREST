package designconquer.prestamodelibrosrest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("genre")
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @Column("idgenre")
    private Long idGenre;

    @Column("genre")
    private String genre;
}
