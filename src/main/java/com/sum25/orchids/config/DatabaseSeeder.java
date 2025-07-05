package com.sum25.orchids.config;

import com.sum25.orchids.entity.*;
import com.sum25.orchids.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired private RoleRepository roleRepo;
    @Autowired private AccountRepository accountRepo;
    @Autowired private CategoryrRepository categoryRepo;
    @Autowired private OrchidRepository orchidRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderDetailRepository orderDetailRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = Role.builder().roleName("Admin").build();
        Role userRole = Role.builder().roleName("User").build();
        roleRepo.saveAll(List.of(adminRole, userRole));

        // 2. Tạo Accounts
        Accounts admin = Accounts.builder()
                .accountName("Thoại Admin")
                .email("admin@example.com")
                .password(passwordEncoder.encode("123456")) // nên mã hoá thật khi deploy
                .role(adminRole)
                .build();

        Accounts user = Accounts.builder()
                .accountName("Nguyễn User")
                .email("user@example.com")
                .password(passwordEncoder.encode("123456"))
                .role(userRole)
                .build();

        accountRepo.saveAll(List.of(admin, user));

        // 3. Tạo Category
        Categories cat1 = Categories.builder().categoryName("Lan rừng").build();
        Categories cat2 = Categories.builder().categoryName("Lan cấy mô").build();
        categoryRepo.saveAll(List.of(cat1, cat2));

        // 4. Tạo Orchid
        Orchids orchid1 = Orchids.builder()
                .orchidName("Phi điệp tím")
                .orchidDescription("Loại lan quý từ Tây Bắc")
                .orchidUrl("http://localhost:8080/images/orchid1.jpg")
                .price(150000)
                .isNatural(true)
                .category(cat1)
                .build();

        Orchids orchid2 = Orchids.builder()
                .orchidName("Lan hồ điệp")
                .orchidDescription("Lan đẹp cho ngày Tết")
                .orchidUrl("http://localhost:8080/images/orchid2.jpg")
                .price(200000)
                .isNatural(false)
                .category(cat2)
                .build();

        orchidRepo.saveAll(List.of(orchid1, orchid2));

        // 5. Tạo Order
        Orders order = Orders.builder()
                .account(user)
                .orderDate(LocalDate.now())
                .status("Pending")
                .totalAmount(orchid1.getPrice() * 2)
                .build();

        orderRepo.save(order);

        // 6. Tạo OrderDetail
        OrderDetails detail = OrderDetails.builder()
                .order(order)
                .orchid(orchid1)
                .quantity(2)
                .price(orchid1.getPrice())
                .build();

        orderDetailRepo.save(detail);

        System.out.println("✅ Seeded dữ liệu mẫu hoàn tất.");
    }
}
