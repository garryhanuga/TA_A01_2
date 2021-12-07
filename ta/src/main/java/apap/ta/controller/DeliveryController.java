package apap.ta.controller;

import apap.ta.model.DeliveryModel;
import apap.ta.model.PegawaiModel;
import apap.ta.model.RequestUpdateItemModel;
import apap.ta.service.DeliveryRestService;
import apap.ta.service.PegawaiService;
import apap.ta.service.RequestUpdateItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Controller
public class DeliveryController {
    @Autowired
    DeliveryRestService deliveryRestService;

    @Autowired
    PegawaiService pegawaiService;

    @Autowired
    RequestUpdateItemRestService requestUpdateItemRestService;

    @GetMapping(value="/delivery/add/{idRequestUpdateItem}")
    private String assignDeliveryForm(
            @PathVariable Long idRequestUpdateItem,
            Model model
    ) {
        DeliveryModel delivery = new DeliveryModel();
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemRestService.getRequestItemModelByIdRequestItemModel(idRequestUpdateItem);
        delivery.setRequestUpdateItem(requestUpdateItem);
        List<PegawaiModel> listPegawai =  pegawaiService.getPegawaiList();
        List<PegawaiModel> listKurir = new ArrayList<>();
        for(PegawaiModel pegawai : listPegawai){
            if(pegawai.getRole().equals("STAFF_KURIR")){
                listKurir.add(pegawai);
            }
        }
        model.addAttribute("listKurir", listKurir);
        model.addAttribute("delivery", delivery);
        return "form-pilih-kurir";
    }

    @PostMapping(value="/delivery/add/{usernameKurir}")
    private String assignDeliverySubmit(
            @PathVariable String usernameKurir,
            @ModelAttribute DeliveryModel delivery,
            Model model
    ) {
        java.util.Date date = new java.util.Date();
        Date tgl_sekarang = new Date(date.getTime());
        delivery.setTanggalDibuat(tgl_sekarang);
        delivery.setTanggalDikirim(tgl_sekarang);
        // set tanggal dikirim sementara tgl skrg dulu
        PegawaiModel kurir = pegawaiService.getPegawai(usernameKurir);
        kurir.setCounter(kurir.getCounter()+1);
        delivery.setPegawai(kurir);
        delivery.setSent(false);
        deliveryRestService.addDelivery(delivery);
        return "home";
    }

    @GetMapping("/list-delivery")
    public String listDelivery(Model model){
        List<DeliveryModel> listDelivery = deliveryRestService.getDeliveryList();
        model.addAttribute("listDelivery", listDelivery);
        return "daftar-delivery";
    }





}