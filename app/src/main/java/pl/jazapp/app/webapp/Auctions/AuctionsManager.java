package pl.jazapp.app.webapp.Auctions;

import pl.jazapp.app.Auctions.AuctionCreatorService;
import pl.jazapp.app.Auctions.AuctionEntity;
import pl.jazapp.app.Auctions.AuctionSearchService;
import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.UserContext;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@RequestScoped
public class AuctionsManager {

    @Inject
    AuctionCreatorService auctionCreator;
    @Inject
    AuctionSearchService auctionSearch;

    public String addAuction(AuctionRequest req)
    {
        if(auctionSearch.findAuctionById(req.getId()).isEmpty() || req.getId() == 0)
        {
            auctionCreator.addAuction(req);
        } else {
            auctionCreator.editAuction(req);
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public void isUserOwnerOrAdmin(AuctionRequest req){
        if(req.getId() != null) {
            Optional<AuctionEntity> auction = auctionSearch.findAuctionById(req.getId());
                if(auction.isEmpty()){
                    return;
                }

                if(!auction.get().getOwner().getId().equals(UserContext.getId()) && !UserContext.getRole().contains("ADMIN")){
                    FacesContext fc = FacesContext.getCurrentInstance();

                    ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

                    nav.performNavigation("/index.xhtml");
                }
        }
    }

    public List<AuctionEntity> getAllAuctions()
    {
        return auctionSearch.getAllAuctions();
    }

    public List<AuctionEntity> getAllUserAuctions()
    {
        List<AuctionEntity> allAuctions = auctionSearch.getAllAuctions();
        List<AuctionEntity> allUserAuctions = new ArrayList<AuctionEntity>();

        for(int i = 0; i < allAuctions.size(); i++){
            if(allAuctions.get(i).getOwner().getId().equals(UserContext.getId())){
                allUserAuctions.add(allAuctions.get(i));
            }
        }

        return allUserAuctions;
    }
}
