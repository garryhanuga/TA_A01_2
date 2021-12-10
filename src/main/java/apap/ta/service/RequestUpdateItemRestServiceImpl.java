package apap.ta.service;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.repository.RequestUpdateItemDb;
import apap.ta.rest.RequestUpdateItemDetail;
import apap.ta.rest.Setting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import java.sql.Date;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestUpdateItemRestServiceImpl implements RequestUpdateItemRestService{
    private final WebClient webClient;

    @Autowired
    private RequestUpdateItemDb requestUpdateItemDb;

    @Override
    public RequestUpdateItemModel getRequestById(Long id) {
        return requestUpdateItemDb.getById(id);
    }

    @Override
    public RequestUpdateItemModel createRequestUpdateItem(RequestUpdateItemDetail requestUpdateItem) {
        RequestUpdateItemModel rui = new RequestUpdateItemModel();
        rui.setIdItem(requestUpdateItem.getIdItem()); //id_item
        rui.setIdKategori(requestUpdateItem.getIdKategori()); //id_kategori
        rui.setTambahanStok(requestUpdateItem.getTambahanStok()); //tambahan_stok
        java.util.Date date = new java.util.Date();
        rui.setTanggalRequest(new Date(date.getTime())); //tanggal_request
        rui.setIdCabang(requestUpdateItem.getIdCabang()); //id_Cabang
        rui.setExecuted(false); //executed
        return requestUpdateItemDb.save(rui);
    }

    @Override
    public List<RequestUpdateItemModel> retrieveListRequestUpdateItem() {
        return requestUpdateItemDb.findAll();
    }

    @Override
    public RequestUpdateItemModel getRequestItemModelByIdRequestItemModel(Long id) {
        Optional<RequestUpdateItemModel> request = requestUpdateItemDb.findByidRequestUpdateItem(id);
        if(request.isPresent()){
            return request.get();
        }
        return null;
    }

    @Override
    public RequestUpdateItemModel updateRequestUpdateItem(RequestUpdateItemModel requestUpdateItem) {
        return requestUpdateItemDb.save(requestUpdateItem);
    }

    public RequestUpdateItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.requestUpdateItemUrl).build();
    }

    @Override
    public Mono<RequestUpdateItemDetail> updateCabangStok(RequestUpdateItemDetail detailItem) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("idItem", detailItem.getIdItem());
        data.add("idKategori,", detailItem.getIdKategori());
        data.add("tambahanStok", detailItem.getTambahanStok());
        data.add("idCabang,", detailItem.getIdCabang());

        return this.webClient.post()
                .syncBody(data)
                .retrieve()
                .bodyToMono(RequestUpdateItemDetail.class);

    }

}
