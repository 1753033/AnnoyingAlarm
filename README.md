# Annoying Alarm
## Project Android: Annoying Alarm - Wake you up everytime

1.  1753033 - Trịnh Thái Bình - 1753033@student.hcmus.edu.vn

2.  1753030 - Mai Thanh Bình - 1753030@student.hcmus.edu.vn


## Mô tả chức năng
link: https://drive.google.com/file/d/1YCceFuEgN4QqU8mrMTwRrR80XZpNjVom/view?usp=sharing

1. Hẹn giờ báo thức

* Đây là chức năng chính của ứng dụng, cho người dùng tùy chỉnh các lựa chọn
thiết lập báo thức như: thời gian, nhãn nhận biết, âm thanh, lặp lại, độ lớn âm
lượng, âm lượng tăng dần.

* Điểm đặc biệt là sẽ có phần chọn nhiệm vụ tắt được báo thức:

  o Mặc định: chỉ cần bấm tắt một lần.

  o Lắc: người dùng sẽ chọn số lần lắc điện thoại.

  o Giải bài toán: chọn số lượng và mực độ khó của bài toán.

  o Có thể thêm nhiều nhiệm vụ mới trong tương lai.

2. Quản lý âm thanh

* Các chức năng thêm xóa một bài hát, đoạn nhạc làm chuông báo thức.

3. Đọc tin tức

* Chức năng này yêu cầu thiết bị phải được kết nối với Internet.

* Cung cấp những tin tức mới nhất cho người dùng, giúp người dung theo dõi
thông tin mà không cần phải truy cập nhiều trang báo.

4. Theo dõi thời tiết

* Yêu cầu: thiết bị kết nối Internet.

* Cung cấp thông tin thời tiết trong ngày.

5. Nghe nhạc thư giản trước khi ngủ

* Cho người dùng chọn những bản nhạc thư giản ngắn trước khi ngủ.

* Người dùng sẽ chọn thời gian bản nhạc được phát.

* Nhạc thư giản gồm những bài hát nhẹ nhàng, tiếng mưa rơi.

6. Theo dõi lịch sử giấc ngủ

* Đưa ra một bảng báo cáo thời gian ngủ người dùng theo từng ngày, tuần, tháng,

* Bao gồm thời gian bắt đầu ngủ và thức dậy.

7. Tùy chỉnh giao diện

* Cho phép tùy chỉnh màu sắc của ứng dụng: light mode, dark mode.

8. Chế độ tiết kiệm pin

* Không báo thức nếu thiết bị trong chế độ rung

9. To do list

* Cho người dùng lên danh sách những việc cần làm và đánh dấu hoàn thành khi
đã làm xong việc.

10. Thêm nhắc nhở

* Một dạng báo thức nhưng kèm theo một sự kiện nào đó, bao gồm ghi chú, thời
gian, công việc là gì.

11. Đồng bộ Google Calendar

* Yêu cầu: thiết bị kết nối Internet và phải đăng nhập tài khoản google.

* Đồng bộ những sự kiện với Google Calendar.

12. Hẹn giờ tắt media

* Cho phép người dùng đặt thời gian, sau khoảng thời gian đó tất cả các ứng dụng
phát media sẽ được tắt.

Ví dụ: Youtube, Soundcloud, Facebook Video, ...

13. Đăng nhập

* Đăng nhập bằng tài khoản google.

14. Đồng bộ dữ liệu cũ

* Đồng bộ dữ liệu cũ nếu đã đăng nhập trước đó.

* Đề xuất: dùng sqlite kết hợp firebase.

15. Thành tích

* Đánh giá bằng cách tính điểm.

* Điểm sẽ ứng với thời gian người dùng dậy và thời gian đặt báo thức.

* Từ đó, người dùng sẽ theo dõi được tình trạng ngủ của mình và điều chỉnh lại
thời gian báo thức phù hợp.

Tham khảo: Alarmy

## Kế hoạch trong tuần
* Tuần 1: Giao Diện - UI
  | MSSV    | Tên thành viên         | Chức năng           | % Hoàn thành |
  |---------|------------------------|---------------------|--------------|
  | 1753033 |   Trịnh Thái Bình      | Báo thức            |     100      |
  |         |                        | Thời tiết           |     100      |
  |         |                        | Tin tức             |     100      |
  |         |                        | Nhạc thư giãn       |     100      |
  |         |                        | Thêm nhắc nhở       |     100      |
  |         |                        |                     |              |
  | 1753030 |   Mai Thanh Bình       | Đăng nhập           |     100      |
  |         |                        | Theo dõi giấc ngủ   |     100      |
  |         |                        | Thành tích          |     100      |
  |         |                        | Đồng bộ GCalender   |     100      |
  |         |                        | Đồng bộ dữ liệu     |     100      |
  |         |                        |                     |              |
  | 1753028 |   Nguyễn Trần Tuấn Anh | Tiết kiệm pin       |     100      |
  |         |                        | To do list          |     100      |
  |         |                        | Hẹn giờ tắt Media   |     100      |
  |         |                        | Quản lý âm thanh    |     100      |
  |         |                        | Tùy chỉnh giao diện |     100      |
  
  Link prototype: https://www.figma.com/file/RmN8naeVG5uLmm1Fup2ETK/Project-Android?node-id=1%3A3

* Tuần 2: Cài đặt giao diện - Layout XML
  | MSSV    | Tên thành viên         | Chức năng           | % Hoàn thành |
  |---------|------------------------|---------------------|--------------|
  | 1753033 |   Trịnh Thái Bình      | Báo thức            |     100      |
  |         |                        | Thời tiết           |     100      |
  |         |                        | Tin tức             |     100      |
  |         |                        | Thêm nhắc nhở       |     100      |
  |         |                        |                     |              |
  | 1753030 |   Mai Thanh Bình       | Đăng nhập           |     100      |
  |         |                        | Theo dõi giấc ngủ   |     100      |
  |         |                        | Thành tích          |     100      |
  |         |                        | Đồng bộ GCalender   |     100      |
  |         |                        |                     |              |
  | 1753028 |   Nguyễn Trần Tuấn Anh | To do list          |     100      |

  Link UI : https://drive.google.com/open?id=1b3Y06f8MNqxVWmXptFNP63FMunYbdxSq
* Tuần 3: Cài đặt chức năng Báo thức,Tin tức,Thời tiết
  | Chức năng           | % Giao diện | % Chức năng |
  |---------------------|-------------|-------------|
  | Báo thức            |      70     |      50     |
  | Thời tiết           |      90     |     100     |
  | Tin tức             |      90     |     100     |
  
  Tham khảo : 
  https://viblo.asia/p/ung-dung-alarmclock-voi-android-ZK1ov1dyG5b9
  https://www.youtube.com/watch?v=hYT111gytpg&t=4s
  https://www.youtube.com/watch?v=syYMLWQLBQk&t=23s
  https://youtu.be/spvbL94cE9c
 
