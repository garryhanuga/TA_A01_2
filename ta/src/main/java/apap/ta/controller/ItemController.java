package apap.ta.controller;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.rest.ItemDetail;
import apap.ta.restcontroller.ItemRestController;
import apap.ta.restcontroller.RequestUpdateItemRestController;
import apap.ta.service.ItemRestService;
import apap.ta.service.RequestUpdateItemRestService;
import apap.ta.service.ItemRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private RequestUpdateItemRestService ruirs;

    @GetMapping("/list-item")
    private String getListItem(Model model) {
        Mono<ItemDetail> itemapi = itemRestService.getListItem();
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setMessage(itemapi.block().getMessage());
        itemDetail.setStatus(itemapi.block().getStatus());
        itemDetail.setResult(itemapi.block().getResult());

        System.out.println("masuk sini getListItem di ItemController");
        System.out.println(itemapi.block().getStatus());
        System.out.println(itemapi.block().getMessage());
        System.out.println(itemapi.block().getResult());
        model.addAttribute("listItem", itemapi.block().getResult());
        return "daftar-item";
    }

    @GetMapping("/list-request-update-item")
    private String getListRequestUpdateItem(Model model) {
        List<RequestUpdateItemModel> listUpdate = ruirs.retrieveListRequestUpdateItem();
        System.out.print("LIATNIHIH WOI");
        System.out.print(listUpdate);
        model.addAttribute("listUpdate", listUpdate);
        return "daftar-request";
    }

}