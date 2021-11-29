package apap.ta.restcontroller;

import apap.ta.model.RequestUpdateItemModel;
import apap.ta.rest.RequestUpdateItemDetail;
import apap.ta.service.RequestUpdateItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2")
public class RequestUpdateItemRestController {
    @Autowired
    private RequestUpdateItemRestService requestUpdateItemRestService;

    @PostMapping(value="/requestupdateitem",consumes = "application/json",produces = "application/json")
    private RequestUpdateItemModel createRequestUpdateItem(
            @Valid @RequestBody RequestUpdateItemDetail requestUpdateItem,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            return requestUpdateItemRestService.createRequestUpdateItem(requestUpdateItem);
        }
    }

}