package tiimae.tiimaebot.backendtiimaebot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "money")
@Getter
@Setter
public class Money {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private UserGuild userGuild;

    @NotNull
    @Value("0")
    private long totalMoney;

    @NotNull
    @Column(nullable = false, updatable = true)
    @CreationTimestamp
    private Timestamp moneylock;

    @NotNull
    @Column(nullable = false, updatable = true)
    @CreationTimestamp
    private Timestamp roblock;

    public Money() { }

    public Money(UserGuild userGuild, long totalMoney, Timestamp moneylock, Timestamp roblock) {
        this.userGuild = userGuild;
        this.totalMoney = totalMoney;
        this.moneylock = moneylock;
        this.roblock = roblock;
    }
}
