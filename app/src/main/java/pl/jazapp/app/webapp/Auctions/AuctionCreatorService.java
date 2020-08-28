package pl.jazapp.app.webapp.Auctions;

import pl.jazapp.app.users.UserSearchService;
import pl.jazapp.app.webapp.Categories.CategoriesRequest;
import pl.jazapp.app.webapp.Categories.CategoryCreatorService;
import pl.jazapp.app.webapp.Categories.CategoryEntity;
import pl.jazapp.app.webapp.Categories.CategorySearchService;
import pl.jazapp.app.webapp.Departaments.DepartmentSearchService;

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
        auction.setOwner(userSearch.findUser(1L).get());
        auction.setVersion(1L);
        auction.setPhotos_urls("blablazdjecia");

        em.persist(auction);
    }
}