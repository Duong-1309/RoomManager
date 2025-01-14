-- Thêm users
INSERT INTO users (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN'),
       ('vanduong', '123456', 'ADMIN'),
       ('user1', 'user123', 'USER');

-- Thêm rooms
INSERT INTO rooms (room_number, status, floor, area, rent_price, description)
VALUES ('1A', 'OCCUPIED', 1, 25.5, 2500000, 'Phòng góc, có ban công'),
       ('1B', 'OCCUPIED', 1, 20.0, 2000000, 'Phòng tiêu chuẩn'),
       ('2A', 'VACANT', 2, 25.5, 2500000, 'Phòng góc, có ban công'),
       ('2B', 'OCCUPIED', 2, 20.0, 2000000, 'Phòng tiêu chuẩn'),
       ('2C', 'OCCUPIED', 2, 22.0, 2200000, 'Phòng tiêu chuẩn'),
       ('3A', 'OCCUPIED', 3, 25.5, 2500000, 'Phòng góc, có ban công'),
       ('3B', 'VACANT', 3, 20.0, 2000000, 'Phòng tiêu chuẩn'),
       ('4A', 'OCCUPIED', 4, 25.5, 2500000, 'Phòng góc, có ban công'),
       ('4B', 'OCCUPIED', 4, 20.0, 2000000, 'Phòng tiêu chuẩn'),
       ('5A', 'VACANT', 5, 25.5, 2500000, 'Phòng góc, có ban công');

-- Thêm tenants
INSERT INTO tenants (room_id, full_name, phone, email, id_number, address, start_date, status)
VALUES (1, 'Nguyễn Văn A', '0901234567', 'nguyenvana@email.com', '001201012345', 'Hà Nội', '2024-01-01', 'ACTIVE'),
       (2, 'Trần Thị B', '0901234568', 'tranthib@email.com', '001201012346', 'Hải Phòng', '2024-01-01', 'ACTIVE'),
       (4, 'Lê Văn C', '0901234569', 'levanc@email.com', '001201012347', 'Nam Định', '2024-01-01', 'ACTIVE'),
       (5, 'Phạm Thị D', '0901234570', 'phamthid@email.com', '001201012348', 'Thái Bình', '2024-01-01', 'ACTIVE'),
       (6, 'Hoàng Văn E', '0901234571', 'hoangvane@email.com', '001201012349', 'Hà Nam', '2024-01-01', 'ACTIVE'),
       (8, 'Đỗ Thị F', '0901234572', 'dothif@email.com', '001201012350', 'Ninh Bình', '2024-01-01', 'ACTIVE'),
       (9, 'Vũ Văn G', '0901234573', 'vuvang@email.com', '001201012351', 'Hà Nội', '2024-01-01', 'ACTIVE');

-- Thêm bills (hoá đơn tháng 1/2024)
INSERT INTO bills (room_id, month, year, rent_amount, electric_old_index, electric_new_index, electric_price,
                   electric_amount,
                   water_old_index, water_new_index, water_price, water_amount, garbage_fee, wifi_fee, parking_fee,
                   total_amount, status)
VALUES (1, 1, 2024, 2500000, 100, 200, 3500, 350000, 10, 15, 15000, 75000, 50000, 50000, 200000, 3225000, 'PAID'),
       (2, 1, 2024, 2000000, 150, 250, 3500, 350000, 15, 20, 15000, 75000, 50000, 50000, 200000, 2725000, 'PAID'),
       (4, 1, 2024, 2000000, 200, 300, 3500, 350000, 20, 25, 15000, 75000, 50000, 50000, 200000, 2725000, 'PENDING'),
       (5, 1, 2024, 2200000, 120, 220, 3500, 350000, 12, 17, 15000, 75000, 50000, 50000, 200000, 2925000, 'PENDING'),
       (6, 1, 2024, 2500000, 180, 280, 3500, 350000, 18, 23, 15000, 75000, 50000, 50000, 200000, 3225000, 'PENDING'),
       (8, 1, 2024, 2500000, 90, 190, 3500, 350000, 8, 13, 15000, 75000, 50000, 50000, 200000, 3225000, 'PAID'),
       (9, 1, 2024, 2000000, 130, 230, 3500, 350000, 14, 19, 15000, 75000, 50000, 50000, 200000, 2725000, 'PENDING');

-- Thêm notifications
INSERT INTO notifications (room_id, title, content, type, is_read)
VALUES (1, 'Thông báo đóng tiền phòng', 'Đến hạn đóng tiền phòng tháng 2/2024', 'BILL', 0),
       (2, 'Thông báo đóng tiền phòng', 'Đến hạn đóng tiền phòng tháng 2/2024', 'BILL', 0),
       (4, 'Thông báo đóng tiền phòng', 'Đến hạn đóng tiền phòng tháng 2/2024', 'BILL', 0),
       (5, 'Thông báo bảo trì', 'Bảo trì hệ thống điện ngày 15/02/2024', 'MAINTENANCE', 0),
       (6, 'Thông báo đóng tiền phòng', 'Đến hạn đóng tiền phòng tháng 2/2024', 'BILL', 0),
       (null, 'Thông báo chung', 'Tổng vệ sinh khu vực chung ngày 20/02/2024', 'OTHER', 0);

-- Thêm utility_history (lịch sử chỉ số điện nước tháng 1/2024)
INSERT INTO utility_history (room_id, month, year, electric_index, water_index, record_date)
VALUES (1, 1, 2024, 200, 15, '2024-01-31'),
       (2, 1, 2024, 250, 20, '2024-01-31'),
       (4, 1, 2024, 300, 25, '2024-01-31'),
       (5, 1, 2024, 220, 17, '2024-01-31'),
       (6, 1, 2024, 280, 23, '2024-01-31'),
       (8, 1, 2024, 190, 13, '2024-01-31'),
       (9, 1, 2024, 230, 19, '2024-01-31');

-- Thêm settings (đã có từ script trước)
INSERT INTO settings (key, value, description)
VALUES ('ELECTRIC_PRICE', '3500', 'Giá điện mỗi số (VND)'),
       ('WATER_PRICE', '15000', 'Giá nước mỗi khối (VND)'),
       ('GARBAGE_FEE', '50000', 'Phí rác cố định hàng tháng (VND)'),
       ('WIFI_FEE', '50000', 'Phí wifi cố định hàng tháng (VND)'),
       ('PARKING_FEE', '200000', 'Phí gửi xe cố định hàng tháng (VND)');