package apap.ta.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
@Setter
public class DeliveryDetail {
    @JsonProperty("idCabang")
    private Long idCabang;

    @JsonProperty("namaCabang")
    private String namaCabang;

    @JsonProperty("alamatCabang")
    private String alamatCabang;
}
