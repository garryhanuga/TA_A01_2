package apap.ta.service;
import java.util.List;

import apap.ta.model.ItemModel;
import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import reactor.core.publisher.Mono;
public interface ProposeItemRestService {
    ItemDetail proposeItem(String nama, int harga, int stok, Long kategori);
}





    
