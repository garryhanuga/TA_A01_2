package apap.ta.service;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.rest.RequestUpdateItemDetail;

import java.util.List;

public interface RequestUpdateItemRestService {
    RequestUpdateItemModel createRequestUpdateItem(RequestUpdateItemDetail requestUpdateItem);
    List<RequestUpdateItemModel> retrieveListRequestUpdateItem();
    RequestUpdateItemModel getRequestItemModelByIdRequestItemModel(Long id);

}
