package com.karacamehmet.karacablog.controller;


import com.karacamehmet.karacablog.dto.request.ChangePasswordRequest;
import com.karacamehmet.karacablog.dto.request.UpdateUserRequest;
import com.karacamehmet.karacablog.dto.response.GetUserResponse;
import com.karacamehmet.karacablog.dto.response.UpdateUserResponse;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<GetUserResponse> getUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUserName(username), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UpdateUserResponse> updateUserByUserName(@PathVariable String username, @Valid @RequestBody UpdateUserRequest request){
        return new ResponseEntity<>(userService.updateUserByUserName(username,request),HttpStatus.OK);
    }

    @PutMapping("/{username}/password")
    public ResponseEntity<Void> changePasswordByUserName(@PathVariable String username,@Valid @RequestBody ChangePasswordRequest request){
        return new ResponseEntity<>(userService.changePasswordByUserName(username,request),HttpStatus.OK);
    }
}
