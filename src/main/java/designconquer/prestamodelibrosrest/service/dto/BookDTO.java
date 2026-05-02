package designconquer.prestamodelibrosrest.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long idBook;
    private String title;
    private Long idAuthor;
    private String authorName;
    private List<String> genreNames;
    private String availability;
}