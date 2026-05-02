package designconquer.prestamodelibrosrest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @Column("idauthor")
    private Long idAuthor;

    @Column("name")
    private String name;
}
