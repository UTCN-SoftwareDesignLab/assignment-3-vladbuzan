package com.assignment3.assignment3.user;

import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.assignment3.assignment3.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAllNonAdmin(@RequestParam int page, @RequestParam int usersPerPage) {
        try {
            var users = userService.findAllNonAdmin(page, usersPerPage);
            return ResponseEntity.ok(users);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }
}
