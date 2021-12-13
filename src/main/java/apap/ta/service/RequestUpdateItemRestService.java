package apap.ta.service;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.rest.RequestUpdateItemDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RequestUpdateItemRestService {
    RequestUpdateItemModel createRequestUpdateItem(RequestUpdateItemDetail requestUpdateItem);
    List<RequestUpdateItemModel> retrieveListRequestUpdateItem();
    RequestUpdateItemModel getRequestById(Long id);
    RequestUpdateItemModel getRequestItemModelByIdRequestItemModel(Long id);
    RequestUpdateItemModel updateRequestUpdateItem(RequestUpdateItemModel requestUpdateItem);
}
