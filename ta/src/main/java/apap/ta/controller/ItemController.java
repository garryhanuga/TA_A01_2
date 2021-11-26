package apap.ta.controller;

import apap.ta.rest.ItemDetail;
import apap.ta.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @GetMapping("/list-item")
    private String getListItem(Model model) {
        Mono<ItemDetail> itemapi = itemRestService.getListItem();
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setMessage(itemapi.block().getMessage());
        itemDetail.setStatus(itemapi.block().getStatus());
        itemDetail.setResult(itemapi.block().getResult());

        model.addAttribute("listItem", itemapi.block().getResult());
        return "daftar-item";
    }

}