package pl.jazapp.app.Auctions;

import pl.jazapp.app.users.UserEntity;
import pl.jazapp.app.Categories.CategoryEntity;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(name="photo_url")
    private String photos_urls;

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

    public String getPhotos_urls() {
        return photos_urls;
    }

    public void setPhotos_urls(String photos_urls) {
        this.photos_urls = photos_urls;
    }
}
