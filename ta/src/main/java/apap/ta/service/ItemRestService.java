package apap.ta.service;

import apap.ta.rest.ItemDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemRestService {
    Mono<ItemDetail> getListItem();
    List<Object> getListResultItem();
}
