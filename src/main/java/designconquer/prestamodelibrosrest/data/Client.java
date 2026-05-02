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
    @Column("idclient")
    private Long idClient;

    @Column("name")
    private String name;

    @Column("accountbalance")
    private Long accountBalance;
}