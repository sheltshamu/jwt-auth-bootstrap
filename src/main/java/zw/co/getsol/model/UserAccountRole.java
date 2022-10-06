package zw.co.getsol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserAccountRole extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
