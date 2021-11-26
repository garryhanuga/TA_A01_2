package apap.ta.service;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    @Override
//    public Map<String,String>  listItem(){
//        String response = this.webClient.get().uri("")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//        System.out.println("MASUK LIST ITEM DI ITEM RESTSERVICEIMPL");
//        System.out.println("Isi Response");
//        System.out.println(response);
//        response = response.substring(1, response.length()-1);    //remove curly brackets
//        String[] keyValuePairs = response.split(",");      //split the string to create key-value pairs
//        Map<String,String> map = new HashMap<>();
//
//        //iterate over the pairs
//        for (String pair : keyValuePairs) {
//            //split the pairs to get key and value
//            String[] entry = pair.split(":");
//
//            //add them to the hashmap and trim whitespaces
//            map.put(entry[0].trim().replace("\"", ""),
//                    entry[1].trim().replace("\"", ""));
//        }
////        System.out.println(map);
//        return null;
//
//    }
//
//    @Override
//    public List<Object> getListResultItem() {
//        return null;
//    }
}
