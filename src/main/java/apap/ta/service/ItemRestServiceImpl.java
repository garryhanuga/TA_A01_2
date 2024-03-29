package apap.ta.service;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import apap.ta.rest.Setting;
import apap.ta.model.ItemModel;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    private final WebClient webClient;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
    }

    @Override
    public Mono<ListItemDetail> getListItem() {
        return this.webClient.get()
                .retrieve()
                .bodyToMono(ListItemDetail.class);
    }

    @Override
    public ItemDetail getItem(String uuid) {
        ItemDetail obj =  this.webClient.get().uri("/" + uuid)
                .retrieve()
                .bodyToMono(ItemDetail.class)
                .block();
        return obj;
    }

    @Override
    public ItemDetail updateItem(ItemDetail item) {
        ItemDetail obj = this.webClient.put().uri("/" + item.getUuid())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(item)
            .retrieve().bodyToMono(ItemDetail.class).block();
            return obj;
    }

    @Override
    public ItemDetail proposeItem(String nama, int harga, int stok, Long kategori) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("harga", harga);
        data.add("kategori", kategori);
        data.add("nama", nama);
        data.add("stok", stok);

        return this.webClient.post()
                .syncBody(data)
                .retrieve()
                .bodyToMono(ItemDetail.class).block();

    }

}