package apap.ta.restcontroller;
import apap.ta.model.DeliveryModel;
import apap.ta.model.PegawaiModel;
import apap.ta.rest.DeliveryDetail;
import apap.ta.rest.Setting;
import apap.ta.service.DeliveryRestService;
import apap.ta.service.PegawaiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class DeliveryRestController {
    @Autowired
    private DeliveryRestService deliveryRestService;

    @Autowired
    private PegawaiServiceImpl pegawaiService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/daftar-alamat-cabang/{idDelivery}/{idCabang}")
    private String getCabang(
            @PathVariable Long idDelivery,
            @PathVariable Long idCabang,
            Model model){
        DeliveryModel delivery = deliveryRestService.getIdDelivery(idDelivery);
        PegawaiModel kurir = delivery.getPegawai();
        String cabangUrl = Setting.cabangUrl;
        List listCabangRest = restTemplate.getForObject(cabangUrl, List.class);
        List<DeliveryDetail> cabangDelivery = new ArrayList<>();

        for(int i = 0; i< listCabangRest.size(); i++){
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            LinkedHashMap<String, Object> firstHash = (LinkedHashMap<String, Object>) listCabangRest.get(i);
            String id_cabang = String.valueOf(firstHash.get("id"));
            Long id = Long.parseLong(id_cabang);
//            String namaCabang = String.valueOf(firstHash.get("nama"));
            String alamatCabang = String.valueOf(firstHash.get("alamat"));

            deliveryDetail.setIdCabang(id);
//            deliveryDetail.setNamaCabang(namaCabang);
            deliveryDetail.setAlamatCabang(alamatCabang);

            cabangDelivery.add(deliveryDetail);
        }

        for(DeliveryDetail cabang: cabangDelivery){
            if(cabang.getIdCabang().equals(idCabang)){
                delivery.setSent(true);
                deliveryRestService.updateDelivery(delivery);
                pegawaiService.addCounter(kurir);
                model.addAttribute("cabang", cabang);
                model.addAttribute("delivery", delivery);
                return "cabang-found";
            }
        }
        model.addAttribute("delivery", delivery);
        return "cabang-not-found";
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
