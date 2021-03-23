package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.models.Code;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, UUID> {

    Optional<Code> findById(UUID id);

    @Override
    void deleteById(UUID uuid);

    List<Code> findFirst10ByTimeEqualsAndViewsEqualsOrderByDateTimeDesc(long time, long views);

}
