package apap.ta.service;

import apap.ta.rest.ItemDetail;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ItemRestService {
    Mono<ItemDetail> getListItem();
//    Map<String,String> listItem();
}
