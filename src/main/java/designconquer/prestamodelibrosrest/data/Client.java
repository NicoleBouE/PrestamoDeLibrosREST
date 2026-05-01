package designconquer.prestamodelibrosrest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Client")
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id
    private Long idClient;

    @Column("name")
    @NonNull
    private String name;

    @Column("accountBalance")
    @NonNull
    private Long accountBalance;
}