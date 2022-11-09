package tiimae.tiimaebot.backendtiimaebot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "xp")
@Getter
@Setter
public class Xp {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference

    private UserGuild userGuild;

    @NotNull
    @Value("0")
    private long exp;
    @NotNull
    @Value("0")
    private long level;

    @NotNull
    @Column(nullable = false, updatable = true)
    @CreationTimestamp
    private Timestamp xplock;

    public Xp() { }

    public Xp(UserGuild userGuild, long exp, long level, Timestamp xplock) {
        this.userGuild = userGuild;
        this.exp = exp;
        this.level = level;
        this.xplock = xplock;
    }
}
