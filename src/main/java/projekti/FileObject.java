
package projekti;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileObject extends AbstractPersistable <Long> {

    @Lob 
    @Basic(fetch = FetchType.LAZY)
    @Type(type="org.hibernate.type.ImageType")
    private byte[] content;
    @ManyToOne
    private Account account;
    private String description;   
    private int likes =0;
}
