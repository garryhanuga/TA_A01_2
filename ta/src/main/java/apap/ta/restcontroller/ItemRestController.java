package apap.ta.restcontroller;

import apap.ta.model.ItemModel;
import apap.ta.model.MesinModel;
import apap.ta.rest.ItemDetail;
import apap.ta.service.ItemRestService;
import apap.ta.service.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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


    // @PutMapping(value = "/item/{uuid}")
    // private ItemDetail updateItem(@PathVariable String uuid, @RequestBody int stok) {
    //     try {

    //     } catch (NoSuchElementException e) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item tidak ditemukan");
    //     }

    // }

}
