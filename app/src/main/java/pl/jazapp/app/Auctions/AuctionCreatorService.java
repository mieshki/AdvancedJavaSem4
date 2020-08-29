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

@ApplicationScoped
public class AuctionCreatorService {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private CategorySearchService categorySearch;
    @Inject
    private UserSearchService userSearch;

    @Transactional
    public void addAuction(AuctionRequest req) {
        var auction = new AuctionEntity();
        auction.setTitle(req.getTitle());
        auction.setDescription(req.getDescription());
        auction.setPrice(req.getPrice());
        auction.setCategory(categorySearch.findCategoryById(req.getCategory_id()).get());
        auction.setOwner(userSearch.findUser(UserContext.getId()).get());
        auction.setVersion(1L);
        auction.setPhotos_urls(req.getPhotos_urls());

        em.persist(auction);
    }

    @Transactional
    public void editAuction(AuctionRequest req){
        var auction = new AuctionEntity();
        auction.setId(req.getId());
        auction.setTitle(req.getTitle());
        auction.setDescription(req.getDescription());
        auction.setPrice(req.getPrice());
        auction.setCategory(categorySearch.findCategoryById(req.getCategory_id()).get());
        auction.setOwner(userSearch.findUser(UserContext.getId()).get());
        auction.setVersion(1L);
        auction.setPhotos_urls(req.getPhotos_urls());

        em.merge(auction);
    }
}