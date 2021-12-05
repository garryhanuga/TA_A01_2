package apap.ta.service;

import java.util.List;

import apap.ta.model.ItemModel;
import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<ListDetail> getListItem();
    ItemDetail getItem(String uuid);
    ItemDetail updateItem(ItemDetail item);
}
