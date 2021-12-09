package apap.ta.service;

import apap.ta.model.DeliveryModel;
import apap.ta.repository.DeliveryDb;
import apap.ta.rest.DeliveryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.ta.rest.Setting;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class DeliveryRestServiceImpl implements DeliveryRestService{
    private final WebClient webClient;

    public DeliveryRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.cabangUrl).build();
    }

    @Autowired
    DeliveryDb deliveryDb;
    
    @Override
    public DeliveryModel addDelivery(DeliveryModel delivery) {
        return deliveryDb.save(delivery);
    }

    @Override
    public List<DeliveryModel> getDeliveryList() {
        return deliveryDb.findAll();
    }

    @Override
    public Mono<String> getListCabang() {
        return this.webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public DeliveryModel getIdDelivery(Long idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDb.findByIdDelivery(idDelivery);
        if(delivery.isPresent()){
            return delivery.get();
        }
        return null;
    }

    //Mock Server
    @Override
    public Mono<String> getListCabangMock(){
        return this.webClient.get().uri("/rest/listCabang/").retrieve().bodyToMono(String.class);
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel delivery) {
        return deliveryDb.save(delivery);
    }


//    @Override
//    public List<DeliveryDetail> getListCabangRetail(Long idCabang) {
//        List<DeliveryDetail> listCabang =
//        for(int i = 0; i<)
//    }


}
