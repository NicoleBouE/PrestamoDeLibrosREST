package designconquer.prestamodelibrosrest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("dbuser") // DB por Database
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column("iduser")
    private Long idUser;

    @Column("username")
    @NonNull
    private String username;
    @Column("password")
    @NonNull
    private String password;
    @Column("role")
    @NonNull
    private Role role;

    public enum Role {
        ADMIN,
        LIBRARIAN,
        USER;
    }
}