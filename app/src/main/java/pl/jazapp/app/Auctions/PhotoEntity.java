package pl.jazapp.app.Auctions;

import javax.persistence.*;

@Entity
@Table(name = "photo")
@NamedQuery(name="Photo.findAll", query="SELECT p FROM PhotoEntity p")
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuctionEntity getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(AuctionEntity auction_id) {
        this.auction_id = auction_id;
    }
}
