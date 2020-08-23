
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where ?1 member of f.involvedUsers or f.owner = ?1")
	Collection<Forum> findInvolvedForums(Authenticated user);

	@Query("select count(f)>0 from Forum f where f = :forum and :user member of f.involvedUsers or f.owner = :user")
	Boolean isInvolved(@Param("forum") Forum forum, @Param("user") Authenticated user);

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findUser(int id);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findUserByUsername(String username);

}
