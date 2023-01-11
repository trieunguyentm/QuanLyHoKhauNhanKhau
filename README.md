# QuanLyHoKhauNhanKhau
<h2>Bài Tập Lớn Môn Công Nghệ Phần Mềm</h2>
phần này t sẽ thêm các chức năng về nhân khẩu</br>
thiedzvc
<h2>trình tự tạo một khối chức năng mới:</h2>
<h3>1, tạo view và controller cho khối chức năng</h3>
-Tạo view trong scenebuilder, tạo một tableview để hiện danh sách hiện tại trong database</br>
-Gắn các id cho tableview và gắn các method cho các button</br>
![image](https://user-images.githubusercontent.com/100015056/211810172-45a59d65-3bd3-430c-a180-6e87330ea291.png)</br>
-generate controller theo view vừa tạo</br>
-controller lớn này dùng để ủy nhiệm chức năng cho một khối M-V-C nhỏ khác, sau khi click vào nút(etc tachkhau), controller đưa đến view tách khẩu</br>
![image](https://user-images.githubusercontent.com/100015056/211810230-1f8f5949-42d4-497b-90d5-acb236695501.png)</br>
<h3>2, tạo view - controller - model&service phục vụ từng nhiệm vụ như thêm/sửa/xóa</h3>
-view: một form đơn giản với mục đích lấy dữ liệu nhập </br>
![image](https://user-images.githubusercontent.com/100015056/211810279-e1d015f6-293f-427d-be86-15fe574d5c71.png)
-controller: lấy dữ liệu từ view, valid data, gọi đến service để thao tác database</br>
![image](https://user-images.githubusercontent.com/100015056/211810342-33ae55a3-e77b-4c63-9a3b-57732fba94d2.png)</br>
-service & model: nhận yêu cầu và dữ liệu người dùng từ controller, thực hiện thao tác với database:</br>
![image](https://user-images.githubusercontent.com/100015056/211810367-740cf3da-a469-471c-9fac-c8d54f017cee.png)</br>
<h4>ps: người lớn không có quyền lựa chọn, vì vậy ta sẽ yêu cả hai nàng</h4>
