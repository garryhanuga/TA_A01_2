package apap.ta.service;

import apap.ta.rest.ListItemDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<ListItemDetail> getListItem();
}
