package pl.jazapp.app.Auctions;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.jazapp.app.users.UserEntity;
import pl.jazapp.app.Categories.CategoryEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auction")
@NamedQuery(name="Auction.findAll", query="SELECT a FROM AuctionEntity a")
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name ="owner_id")
    private UserEntity owner;

    @Column(name="version")
    private Long version;

    public List<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
    }

    @OneToMany(mappedBy = "auction_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhotoEntity> photos;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "auction_id", cascade = CascadeType.ALL)
    private List<Auction_ParameterEntity> auction_parametersEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Auction_ParameterEntity> getAuction_parametersEntities() {
        return auction_parametersEntities;
    }

    public void setAuction_parametersEntities(List<Auction_ParameterEntity> auction_parametersEntities) {
        this.auction_parametersEntities = auction_parametersEntities;
    }
}
