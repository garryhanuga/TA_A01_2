package apap.ta.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="delivery")

public class DeliveryModel implements Serializable {
    @Id
    @Column(name="id_delivery")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    // Relasi dengan pegawai
    @ManyToOne(fetch = FetchType.EAGER, optional= false)
    @JoinColumn(name="id_kurir", referencedColumnName="id_pegawai", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

    @NotNull
    @Column(name="id_cabang", nullable = false)
    private Long idCabang;

    @NotNull
    @Column(name="tanggal_dibuat", nullable = false)
    private Date tanggalDibuat;

    @NotNull
    @Column(name="tanggal_dikirim", nullable = false)
    private Date tanggalDikirim;

    @NotNull
    @Column(name="sent", nullable = false)
    private Boolean sent;

    // Relasi dengan request update item
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_request_update_item", referencedColumnName="id_request_update_item")
    private RequestUpdateItemModel RequestUpdateItem;


}