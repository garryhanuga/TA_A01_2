package apap.ta.service;

import java.util.List;

import apap.ta.model.ItemModel;
import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<ListItemDetail> getListItem();
    ItemDetail getItem(String uuid);
    ItemDetail updateItem(ItemDetail item);
}
