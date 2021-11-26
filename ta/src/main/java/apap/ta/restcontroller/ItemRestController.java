package apap.ta.restcontroller;

import apap.ta.rest.ItemDetail;
import apap.ta.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {
    @Autowired
    private ItemRestService itemRestService;

    @GetMapping(value = "/list-item")
    private List<Object> retrieveListItem(
            Model model
    ){

        Mono<ItemDetail> itemapi = itemRestService.getListItem();
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setMessage(itemapi.block().getMessage());
        itemDetail.setStatus(itemapi.block().getStatus());
        itemDetail.setResult(itemapi.block().getResult());

        itemapi.block().getResult();
//        Map<String, Object> data = new HashMap<>();
//        data.put("message", itemDetail.getMessage());
//        data.put("status", itemDetail.getStatus());
//        data.put("result", itemDetail.getResult());
//        System.out.println(data.get("result"));
//        model.addAttribute("listItem", data.get("result"));
        System.out.println("masuk sini");
        System.out.println(itemapi.block().getResult());
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("listItem",itemapi.block().getResult());
//        modelAndView.setViewName("daftar-item");
//        return itemapi.blok();
        return itemapi.block().getResult();

    }

}
