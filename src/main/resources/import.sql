
INSERT INTO order_main VALUES (2147483643, '3100 Western Road A', 'customer2@email.com', 'customer2', '2343456', '2018-03-15 12:52:20.439', 100.00, 0, '2018-03-15 12:52:20.439');
INSERT INTO order_main VALUES (2147483645, '3100 Western Road A', 'customer2@email.com', 'customer2', '2343456', '2018-03-15 12:52:29.007', 4.00, 0, '2018-03-15 12:52:29.007');
INSERT INTO order_main VALUES (2147483641, '3100 Western Road A', 'customer2@email.com', 'customer2', '2343456', '2018-03-15 12:52:07.428', 180.00, 2, '2018-03-15 12:52:53.664');
INSERT INTO order_main VALUES (2147483647, '3100 Western Road A', 'customer2@email.com', 'customer2', '2343456', '2018-03-15 12:52:35.289', 2.00, 2, '2018-03-15 12:52:55.919');
INSERT INTO order_main VALUES (2147483649, '3100 Western Road A', 'customer2@email.com', 'customer2', '2343456', '2018-03-15 12:58:23.824', 150.00, 0, '2018-03-15 12:58:23.824');
INSERT INTO order_main VALUES (2147483642, '3200 West Road', 'customer1@email.com', 'customer1', '123456789', '2018-03-15 13:01:21.135', 4.00, 2, '2018-03-15 13:02:09.023');
INSERT INTO order_main VALUES (2147483640, '3200 West Road', 'customer1@email.com', 'customer1', '123456789', '2018-03-15 13:01:16.271', 20.00, 2, '2018-03-15 13:02:52.067');
INSERT INTO order_main VALUES (2147483648, '3200 West Road', 'customer1@email.com', 'customer1', '123456789', '2018-03-15 13:01:06.943', 134.00, 1, '2018-03-15 13:02:56.498');

-- ----------------------------
-- Table structure for product_category

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO manga_category VALUES (2147483641, 'Books', 0, '2018-03-09 23:03:26', '2018-03-10 00:15:27');
INSERT INTO manga_category VALUES (2147483642, 'Programming', 2, '2018-03-10 00:15:02', '2018-03-10 00:15:21');
INSERT INTO manga_category VALUES (2147483644, 'Anime', 3, '2018-03-10 01:01:09', '2018-03-10 01:01:09');
INSERT INTO manga_category VALUES (2147483645, 'Food', 1, '2018-03-10 00:26:05', '2018-03-10 00:26:05');


-- ----------------------------
-- Records of product_in_order
-- ----------------------------
INSERT INTO manga_in_order VALUES (2147483646, 1,1, 'Gordon Ramsey', 'https://bukva.ua/img/products/454/454878.jpg', 'F0001', 'Gordon', 4.00,57,NULL, 2147483645);
INSERT INTO manga_in_order VALUES (2147483648, 3,1,'Berserk', 'https://images-na.ssl-images-amazon.com/images/I/91oSUA0bSuL.jpg', 'D0002', 'Berserk', 2.00,200,NULL, 2147483647);
INSERT INTO manga_in_order VALUES (2147483643, 0,1, 'Books for learning Java', 'https://images-na.ssl-images-amazon.com/images/I/41f6Rd6ZEPL._SX363_BO1,204,203,200_.jpg', 'B0001', 'Core Java', 30.00,96,NULL, 2147483648);
INSERT INTO manga_in_order VALUES (2147483634, 2,1, 'c++ for Beginners', 'https://img.yakaboo.ua/media/catalog/product/cache/1/image/546x/00c1a1eab9920e00d38dc8798e6142c9/i/m/img644_3_91.jpg', 'C0001', 'c++', 10.00, 109,NULL, 2147483649);


-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO manga_info VALUES ('D0002', 3, '2018-03-10 12:08:17', 'Berserk', 'https://images-na.ssl-images-amazon.com/images/I/91oSUA0bSuL.jpg', 'Berserk', 2.00, 0, 200, '2018-03-10 12:08:17');
INSERT INTO manga_info VALUES ('C0001', 2, '2018-03-10 12:09:41', 'c++ for Beginners', 'https://img.yakaboo.ua/media/catalog/product/cache/1/image/546x/00c1a1eab9920e00d38dc8798e6142c9/i/m/img644_3_91.jpg', 'c++', 10.00, 0, 109, '2018-03-10 12:09:41');
INSERT INTO manga_info VALUES ('B0001', 0, '2018-03-10 06:44:25', 'Books for learning Java', 'https://images-na.ssl-images-amazon.com/images/I/41f6Rd6ZEPL._SX363_BO1,204,203,200_.jpg', 'Core Java', 30.00, 0, 96, '2018-03-10 06:44:25');
INSERT INTO manga_info VALUES ('F0001', 1, '2018-03-10 12:15:05', 'Gordon Ramsey', 'https://bukva.ua/img/products/454/454878.jpg', 'Gordon', 4.00, 0, 57, '2018-03-10 12:15:10');


-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES (2147483641, 't', '3200 West Road', 'customer1@email.com', 'customer1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '123456789', 'ROLE_CUSTOMER');
INSERT INTO users VALUES (2147483642, 't', '2000 John Road', 'manager1@email.com', 'manager1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '987654321', 'ROLE_MANAGER');
INSERT INTO users VALUES (2147483643, 't', '222 East Drive ', 'employee1@email.com', 'employee1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '123123122', 'ROLE_EMPLOYEE');
INSERT INTO users VALUES (2147483645, 't', '3100 Western Road A', 'customer2@email.com', 'customer2', '$2a$10$0oho5eUbDqKrLH026A2YXuCGnpq07xJpuG/Qu.PYb1VCvi2VMXWNi', '2343456', 'ROLE_CUSTOMER');

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO cart VALUES (2147483641);
INSERT INTO cart VALUES (2147483642);
INSERT INTO cart VALUES (2147483643);
INSERT INTO cart VALUES (2147483645);


