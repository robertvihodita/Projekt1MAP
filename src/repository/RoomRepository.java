package repository;

import org.springframework.stereotype.Repository;

import model.Room;

@Repository
public class RoomRepository extends InMemoryRepository<Room> {}