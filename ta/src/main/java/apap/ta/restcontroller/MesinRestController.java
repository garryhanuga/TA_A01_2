package apap.ta.restcontroller;

import apap.ta.model.MesinModel;
import apap.ta.service.MesinRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class MesinRestController {
    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(value = "/list-mesin")
    private List<MesinModel> retrieveListMesin() { return mesinRestService.retrieveListMesin(); }

}
