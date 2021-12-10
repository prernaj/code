package spring.projects.distributed_id_generator.service;

import org.springframework.stereotype.Service;

import spring.projects.distributed_id_generator.exception.ClockMovedBackException;
import spring.projects.distributed_id_generator.exception.NodeIdOutOfBoundException;

@Service
public interface SequenceIdGenerator {
    long generateId() throws ClockMovedBackException, NodeIdOutOfBoundException;
}
