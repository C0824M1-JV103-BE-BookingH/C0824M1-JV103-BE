use case_study;
TRUNCATE TABLE payments;
TRUNCATE TABLE bookings;
-- 1. Chèn dữ liệu vào bảng hotels
INSERT INTO hotels (owner, name, address, description, hotline, image_url) VALUES
('TNHDT', 'TNHDT Hotel', '123 Beach Road, Da Nang, Vietnam', 'A luxurious hotel by the beach with stunning views.', 84912345678, 'https://example.com/images/sunset_hotel.jpg');

-- 2. Chèn dữ liệu vào bảng room_types
INSERT INTO room_types (name, description, price, max_people, image_url) VALUES
('Standard Room', 'Cozy room with a single bed.', 50.0, 2, 'https://media.danitel.vn/uploads/2023/07/23c629b5-6f59-40b5-8654-adf775c5b67d/standard-02.jpg?width=1900&height=1267'),
('Deluxe Room', 'Spacious room with ocean view.', 100.0, 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQzGTijzYYXZFz9M6K4dh4p5flFe4tM1f8vQ&s'),
('Suite', 'Luxury suite with full amenities.', 200.0, 4, 'https://ezcloud.vn/wp-content/uploads/2018/05/phong-suite-la-gi.webp');

-- 3. Chèn dữ liệu vào bảng rooms
INSERT INTO rooms (room_type_id, room_number, status, image_url) VALUES
(1, '101', 'available', 'https://example.com/images/room101.jpg'),
(1, '102', 'available', 'https://example.com/images/room102.jpg'),
(2, '201', 'available', 'https://example.com/images/room201.jpg'),
(2, '202', 'booked', 'https://example.com/images/room202.jpg'),
(3, '301', 'available', 'https://example.com/images/room301.jpg');

-- 4. Chèn dữ liệu vào bảng users
INSERT INTO users (name, email, password, phone, role, status) VALUES
('Admin User', 'admin@gmail.com', 'admin', '84987654321', 'admin', 'active'),
('Staff User', 'staff@sunset.com', 'staff', '84987654322', 'staff', 'active'),
('John Doe', 'john.doe@gmail.com', '123', '84912345679', 'customer', 'active'),
('Jane Smith', 'jane.smith@gmail.com', '123', '84912345680', 'customer', 'active');

-- 5. Chèn dữ liệu vào bảng discount_codes


-- 6. Chèn dữ liệu vào bảng bookings
INSERT INTO bookings (user_id, room_id, check_in, check_out, status, payment_status, created_at) VALUES
(3, 1, '2025-04-27 14:00:00', '2025-04-30 12:00:00', 'confirmed', 'paid', '2025-04-25 10:00:00'),
(3, 3, '2025-05-01 14:00:00', '2025-05-03 12:00:00', 'pending', 'unpaid', '2025-04-26 09:00:00'),
(4, 1, '2025-04-28 14:00:00', '2025-05-01 12:00:00', 'cancelled', 'refunded', '2025-04-24 15:00:00'),
(4, 2, '2025-05-05 14:00:00', '2025-05-07 12:00:00', 'completed', 'paid', '2025-04-20 12:00:00');

-- 7. Chèn dữ liệu vào bảng payments
INSERT INTO payments (booking_id, method, amount, surcharge, paid_at) VALUES
(1, 'bank', 150.0, 5.0, '2025-04-25 10:30:00'),
(2, 'cash', 100.0, 0.0, '2025-04-20 12:30:00'),
(3, 'bank', 200.0, 10.0, '2025-04-24 15:30:00');

-- 8. Chèn dữ liệu vào bảng reviews
INSERT INTO reviews (user_id, hotel_id, rating, comment, created_at) VALUES
(3, 1, 5, 'Amazing stay, great view!', '2025-04-26 08:00:00'),
(4, 1, 4, 'Good service, but room could be cleaner.', '2025-04-25 09:00:00'),
(3, 1, 3, 'Average experience, expected more.', '2025-04-24 10:00:00');

-- 9. Chèn dữ liệu vào bảng support_requests
INSERT INTO support_requests (user_id, subject, message, created_at) VALUES
(3, 'Booking Issue', 'I need to change my booking dates.', '2025-04-26 07:00:00'),
(4, 'Refund Request', 'Please process my refund for cancelled booking.', '2025-04-25 08:00:00'),
(3, 'Room Complaint', 'The AC in my room is not working properly.', '2025-04-24 09:00:00');

			
SELECT * FROM users;
DELETE FROM bookings WHERE id > 4;

ALTER TABLE bookings AUTO_INCREMENT = 5;

