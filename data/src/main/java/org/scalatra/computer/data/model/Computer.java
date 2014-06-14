package org.scalatra.computer.data.model;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(name = "computer_seq", sequenceName = "computer_seq")
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Getter
@Setter
public class Computer implements Serializable {

    public Computer() {}

    public Computer(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "computer_seq")
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private DateTime introduced;

    @Temporal(TemporalType.DATE)
    private Date discontinued;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "companyId", nullable = true)
    private Company company;


    public int hashCode() {
        return (id != null) ? id.hashCode() : Objects.hashCode(name);
    }

    public Objects.ToStringHelper buildStringHelper() {
        return Objects.toStringHelper(this)
                      .add("name", name)
                      .add("introduced", introduced)
                      .add("discontinued", discontinued)
                      .add("company", company);
    }

    private static final long serialVersionUID = 2688919017191329807L;
}
