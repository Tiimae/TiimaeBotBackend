package tiimae.tiimaebot.backendtiimaebot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "settings")
@Getter
@Setter
public class Settings {
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
    private Guild guild;

    @NotNull
    private String key;
    @NotNull
    private String value;

    public Settings() { }

    public Settings(Guild guild, String key, String value) {
        this.guild = guild;
        this.key = key;
        this.value = value;
    }
}
