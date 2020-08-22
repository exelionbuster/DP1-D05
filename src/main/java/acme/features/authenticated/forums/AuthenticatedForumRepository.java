
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where ?1 member of f.involvedUsers")
	Collection<Forum> findInvolvedForums(Authenticated user);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findUser(int id);

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);
}
