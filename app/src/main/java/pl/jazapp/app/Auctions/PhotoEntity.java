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

    @Column(name = "auction_id")
    private Long auction_id;
}
