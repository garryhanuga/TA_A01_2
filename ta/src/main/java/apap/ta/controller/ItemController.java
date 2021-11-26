package apap.ta.controller;

import apap.ta.rest.ItemDetail;
import apap.ta.service.ItemRestService;
import apap.ta.service.PegawaiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private PegawaiServiceImpl pegawaiService;

    @GetMapping("/list-item")
    private String getListItem(Model model) {
        Mono<ItemDetail> itemapi = itemRestService.getListItem();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setMessage(itemapi.block().getMessage());
        itemDetail.setStatus(itemapi.block().getStatus());
        itemDetail.setResult(itemapi.block().getResult());


        model.addAttribute("listItem", itemapi.block().getResult());
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("role", rolePegawai);

        System.out.println(rolePegawai);
        return "daftar-item";
    }

}