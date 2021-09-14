package com.company.my_online_store.model.entity;

import com.company.my_online_store.model.auth.RefreshToken;
import com.company.my_online_store.model.entity.enums.KnownAuthority;
import com.company.my_online_store.model.entity.enums.UserStatus;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@SuppressWarnings("FieldMayBeFinal")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(nullable = false)
    @Size(min = 8, message = "Length must be more than 8")
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(name = "role")
    private String role = "ROLE_CUSTOMER";

    @NotEmpty
    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKey(name = "id")
    private Map<KnownAuthority, UserAuthority> authorities = new EnumMap<KnownAuthority, UserAuthority>(KnownAuthority.class);

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<RefreshToken> getRefreshTokenList() {
        return refreshTokenList;
    }

    public void setRefreshTokenList(List<RefreshToken> refreshTokenList) {
        this.refreshTokenList = refreshTokenList;
    }

    public Map<KnownAuthority, UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Map<KnownAuthority, UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return email.equals(((User) obj).email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", cart=" + cart +
                ", refreshTokenList=" + refreshTokenList +
                ", authorities=" + authorities +
                '}';
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
