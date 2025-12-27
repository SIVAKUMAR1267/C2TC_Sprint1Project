package shopowner;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class shopownerservices {

    @Autowired
    private shoprepo repo;

    // Get a list of all shop owners
    public List<Shopowner> listAll() {
        return repo.findAll();
    }

    // Save a shop owner (insert or update)
    public void save(Shopowner shopowner) {
        repo.save(shopowner);
    }

    // Get a shop owner by ID, throws an exception if not found
    public Shopowner get(Integer id) {
        Optional<Shopowner> shopOwner = repo.findById(id);
        return shopOwner.orElseThrow(() -> new RuntimeException("ShopOwner not found with id: " + id));
    }

    // Delete a shop owner by ID
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
