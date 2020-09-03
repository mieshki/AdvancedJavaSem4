package pl.jazapp.app.Auctions;

import pl.jazapp.app.users.UserSearchService;
import pl.jazapp.app.webapp.Auctions.AuctionRequest;
import pl.jazapp.app.Categories.CategorySearchService;
import pl.jazapp.app.webapp.UserContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AuctionCreatorService {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private CategorySearchService categorySearch;
    @Inject
    private UserSearchService userSearch;
    @Inject
    AuctionSearchService auctionSearch;

    @Transactional
    public void addAuction(AuctionRequest req) {
        var auction = new AuctionEntity();
        auction.setTitle(req.getTitle());
        auction.setDescription(req.getDescription());

        if(req.getPrice() == null){
            auction.setPrice(new BigDecimal(0));
        } else {
            auction.setPrice(req.getPrice());
        }

        var category = categorySearch.findCategoryById(req.getCategory_id());

        if(category.isEmpty()){
            return;
        }

        auction.setCategory(category.get());
        auction.setOwner(userSearch.findUser(UserContext.getId()).get());
        auction.setVersion(1L);


        //dodawanie zdjec
        PhotoEntity photo1 = new PhotoEntity();
        PhotoEntity photo2 = new PhotoEntity();
        PhotoEntity photo3 = new PhotoEntity();

        photo1.setUrl(req.getPhoto1());
        photo2.setUrl(req.getPhoto2());
        photo3.setUrl(req.getPhoto3());

        photo1.setAuction_id(auction);
        photo2.setAuction_id(auction);
        photo3.setAuction_id(auction);

        List<PhotoEntity> photosList = new ArrayList<>();
        photosList.add(photo1);
        photosList.add(photo2);
        photosList.add(photo3);

        auction.setPhotos(photosList);

        em.persist(auction);
    }

    @Transactional
    public void editAuction(AuctionRequest req){
        var auction = new AuctionEntity();
        auction.setId(req.getId());
        auction.setTitle(req.getTitle());
        auction.setDescription(req.getDescription());

        if(req.getPrice() == null){
            auction.setPrice(new BigDecimal(0));
        } else {
            auction.setPrice(req.getPrice());
        }

        auction.setCategory(categorySearch.findCategoryById(req.getCategory_id()).get());
        auction.setOwner(userSearch.findUser(UserContext.getId()).get());
        auction.setVersion(1L);

        //edytowanie zdjec
        PhotoEntity photo1 = new PhotoEntity();
        PhotoEntity photo2 = new PhotoEntity();
        PhotoEntity photo3 = new PhotoEntity();

        var actualnAuction = auctionSearch.findAuctionById(req.getId());
        if(actualnAuction.isEmpty()){
            return;
        }

        photo1.setId(actualnAuction.get().getPhotos().get(0).getId());
        photo2.setId(actualnAuction.get().getPhotos().get(1).getId());
        photo3.setId(actualnAuction.get().getPhotos().get(2).getId());

        photo1.setUrl(req.getPhoto1());
        photo2.setUrl(req.getPhoto2());
        photo3.setUrl(req.getPhoto3());

        photo1.setAuction_id(auction);
        photo2.setAuction_id(auction);
        photo3.setAuction_id(auction);

        List<PhotoEntity> photosList = new ArrayList<>();
        photosList.add(photo1);
        photosList.add(photo2);
        photosList.add(photo3);

        auction.setPhotos(photosList);

        em.merge(auction);
    }
}