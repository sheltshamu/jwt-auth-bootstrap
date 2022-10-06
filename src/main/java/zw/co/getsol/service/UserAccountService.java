package zw.co.getsol.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import zw.co.getsol.request.RoleRequest;
import zw.co.getsol.request.UserRequest;
import zw.co.getsol.responses.RoleDto;
import zw.co.getsol.responses.UserDto;

import java.util.List;

public interface UserAccountService {
    UserDto saveUser(UserRequest userRequest);
    RoleDto saveRole(RoleRequest roleRequest);
    void assignRoleToUser(String username,String roleName);
    List<UserDto> getAllUsers();
    UserDto getUser(String username);
    List<String> findUserRoles(String username);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
