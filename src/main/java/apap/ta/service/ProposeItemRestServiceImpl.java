package apap.ta.service;

import apap.ta.rest.ItemDetail;
import apap.ta.rest.ListItemDetail;
import apap.ta.rest.Setting;
import apap.ta.model.ItemModel;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ProposeItemRestServiceImpl implements ProposeItemRestService{
    private final WebClient webClient;

    public ProposeItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.proposeItemUrl).build();
    }

    @Override
    public void proposeItem(String nama, int harga, int stok, Long kategori, String cluster) {
        System.out.println("masukk rest serviceimpl");
        String url = "https://tk-apap-a01-1.herokuapp.com/api/item-factory";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //request body parameter
        Map<String, Object> data = new HashMap<>();
        data.put("name", nama);
        data.put("price", harga);
        data.put("stock", stok);
        data.put("category", kategori);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(data, headers);

        // send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

    }

}