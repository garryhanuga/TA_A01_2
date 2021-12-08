package apap.ta.controller;

import apap.ta.model.DeliveryModel;
import apap.ta.model.PegawaiModel;
import apap.ta.model.RequestUpdateItemModel;
import apap.ta.service.DeliveryRestService;
import apap.ta.service.PegawaiService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping(value="/delivery/add/{idRequestUpdateItem}/{idCabang}")
    private String assignDeliveryForm(
            @PathVariable Long idRequestUpdateItem,
            @PathVariable Long idCabang,
            Model model
    ) {
        DeliveryModel delivery = new DeliveryModel();
        List<PegawaiModel> listPegawai =  pegawaiService.getPegawaiList();
        List<PegawaiModel> listKurir = new ArrayList<>();
        for(PegawaiModel pegawai : listPegawai){
            if(pegawai.getRole().getNamaRole().equals("STAFF_KURIR")){
                listKurir.add(pegawai);
            }
        }
        model.addAttribute("listKurir", listKurir);
        model.addAttribute("delivery", delivery);
        return "form-pilih-kurir";
    }

    @PostMapping(value="/delivery/add/{idRequestUpdateItem}/{idCabang}")
    private String assignDeliverySubmit(
            @PathVariable Long idRequestUpdateItem,
            @PathVariable Long idCabang,
            @ModelAttribute DeliveryModel delivery,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel staff_op = pegawaiService.getPegawai(auth.getName());
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemRestService.getRequestItemModelByIdRequestItemModel(idRequestUpdateItem);
        PegawaiModel kurir = pegawaiService.getPegawai(delivery.getPegawai().getUsername());
        int counter = staff_op.getCounter();
        counter+=1;
        delivery.setRequestUpdateItem(requestUpdateItem);
        delivery.setIdCabang(idCabang);
        java.util.Date date = new java.util.Date();
        Date tgl_sekarang = new Date(date.getTime());
        delivery.setTanggalDibuat(tgl_sekarang);
        delivery.setTanggalDikirim(tgl_sekarang);
        // set tanggal dikirim sementara tgl skrg dulu
        staff_op.setCounter(counter);
        pegawaiService.updatePegawai(staff_op);
        delivery.setPegawai(kurir);
        delivery.setSent(false);
        deliveryRestService.addDelivery(delivery);
        model.addAttribute("delivery", delivery);
        return "add-delivery";
    }

    @GetMapping("/list-delivery")
    public String listDelivery(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PegawaiModel peg = pegawaiService.getPegawai(auth.getName());
        System.out.println("nama kurir" + peg.getNamaPegawai());
        model.addAttribute("nama", peg.getIdPegawai());
        model.addAttribute("role", peg.getRole().getNamaRole());
        List<DeliveryModel> listDelivery = deliveryRestService.getDeliveryList();
        model.addAttribute("listDelivery", listDelivery);
        return "daftar-delivery";
    }





}
