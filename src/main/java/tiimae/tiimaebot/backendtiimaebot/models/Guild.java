package tiimae.tiimaebot.backendtiimaebot.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "guild")
@Getter
@Setter
public class Guild {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private long guildId;

    @OneToMany(mappedBy = "guild", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserGuild> userGuildSet = new HashSet<>();

    @OneToOne(mappedBy = "guild", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Settings settings;

    public Guild() { }

    public Guild(String name, long guildId, Set<UserGuild> userGuildSet, Settings settings) {
        this.name = name;
        this.guildId = guildId;
        this.userGuildSet = userGuildSet;
        this.settings = settings;
    }
}
