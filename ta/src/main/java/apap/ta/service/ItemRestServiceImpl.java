package apap.ta.service;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import apap.ta.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

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
    public Mono<ItemDetail> getItem(String uuid) {
        return this.webClient.get().uri("/"+uuid)
        .retrieve()
        .bodyToMono(ItemDetail.class);

    }
}
