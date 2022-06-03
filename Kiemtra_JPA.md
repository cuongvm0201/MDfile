# ***KIỂM TRA JPA***
## 1. Thuộc tính name trong annotation @Entity khác với thuộc tính name trong @Table như thế nào? Hãy giải thích rõ cần thì minh hoạ
@Entity(name = ‘EntityName’) dùng để chỉ tên của entity được Hibernate quản lý trong khi @Table(name = “TableName”) chỉ đích danh tên của table dưới database. Ví dụ khi chúng ta sử dụng HQL để truy vấn thì chúng ta cần chỉ định EntityName và Hibernate sẽ dựa vào đó để ánh xạ thành TableName tương ứng.

```
Hibernate: select m from EntityName m => SQL: select m from TableName m
```

---
## 2. Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file application.properties?
Thêm thuộc tính dưới đây vào file
```
spring.jpa.show-sql=true
```
---
## 3. Khi sử dụng H2, làm thế nào để xem được cơ sở dữ liệu và viết câu truy vấn?
Thêm cấu hình này vào file `application.properties`
```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```
Sau đó chạy ứng dụng và mở trình duyệt truy cập URL: 
- http://localhost:8080/h2-console/login.jsp
+ username:sa
+ password:123
- sau khi truy cập có thể xem được cơ sở dữ liệu đã tạo trong `model`
- muốn truy vấn sẽ viết câu lệnh query
---
## 4. Khi viết mô tả một model, những thuộc tính chúng ta không muốn lưu xuống CSDL thì cần đánh dấu bằng annotation nào?
Transient annotation

`@Transient dùng để đánh dấu một thuộc tính trong entity class không phải là một cột tương ứng trong database. Ví dụ chúng ta có thuộc tính age được tính bằng công thức năm hiện tại trừ đi năm sinh, do thuộc age không cần lưu trong database.`
```java
@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="STUDENT_NAME", length=50, nullable=false)
    private String name;
    
    @Transient
    private Integer age;
    
    // other fields, getters and setters
}
```
---
## 5. Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính. Tham số nào trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính, tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu, tham số nào buộc trường không được null?
``` @Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)```

`name="STUDENT_NAME"`: đặt lại tên cột khác với tên thuộc tính ban đầu

`nullable=false`: cột name sẽ không được để trống trong database

`unique=true`: tham số nào chỉ định yêu cầu duy nhất

---
## 6. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung
o Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)

o Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)

---> Vậy 2 annotation này là:

`@PrePersist`

`@PreUpdate`

---
## 7. Tổ hợp các trường thông tin địa chỉ: country, city, county, addressline thường luôn đi cùng nhau và sử dụng lại trong các Entity khác nhau. Nhóm 2 annotation nào dùng để tái sử dụng, nhúng một Entity vào một Entity khác?
`@Embedded`, `@Embeddable`, `@AttributeOverrides`, `@AttributeOverride` 
do Java Persistence API cung cấp.


Code ví dụ:
```java
@Entity
public class Company {
    @Id   @GeneratedValue   private Integer id;
    private String name;
    private String address;
    private String phone;
    @Embedded  private ContactPerson contactPerson;
    // thuộc tính này được nhúng từ 1 entity khác mà không cần tạo bảng
    // standard getters, setters
}
```

```java
@Embeddable
public class ContactPerson {

    private String firstName;

    private String lastName;

    private String phone;

    // standard getters, setters
}
```
`Vấn đề là các trường của ta được gọi là những thứ như contactFirstName trong lớp Company ban đầu của ta và bây giờ là firstName trong lớp ContactPerson của chúng tôi . Vì vậy, JPA sẽ muốn ánh xạ những thứ này thành contact_first_name và first_name, tương ứng.
Tuy nhiên như hai lớp ở trên ta đều có cột điện thoại phone vì vậy ghi đè lên nó sẽ trùng lặp`

Giải pháp ở đây là ta sử dụng @AttributeOverrides và @AttibuteOverride để ghi đè các thuộc tính cột của kiểu embedded của ta.
```java
@Embedded
@AttributeOverrides({
  @AttributeOverride( name = "firstName", column = @Column(name = "contact_first_name")),
  @AttributeOverride( name = "lastName", column = @Column(name = "contact_last_name")),
  @AttributeOverride( name = "phone", column = @Column(name = "contact_phone"))
})
private ContactPerson contactPerson;
```
---
## 8. JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?
Kế thừa từ interface `Repository` trong Spring Data abstract repository 

---
## 9. Hãy viết khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
}
```

```java
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
```

```java
@Service
public class PostService{
    @Autowired
    private PostRepository postRepository;
    public Collection<Post> getAll() {
        return postRepository.findAll();
    }
}
```
---
## 10. Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì có cần phải dùng xác định unique dùng annotation @Column(unique=true) không?

 Không cần dùng annotation `@Column(unique=true)` bởi vì thuộc tính có gán annotation `@Id` đã xác định đó là khóa chính trong bảng và nó là duy nhất.

---
## 11. Khác biệt giữa @Id với @NaturalId là gì?

- `@Id` và `@NaturalId` đều giống nhau ở 1 điểm đó là phải unique (duy nhất)

- `@Id` khác `@NaturalId` đó là: `@Id` không được thay đổi vì annotation này biểu diễn khóa chính trong bảng, còn `@NaturalId` có thể thay đổi được chỉ cần vẫn duy nhất.
---

## 12. Có những cột không phải primary key (@Id) hay @NaturalId, dữ liệu có thể trùng lặp (unique không đảm bảo true), nhưng cần đánh chỉ mục (index) để tìm kiếm nhanh hơn vậy phải dùng annotation gì? Hãy viết 1 ví dụ sử dụng annotation đó với index cho 1 column và 1 ví dụ với index cho tổ hợp nhiều column. Tham khảo tại (https://www.baeldung.com/jpaindexes)

---
## 13. Annotation @GeneratedValue dùng để chọn cách tự sinh unique id cho primary key phải là trường kiểu int hoặc long. Nếu trường primary key có kiểu là String, chúng ta không thể dùng @GeneratedValue vậy hãy thử liệt kê các cách đảm bảo sinh ra chuỗi có tính duy nhất?
```java
@Data
@Entity
@Table
@NoArgsConstructor
public class Story {
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
@Column(name = "PR_KEY")
private String prKey;
private String title; 
}
```
---
## 14. Giả sử có 1 class Employee với các fields sau {id, emailAddress, firstName, lastName}. Hãy
`viết các method trong interface EmployeeRespository để :
o Tìm tất cả các Employee theo emailAddress và lastName
o Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
o Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
o Tìm tất cả các Employee theo fistName không phân biệt hoa thường`

---
## 15. Hãy nêu cách sử dụng của @NamedQuery và @Query. Cho ví dụ

*@NamedQuery*
Nếu bạn không sử dụng repository interface mà chỉ dùng EntityManager để thao tác dữ liệu. Query đó sử dụng ở nhiều nơi khác nhau thì có thể dùng @NamedQuery.
```java
@Entity(name ="oto") //tên entity sẽ sử dụng trong câu lệnh JPQL
@Table(name = "car") //tên table sẽ sử dụng để lưu xuống bảng vật lý trong CSDL
@Data //annotation của Lombok
@NamedQuery(name = "Car.findById", query = "SELECT c FROM oto c WHERE c.id=:id")
public class Car {
@Id private long id; 
private String model;
private String maker;
private int year; 
}
```
----
