package spring.projects.distributed_id_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.projects.distributed_id_generator.exception.ClockMovedBackException;
import spring.projects.distributed_id_generator.exception.NodeIdOutOfBoundException;
import spring.projects.distributed_id_generator.service.SequenceIdGenerator;

@RestController()
public class IdGeneratorController {
    @Autowired
    private SequenceIdGenerator sequenceIdGenerator;

    @GetMapping(produces = {"application/JSON"})
    public ResponseEntity<?> getNextId() throws NodeIdOutOfBoundException, ClockMovedBackException {
        return ResponseEntity.ok(sequenceIdGenerator.generateId());
    }
}
