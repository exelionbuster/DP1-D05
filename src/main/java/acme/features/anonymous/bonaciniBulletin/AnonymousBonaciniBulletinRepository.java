
package acme.features.anonymous.bonaciniBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.BonaciniBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBonaciniBulletinRepository extends AbstractRepository {

	@Query("select b from BonaciniBulletin b")
	Collection<BonaciniBulletin> findMany();
}
