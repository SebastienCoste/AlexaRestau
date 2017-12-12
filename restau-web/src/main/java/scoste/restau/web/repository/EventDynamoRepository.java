package scoste.restau.web.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import scoste.restau.web.repository.dto.EventDto;

@EnableScan
public interface EventDynamoRepository extends CrudRepository<EventDto, String> {

//    Optional<EventDto> findById(String id);
}
