package apap.ta.controller;

import apap.ta.model.ItemModel;
import apap.ta.model.MesinModel;
import apap.ta.model.PegawaiModel;
import apap.ta.model.ProduksiModel;
import apap.ta.model.RequestUpdateItemModel;
import apap.ta.repository.MesinDb;
import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListDetail;
import apap.ta.service.ItemRestService;
import apap.ta.service.PegawaiServiceImpl;

import apap.ta.service.RequestUpdateItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private PegawaiServiceImpl pegawaiService;

    @Autowired
    private RequestUpdateItemRestService ruirs;

    @Autowired
    private MesinDb mesinDb;

    @GetMapping("/list-item")
    private String getListItem(Model model) {
        Mono<ListDetail> itemapi = itemRestService.getListItem();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ListDetail items = new ListDetail();
        items.setMessage(itemapi.block().getMessage());
        items.setStatus(itemapi.block().getStatus());
        items.setResult(itemapi.block().getResult());
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

    private Long getIdKategori(String katstr) {
        HashMap<String, Integer> kaMap = new HashMap<String, Integer>(14);
        kaMap.put("BUKU", 1);
        kaMap.put("DAPUR", 2);
        kaMap.put("MAKANAN & MINUMAN", 3);
        kaMap.put("ELEKTRONIK", 4);
        kaMap.put("FASHION", 5);
        kaMap.put("KECANTIKAN & PERAWATAN DIRI", 6);
        kaMap.put("FILM & MUSIK", 7);
        kaMap.put("GAMING", 8);
        kaMap.put("GADGET", 9);
        kaMap.put("KESEHATAN", 10);
        kaMap.put("RUMAH TANGGA", 11);
        kaMap.put("FURNITURE", 12);
        kaMap.put("ALAT & PERANGKAT KERAS", 13);
        kaMap.put("WEDDING", 14);

        for (String str: kaMap.keySet()) {
            if (Objects.equals(str, katstr)) {
                return Long.valueOf(kaMap.get(str));
            }
        }
        return Long.valueOf(0);
    }

    @GetMapping(value = "/item/update/{uuid}")
    private String updateItem(@PathVariable String uuid, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ItemDetail idet = itemRestService.getItem(uuid);
        int add_stok = 0;
        String kat = idet.getKategori();
        Long idkat = getIdKategori(kat);
        List<MesinModel> mesinList = mesinDb.findAllByidKategori(idkat);
        MesinModel pilihan = new MesinModel();
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();

        model.addAttribute("role", rolePegawai);
        model.addAttribute("item", uuid);
        model.addAttribute("tambahan_stok", add_stok);
        model.addAttribute("mesinList", mesinList);
        model.addAttribute("pilmesin", pilihan);

        return "form-update-item";
    }

    @PostMapping(value = "/item/update")
    public String updateItemSubmit(@ModelAttribute ItemDetail item, 
                                    @ModelAttribute int stok, 
                                    @ModelAttribute MesinModel mesin) {
        int total_stok = item.getStok() + stok;
        item.setStok(total_stok);
        ItemDetail updated = itemRestService.updateItem(item);
        ProduksiModel prod = new ProduksiModel();
        Long idkat = getIdKategori(item.getKategori());
        Date dt = new Date();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel pegawai = pegawaiService.getPegawai(auth.getName());
        prod.setIdItem(updated.getUuid());
        prod.setIdKategori(idkat);
        prod.setMesin(mesin);
        prod.setRequestUpdateItem(null);
        prod.setPegawai(pegawai);
        prod.setTambahanStok(stok);
        prod.setTanggalProduksi(dt);

        int capMesin = mesin.getKapasitas() - 1;
        mesin.setKapasitas(capMesin);

        int ctr = pegawai.getCounter() + 1;
        pegawai.setCounter(ctr);
        return "update-berhasil";
    }

    @GetMapping(value="request/update/{id}")
    private String updateItemByRequest(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        RequestUpdateItemModel rui = ruirs.getRequestById(id);
        List<MesinModel> mesinList = mesinDb.findAllByidKategori(rui.getIdKategori());
        MesinModel pilihan = new MesinModel();
        String rolePegawai = auth.getAuthorities().toArray()[0].toString();

        model.addAttribute("role", rolePegawai);
        model.addAttribute("item", rui.getIdItem());
        model.addAttribute("tambahan_stok", rui.getTambahanStok());
        model.addAttribute("mesinList", mesinList);
        model.addAttribute("pilmesin", pilihan);

        return "form-update-item";
    }
    

}