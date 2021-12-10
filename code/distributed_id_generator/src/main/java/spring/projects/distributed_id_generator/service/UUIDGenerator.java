package spring.projects.distributed_id_generator.service;


import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
