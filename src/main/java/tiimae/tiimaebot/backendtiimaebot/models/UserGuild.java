package tiimae.tiimaebot.backendtiimaebot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_guild")
@Getter
@Setter
public class UserGuild {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Guild guild;

    @OneToOne(mappedBy = "userGuild", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Xp xp;


    public UserGuild () { }

    public UserGuild(User user, Guild guild, Xp xp) {
        this.user = user;
        this.guild = guild;
        this.xp = xp;
    }
}
