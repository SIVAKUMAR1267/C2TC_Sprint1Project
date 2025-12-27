package shopowner;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/shopowners")
public class shopcontroller {

    @Autowired
    private shopownerservices shopownerService;

    // Retrieve operation (retrieving all Owners)
    @GetMapping
    public List<Shopowner> listAll() {
        return shopownerService.listAll();
    }

    // Retrieve Operation (retrieving Owner by id)
    @GetMapping("/{id}")
    public ResponseEntity<Shopowner> get(@PathVariable Integer id) {
        try {
            Shopowner c1 = shopownerService.get(id);
            return new ResponseEntity<>(c1, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create Operation
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Shopowner shopowner) {
        shopownerService.save(shopowner);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update Operation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShopOwner(@RequestBody Shopowner shopowner, @PathVariable Integer id) {
        try {
            // Check if the shop owner exists by ID
            Shopowner existingShopOwner = shopownerService.get(id);  // Assuming get() retrieves the ShopOwner by ID

            // Update the existing shop owner with the new values
            existingShopOwner.setName(shopowner.getName());
            existingShopOwner.setShopName(shopowner.getShopName());
            existingShopOwner.setContactInfo(shopowner.getContactInfo());

            // Save the updated shop owner (this will update the existing record)
            shopownerService.save(existingShopOwner);

            return ResponseEntity.ok().build(); // Return OK status (200)
        } catch (NoSuchElementException e) {
            // If the shop owner with the given ID doesn't exist, return NOT_FOUND (404)
            return ResponseEntity.notFound().build();
        }
    }



    // Delete Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            shopownerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
