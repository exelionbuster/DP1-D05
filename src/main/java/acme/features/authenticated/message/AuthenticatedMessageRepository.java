
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findUser(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findForumMessages(int id);

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m.forum from Message m where m.forum.id = ?1")
	Forum findMessageForum(int id);
}
