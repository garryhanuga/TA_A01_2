package apap.ta.service;

import java.util.HashMap;

import org.springframework.util.MultiValueMap;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<ListItemDetail> getListItem();
    Mono<ItemDetail> getItem(String uuid);
}
