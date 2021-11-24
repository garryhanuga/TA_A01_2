package apap.ta.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="request_update_item")

public class RequestUpdateItemModel implements Serializable {
    @Id
    @Column(name="id_request_update_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequestUpdateItem;

    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idItem;

    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Long idKategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private int tambahanStok;

    @NotNull
    @Column(name="tanggal_request", nullable = false)
    private Date tanggalRequest;

    @NotNull
    @Column(name="id_cabang", nullable = false)
    private Long idCabang;

    @NotNull
    @Column(name="executed", nullable = false, columnDefinition = "boolean default false")
    private Boolean executed;

    // Relasi dengan delivery
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_delivery", referencedColumnName="id_delivery")
    private DeliveryModel delivery;

    //relasi dengan produksi
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_produksi", referencedColumnName="id_produksi")
    private ProduksiModel produksi;

}
