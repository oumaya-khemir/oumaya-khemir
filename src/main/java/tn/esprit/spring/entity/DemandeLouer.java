package tn.esprit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemandeLouer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private long user;
    @OneToOne
	private Advertisement annonce;
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DemandeLouer that = (DemandeLouer) o;
		return user == that.user && Objects.equals(annonce, that.annonce);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, annonce);
	}
}
