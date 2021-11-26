package apap.ta.service;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    private final WebClient webClient;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
    }

    @Override
    public Mono<ItemDetail> getListItem() {
        return this.webClient.get()
                .retrieve()
                .bodyToMono(ItemDetail.class);
    }
}
