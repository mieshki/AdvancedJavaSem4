package pl.jazapp.app.Auctions;

import pl.jazapp.app.webapp.UserContext;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuctionSearchService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<AuctionEntity> findAuctionById(Long id)
    {
        return em.createQuery("from AuctionEntity where id = :id", AuctionEntity.class)
                .setParameter("id", id)
                .getResultList().stream()
                .findFirst();
    }

    @Transactional
    public List<AuctionEntity> getAllAuctions()
    {
        return em.createNamedQuery("Auction.findAll", AuctionEntity.class).getResultList();
    }

    @Transactional
    public List<AuctionEntity> getAllAuctionsOfUserId()
    {
        var allAuctions = em.createNamedQuery("Auction.findAll", AuctionEntity.class).getResultList();
        var allUserAuctions = new ArrayList<AuctionEntity>();

        for(int i = 0; i < allAuctions.size(); i++){
            if(allAuctions.get(i).getOwner().getId().equals(UserContext.getId())){
                allUserAuctions.add(allAuctions.get(i));
            }
        }

        return allUserAuctions;
    }
}
