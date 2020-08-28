package pl.jazapp.app.webapp.Auctions;

import pl.jazapp.app.webapp.Categories.CategoriesRequest;
import pl.jazapp.app.webapp.Categories.CategorySearchService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AuctionsManager {

    @Inject
    AuctionCreatorService auctionCreator;

    public String addAuction(AuctionRequest req)
    {
        auctionCreator.addAuction(req);

        return "/mine.xhtml?faces-redirect=true";
    }
}
