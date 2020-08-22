
package acme.features.anonymous.toledoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.ToledoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToledoBulletinRepository extends AbstractRepository {

	@Query("select a from ToledoBulletin a")
	Collection<ToledoBulletin> findMany();
}
