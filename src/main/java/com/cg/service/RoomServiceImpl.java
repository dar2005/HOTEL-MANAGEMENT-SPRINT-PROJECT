package com.cg.service; 
import com.cg.entity.Room; 
import com.cg.entity.RoomType;
import com.cg.exception.BadRequestException;
import com.cg.exception.ConflictException;
import com.cg.exception.ResourceNotFoundException; 
import com.cg.repo.RoomRepository; 
import com.cg.repo.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomTypeRepository typeRepo;

    // CREATE
    @Override
    public Room createRoom(Room room, Long typeId) {

        if (room.getRoomNumber() <= 0) {
            throw new BadRequestException("Room number must be positive");
        }

        if (roomRepo.existsById(room.getRoomId())) {
            throw new ConflictException("Room ID already exists: " + room.getRoomId());
        }

        RoomType type = typeRepo.findById(typeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found with id: " + typeId));

        room.setRoomType(type);
        return roomRepo.save(room);
    }

    // GET ALL
    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    // GET BY ID (NEW)
    @Override
    public Room getById(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + id));
    }

    // AVAILABLE
    @Override
    public List<Room> getAvailableRooms() {
        return roomRepo.findByIsAvailableTrue();
    }

    // UNAVAILABLE
    @Override
    public List<Room> getUnavailableRooms() {
        return roomRepo.findByIsAvailableFalse();
    }

    // BY TYPE
    @Override
    public List<Room> getRoomsByType(String typeName) {
        return roomRepo.findByRoomType_TypeName(typeName);
    }

    // BY PRICE
    @Override
    public List<Room> getRoomsByPrice(double min, double max) {

        if (min > max) {
            throw new BadRequestException("Min price cannot be greater than max price");
        }

        return roomRepo.findByRoomType_PricePerNightBetween(min, max);
    }

    // BY ROOM NUMBER
    @Override
    public Room getByRoomNumber(int roomNumber) {

        return roomRepo.findByRoomNumber(roomNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with number: " + roomNumber));
    }
    // UPDATE AVAILABILITY (NEW)
    @Override
    public Room updateAvailability(Long id, boolean status) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + id));

        room.setIsAvailable(status);   // ✅ correct setter
        return roomRepo.save(room);
    }
    
    

    // DELETE
    @Override
    public void deleteRoom(Long id) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + id));

        roomRepo.delete(room);
    }
}