package com.simaomonteiro18.pitchbooking.controllers;

import com.simaomonteiro18.pitchbooking.dtos.UserSummaryDTO;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.mappers.UserMapper;
import com.simaomonteiro18.pitchbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> findById(@PathVariable Long id) {

        User user = userService.findById(id);
        
        UserSummaryDTO userSummaryDTO = UserMapper.toDTO(user);

        return ResponseEntity.ok().body(userSummaryDTO);

    }

}
