package com.example.casestudy_g2_m4.service.booking;

import com.example.casestudy_g2_m4.model.*;
import com.example.casestudy_g2_m4.repository.IBookingRepository;
import com.example.casestudy_g2_m4.service.room.IRoomService;
import com.example.casestudy_g2_m4.service.roomtype.IRoomTypeService;
import com.example.casestudy_g2_m4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookingService implements IBookingService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoomTypeService roomTypeService;
    @Autowired
    private IRoomService roomService;

    @Override
    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void addBooking(BookingDTO bookingDTO) {
        // Kiểm tra và xử lý User
        User user = null;
        if (bookingDTO.getUserName() != null && !bookingDTO.getUserName().trim().isEmpty()) {
            User newUser = new User();
            newUser.setName(bookingDTO.getUserName().trim());
            newUser.setRole(User.Role.customer);
            newUser.setStatus(User.Status.active);
            user = userService.saveUser(newUser);
            if (user.getId() == null) {
                throw new RuntimeException("Không thể lưu người dùng mới vào cơ sở dữ liệu");
            }
        } else {
            throw new RuntimeException("Vui lòng chọn hoặc nhập tên người dùng");
        }

        // Lấy tên loại phòng từ BookingDTO
        String enteredRoomTypeName = bookingDTO.getRoomType();
        if (enteredRoomTypeName == null || enteredRoomTypeName.trim().isEmpty()) {
            throw new RuntimeException("Vui lòng nhập loại phòng");
        }

        // Chuẩn hóa tên loại phòng
        enteredRoomTypeName = Stream.of(enteredRoomTypeName.trim().toLowerCase().split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));

        // Kiểm tra RoomType
        String finalEnteredRoomTypeName = enteredRoomTypeName;
        RoomType roomType = roomTypeService.findByName(enteredRoomTypeName)
                .orElseGet(() -> {
                    RoomType newRoomType = new RoomType();
                    newRoomType.setName(finalEnteredRoomTypeName);
                    newRoomType.setPrice(0.0);
                    newRoomType.setMaxPeople(1);
                    newRoomType.setDescription("User-entered RoomType");
                    return roomTypeService.save(newRoomType);
                });

        // Tạo Room
        Room room = new Room();
        room.setRoomType(roomType);
        int randomFloor = new Random().nextInt(5) + 1;
        String randomRoomNumber = Room.generateRoomNumber(randomFloor);
        room.setRoomNumber(randomRoomNumber);
        room.setStatus(Room.Status.available);
        room = roomService.save(room);

        // Tạo Booking và ánh xạ dữ liệu
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckIn(bookingDTO.getCheckIn());
        booking.setCheckOut(bookingDTO.getCheckOut());
        booking.setStatus(Booking.Status.pending);
        booking.setPaymentStatus(Booking.PaymentStatus.unpaid);
        booking.setCreatedAt(LocalDateTime.now());

        // Lưu Booking
        addBooking(booking);
    }

    @Override
    public Optional<Booking> findBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {
        Booking exitingBoooking = bookingRepository.findById(bookingDTO.getId()).orElseThrow(() -> new RuntimeException("Booking not found"));
        exitingBoooking.setCheckIn(bookingDTO.getCheckIn());
        exitingBoooking.setCheckOut(bookingDTO.getCheckOut());
        exitingBoooking.setStatus(Booking.Status.valueOf(bookingDTO.getStatus()));
        exitingBoooking.setPaymentStatus(Booking.PaymentStatus.valueOf(bookingDTO.getPaymentStatus()));
        if (bookingDTO.getUserName() != null && !bookingDTO.getUserName().trim().isEmpty()) {
            User user = exitingBoooking.getUser();
            if (user == null) {
                user = new User();
                exitingBoooking.setUser(user);
            }
            user.setName(bookingDTO.getUserName().trim());
            userService.saveUser(user);
        }
        if (bookingDTO.getRoomType() != null && !bookingDTO.getRoomType().trim().isEmpty()) {
            Room room = exitingBoooking.getRoom();
            if (room != null) {
                String enteredRoomTypeName = bookingDTO.getRoomType();
                enteredRoomTypeName = Stream.of(enteredRoomTypeName.trim().toLowerCase().split("\\s+"))
                        .filter(word -> !word.isEmpty())
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                        .collect(Collectors.joining(" "));
                String finalEnteredRoomTypeName = enteredRoomTypeName;
                RoomType roomType = roomTypeService.findByName(enteredRoomTypeName)
                        .orElseGet(() -> {
                            RoomType newRoomType = new RoomType();
                            newRoomType.setName(finalEnteredRoomTypeName);
                            newRoomType.setPrice(0.0);
                            newRoomType.setMaxPeople(1);
                            newRoomType.setDescription("User-entered RoomType");
                            return roomTypeService.save(newRoomType);
                        });
                room.setRoomType(roomType);
                roomService.save(room);
            }


        }
    }
}

