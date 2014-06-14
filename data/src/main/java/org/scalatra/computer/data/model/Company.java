package org.scalatra.computer.data.model;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Getter
@Setter
public class Company implements Serializable {

    public Company() {}

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public int hashCode() {
        return (id != null) ? id.hashCode() : Objects.hashCode(name);
    }

    public Objects.ToStringHelper buildStringHelper() {
        return Objects.toStringHelper(this)
                      .add("name", name);
    }

    private static final long serialVersionUID = -7682698820194155449L;
}