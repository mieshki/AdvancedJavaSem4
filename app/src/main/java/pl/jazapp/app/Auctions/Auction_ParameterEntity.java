package pl.jazapp.app.Auctions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auction_parameter")
public class Auction_ParameterEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "auction_parameter_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter_id;

    @Column(name = "value")
    private String value;

    public AuctionEntity getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(AuctionEntity auction_id) {
        this.auction_id = auction_id;
    }

    public ParameterEntity getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(ParameterEntity parameter_id) {
        this.parameter_id = parameter_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
