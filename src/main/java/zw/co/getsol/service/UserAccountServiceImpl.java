package zw.co.getsol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.getsol.model.Role;
import zw.co.getsol.model.UserAccount;
import zw.co.getsol.model.UserAccountRole;
import zw.co.getsol.repository.RoleRepository;
import zw.co.getsol.repository.UserAccountRepository;
import zw.co.getsol.repository.UserAccountRoleRepository;
import zw.co.getsol.request.RoleRequest;
import zw.co.getsol.request.UserRequest;
import zw.co.getsol.responses.RoleDto;
import zw.co.getsol.responses.UserDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final UserAccountRoleRepository userAccountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserRequest userRequest) {
        Optional<UserAccount> existingUser = userAccountRepository.findUserAccountByUsername(userRequest.getUsername());
        if (existingUser.isPresent()){
            log.error("Username already exist {}",existingUser);
            throw new RuntimeException("Username already exist !");
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setName(userRequest.getName());
        userAccount.setUsername(userRequest.getUsername());
        userAccount.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userAccountRepository.save(userAccount);
        return UserDto.fromUser(userAccount);
    }

    @Override
    public RoleDto saveRole(RoleRequest roleRequest) {
        Optional<Role> existingRole = roleRepository.findByName(roleRequest.getName());
        if (existingRole.isPresent()){
            log.error("Role already exist {}",existingRole);
            throw new RuntimeException("Role already exits");
        }
        Role role = new Role();
        role.setName(roleRequest.getName());
        roleRepository.save(role);
        return RoleDto.fromRole(role);
    }

    @Override
    public void assignRoleToUser(String username, String roleName) {
        UserAccount userAccount =userAccountRepository.findUserAccountByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(()-> new RuntimeException("Role not found"));
        UserAccountRole userAccountRole = new UserAccountRole();
        userAccountRole.setUserAccount(userAccount);
        userAccountRole.setRole(role);
        userAccountRoleRepository.save(userAccountRole);
    }

    @Override
    public UserDto getUser(String username) {
       UserAccount userAccount = userAccountRepository.findUserAccountByUsername(username)
               .orElseThrow(()-> new RuntimeException("User not found"));
       return UserDto.fromUser(userAccount);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserAccount> users = userAccountRepository.findAll();
        List<UserDto> userList = new ArrayList<>();
        users.stream()
                .map(userAccount -> userList.add(UserDto.fromUser(userAccount)));
        return userList;
    }

    @Override
    public List<String> findUserRoles(String username) {
        List<String> roles = new ArrayList<>();
        List<UserAccountRole> userAccountRoles = userAccountRoleRepository
                .findByUserAccount_Username(username);
        if (!userAccountRoles.isEmpty()){
            for (UserAccountRole userRole: userAccountRoles
                 ) {
                roles.add(userRole.getRole().getName());
            }
        }
        return roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = findUserRoles(userAccount.getUsername());
        roles.stream()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return new User(userAccount.getUsername(),userAccount.getPassword(),authorities);
    }
}
