package zw.co.getsol.responses;

import lombok.Getter;
import lombok.Setter;
import zw.co.getsol.model.Role;
import zw.co.getsol.model.UserAccount;
import zw.co.getsol.model.UserAccountRole;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String username;

    public static UserDto fromUser(UserAccount userAccount){
        UserDto userDto = new UserDto();
        userDto.setName(userAccount.getName());
        userDto.setUsername(userAccount.getUsername());
        return userDto;
    }

}
