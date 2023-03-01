package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDAOService service;
    private final UserRepository userRepository;

    public UserController(UserDAOService service,
                          UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserV1> retriveAllUsers() {
        return service.findAll();
    }

    // Get /users/1 or /users/10
    @GetMapping("/users/{id}")
    public EntityModel<UserV1> retrieveUsers(@PathVariable int id){
        UserV1 user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<UserV1> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers()); // 전체 사용자 조회
        // get Endpoint of retrieveAllUsers
        model.add(linkTo.withRel("all-users"));
        // add Url
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<UserV1> createUser(@Valid @RequestBody UserV1 user){
        UserV1 saveUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        UserV1 user = service.deleteById(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

    @PutMapping("/users/{id}")
    private UserV1 updateUser(@PathVariable int id, @RequestBody UserV1 user) {
        UserV1 updateUser = service.updateUser(id, user);

        if (updateUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return updateUser;
    }

}
