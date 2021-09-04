package com.example.my_online_store.model.entity;

import com.example.my_online_store.model.entity.enums.KnownAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class UserAuthority {
    @Id
    @Column(nullable = false,unique = true)
    private KnownAuthority id;

    @ManyToMany(mappedBy = "authorities")
    @SuppressWarnings("FieldMayBeFinal")
    private Set<User> userSet = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        UserAuthority that = (UserAuthority) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public KnownAuthority getId() {
        return id;
    }

    public void setId(KnownAuthority id) {
        this.id = id;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
