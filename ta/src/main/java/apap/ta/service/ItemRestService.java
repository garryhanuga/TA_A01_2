package apap.ta.service;

import apap.ta.rest.ItemDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<ItemDetail> getListItem();
}
