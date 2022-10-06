package zw.co.getsol.responses;

import lombok.Getter;
import lombok.Setter;
import zw.co.getsol.model.Role;
@Getter
@Setter
public class RoleDto {
    private String name;
    public static RoleDto fromRole(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }
}
