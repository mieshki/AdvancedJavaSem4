package pl.jazapp.app.Auctions;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parameter")
@NamedQuery(name="parameter.findAll", query="SELECT p FROM ParameterEntity p")
public class ParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parameter_id")
    private List<Auction_ParameterEntity> parameter_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Auction_ParameterEntity> getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(List<Auction_ParameterEntity> parameter_id) {
        this.parameter_id = parameter_id;
    }
}
