package apap.ta.controller;

import apap.ta.model.RequestUpdateItemModel;

import apap.ta.rest.ListItemDetail;
import apap.ta.service.ItemRestService;
import apap.ta.service.PegawaiServiceImpl;

import apap.ta.service.RequestUpdateItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private PegawaiServiceImpl pegawaiService;

    @Autowired
    private RequestUpdateItemRestService ruirs;

    @GetMapping("/list-item")
    private String getListItem(Model model) {
        Mono<ListItemDetail> itemapi = itemRestService.getListItem();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ListItemDetail itemDetail = new ListItemDetail();
        itemDetail.setMessage(itemapi.block().getMessage());
        itemDetail.setStatus(itemapi.block().getStatus());
        itemDetail.setResult(itemapi.block().getResult());

        model.addAttribute("listItem", itemapi.block().getResult());
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("role", rolePegawai);

        return "daftar-item";
    }

    @GetMapping("/list-request-update-item")
    private String getListRequestUpdateItem(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<RequestUpdateItemModel> listUpdate = ruirs.retrieveListRequestUpdateItem();
        System.out.print("LIATNIHIH WOI");
        System.out.print(listUpdate);
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("listUpdate", listUpdate);
        model.addAttribute("role", rolePegawai);
        return "daftar-request";
    }

    @GetMapping(value="/item/{uuid}/{nama}/{kategori}/{stok}/{harga}")
    private String detailItem(
        @PathVariable ("uuid") String uuid,
        @PathVariable ("nama") String nama,
        @PathVariable ("kategori") String kategori,
        @PathVariable ("stok") String stok,
        @PathVariable ("harga") String harga,
        Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("uuid",uuid);
        model.addAttribute("nama",nama);
        model.addAttribute("stok",stok);
        model.addAttribute("kategori",kategori);
        model.addAttribute("harga",harga);
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("role", rolePegawai);
        return "detail-item";
    }
}