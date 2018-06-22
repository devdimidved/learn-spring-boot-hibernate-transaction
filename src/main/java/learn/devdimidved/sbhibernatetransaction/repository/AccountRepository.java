package learn.devdimidved.sbhibernatetransaction.repository;

import learn.devdimidved.sbhibernatetransaction.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
