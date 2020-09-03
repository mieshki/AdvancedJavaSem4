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
import java.util.Collections;
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

        // dodawanie parametrow
        ParameterEntity parametr1 = new ParameterEntity();
        Auction_ParameterEntity parameter1Value = new Auction_ParameterEntity();
        ParameterEntity parametr2 = new ParameterEntity();
        Auction_ParameterEntity parameter2Value = new Auction_ParameterEntity();
        ParameterEntity parametr3 = new ParameterEntity();
        Auction_ParameterEntity parameter3Value = new Auction_ParameterEntity();

        parametr1.setName(req.getParam1Name());
        parameter1Value.setAuction_id(auction);
        parameter1Value.setParameter_id(parametr1);
        parameter1Value.setValue(req.getParam1Value());

        parametr2.setName(req.getParam2Name());
        parameter2Value.setAuction_id(auction);
        parameter2Value.setParameter_id(parametr2);
        parameter2Value.setValue(req.getParam2Value());

        parametr3.setName(req.getParam3Name());
        parameter3Value.setAuction_id(auction);
        parameter3Value.setParameter_id(parametr3);
        parameter3Value.setValue(req.getParam3Value());

        List<Auction_ParameterEntity> auction_parameterEntitiesList = new ArrayList<>();
        auction_parameterEntitiesList.add(parameter1Value);
        auction_parameterEntitiesList.add(parameter2Value);
        auction_parameterEntitiesList.add(parameter3Value);

        auction.setAuction_parametersEntities(auction_parameterEntitiesList);

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

        //edytowanie parametrow
        // dodawanie parametrow
        ParameterEntity parametr1 = new ParameterEntity();
        Auction_ParameterEntity parameter1Value = new Auction_ParameterEntity();
        ParameterEntity parametr2 = new ParameterEntity();
        Auction_ParameterEntity parameter2Value = new Auction_ParameterEntity();
        ParameterEntity parametr3 = new ParameterEntity();
        Auction_ParameterEntity parameter3Value = new Auction_ParameterEntity();

        parametr1.setId(actualnAuction.get().getAuction_parametersEntities().get(0).getParameter_id().getId());
        parametr1.setName(req.getParam1Name());
        parameter1Value.setId(actualnAuction.get().getAuction_parametersEntities().get(0).getId());
        parameter1Value.setAuction_id(auction);
        parameter1Value.setParameter_id(parametr1);
        parameter1Value.setValue(req.getParam1Value());
        parametr1.setParameter_id(Collections.singletonList(parameter1Value));

        parametr2.setId(actualnAuction.get().getAuction_parametersEntities().get(1).getParameter_id().getId());
        parametr2.setName(req.getParam2Name());
        parameter2Value.setId(actualnAuction.get().getAuction_parametersEntities().get(1).getId());
        parameter2Value.setAuction_id(auction);
        parameter2Value.setParameter_id(parametr2);
        parameter2Value.setValue(req.getParam2Value());
        parametr2.setParameter_id(Collections.singletonList(parameter2Value));

        parametr3.setId(actualnAuction.get().getAuction_parametersEntities().get(2).getParameter_id().getId());
        parametr3.setName(req.getParam3Name());
        parameter3Value.setId(actualnAuction.get().getAuction_parametersEntities().get(2).getId());
        parameter3Value.setAuction_id(auction);
        parameter3Value.setParameter_id(parametr3);
        parameter3Value.setValue(req.getParam3Value());
        parametr3.setParameter_id(Collections.singletonList(parameter3Value));

        List<Auction_ParameterEntity> auction_parameterEntitiesList = new ArrayList<>();
        auction_parameterEntitiesList.add(parameter1Value);
        auction_parameterEntitiesList.add(parameter2Value);
        auction_parameterEntitiesList.add(parameter3Value);

        auction.setAuction_parametersEntities(auction_parameterEntitiesList);

        em.merge(auction);
    }
}