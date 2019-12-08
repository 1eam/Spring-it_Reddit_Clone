package com.vega.springit.domain;


import com.vega.springit.domain.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@PasswordsMatch
public class User implements UserDetails {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    @Size (min = 8, max = 20)
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(length = 100)
    private String password;

    @Transient
    @NotEmpty(message = "Please enter confirm password field")
    private String confirmPassword;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

/*Relationship*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }

    @NonNull
    @NotEmpty(message = "You must enter First Name.")
    private String firstName;

    @NonNull
    @NotEmpty(message = "You must enter Last Name.")
    private String lastName;

    //Derrived propersy (combined)
    @Transient //This annotation tells us to not (initially) store this in the DB. Were going to combine the Firstname entry with the Lastname and therefore only going to use a get
    @Setter(AccessLevel.NONE)
    private String fullName;

    //Users might not want to dislplay their first&lastname or emailaddres on their post. So instead of this we'll let them choose their "username"
    @NonNull
    @NotEmpty(message = "Please enter alias.")
    @Column(nullable = false, unique = true)
    private String alias;

    //Get First & Lastname & Combine
    public String getFullName() {
        return firstName + " " + lastName;
    }

/* User Details Methods overriding*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
