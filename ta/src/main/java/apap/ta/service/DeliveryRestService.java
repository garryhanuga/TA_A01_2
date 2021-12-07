package apap.ta.service;
import apap.ta.model.DeliveryModel;

import apap.ta.rest.DeliveryDetail;
import reactor.core.publisher.Mono;


import java.util.List;

public interface DeliveryRestService {
    DeliveryModel addDelivery(DeliveryModel delivery);
    List<DeliveryModel> getDeliveryList();
    Mono<String> getListCabang();
    DeliveryModel getIdDelivery(Long idDelivery);
    Mono<String> getListCabangMock();
//    List<DeliveryDetail> getListCabangRetail(Long idCabang);



}
