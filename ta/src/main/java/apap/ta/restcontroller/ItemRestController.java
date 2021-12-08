package apap.ta.restcontroller;

import apap.ta.rest.ItemDetail;
import apap.ta.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v2")
public class ItemRestController {
    @Autowired
    private ItemRestService itemRS;

    @GetMapping(value = "/item/{uuid}")
    private ItemDetail getItem(@PathVariable String uuid) {
        try {
            return itemRS.getItem(uuid);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item tidak ditemukan");
        }
    }

}
