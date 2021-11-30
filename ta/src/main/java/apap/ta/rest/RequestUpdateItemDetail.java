package apap.ta.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data

//Data yang dibutuhkan
public class RequestUpdateItemDetail {
    private String idItem;
    private Long idKategori;
    private int tambahanStok;
    private Long idCabang;
}
