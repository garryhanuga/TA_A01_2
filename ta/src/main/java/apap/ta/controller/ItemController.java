package apap.ta.controller;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.rest.ItemDetail;
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

    @GetMapping(value="/item/{uuid}")
    private String detailItem(
        @PathVariable ("uuid") String uuid,
        Model model){
        System.out.println("masuk sini");
        System.out.println("masuk sini");
        System.out.println("masuk sini");
        System.out.println("masuk sini");
        Mono<ItemDetail> detailItemApi = itemRestService.getItem(uuid);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setMessage(detailItemApi.block().getMessage());
        itemDetail.setStatus(detailItemApi.block().getStatus());
        itemDetail.setResult(detailItemApi.block().getResult());
        itemDetail.getResult().setUuid(detailItemApi.block().getResult().getUUID());
        itemDetail.getResult().setNama(detailItemApi.block().getResult().getNama());
        itemDetail.getResult().setStok(detailItemApi.block().getResult().getStok());
        itemDetail.getResult().setKategori(detailItemApi.block().getResult().getKategori());
        itemDetail.getResult().setHarga(detailItemApi.block().getResult().getHarga());
        
        model.addAttribute("uuid",itemDetail.getResult().getUUID());
        model.addAttribute("nama",itemDetail.getResult().getNama());
        model.addAttribute("stok",itemDetail.getResult().getStok());
        model.addAttribute("kategori",itemDetail.getResult().getKategori());
        model.addAttribute("harga",itemDetail.getResult().getHarga());
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();
        model.addAttribute("role", rolePegawai);
        return "detail-item";
    }
}