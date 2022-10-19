package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {

    }

    @Override
    public String getAuthority() {
        return role;
    }
}
