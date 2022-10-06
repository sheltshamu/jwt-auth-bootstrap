package zw.co.getsol.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.getsol.request.RoleRequest;
import zw.co.getsol.request.UserRequest;
import zw.co.getsol.responses.RoleDto;
import zw.co.getsol.responses.UserDto;
import zw.co.getsol.service.UserAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userAccountService.getAllUsers());
    }
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequest userRequest){
        UserDto userDto = userAccountService.saveUser(userRequest);
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/role")
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleRequest roleRequest){
        RoleDto roleDto = userAccountService.saveRole(roleRequest);
        return ResponseEntity.ok(roleDto);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<String>> getRoles(@PathVariable String username){
       List<String> roles =  userAccountService.findUserRoles(username);
       return ResponseEntity.ok(roles);
    }
    @PostMapping("/{username}/{roleName}")
    public ResponseEntity<String> assignRoles(@PathVariable String username
            ,@PathVariable String roleName){
        userAccountService.assignRoleToUser(username,roleName);
        return ResponseEntity.ok("successfully assigned !");
    }
}
