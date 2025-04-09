# 24-03-2025
- > Stored Procedures:
    - Syntax:
    *     CREATE PROCEDURE [database_name].[schema_name].[procedure_name]
    *     ([parameter_1] [datatype] [, parameter_2] [datatype] ...)
    *     AS
    *     BEGIN
    *     [statements]
    *     END
    *     CALL: EXEC ProcedureName @TenBien = Value;
- > View:
    - Syntax:
    *     CREATE [ OR ALTER ] VIEW [ schema_name . ] view_name [ (column [ ,...n ] ) ]
    *     [ WITH <view_attribute> [ ,...n ] ]
    *     AS select_statement
    *     [ WITH CHECK OPTION]
    *     [ ; ]
    *     <view_attribute> ::=
    *     {
    *     [ ENCRYPTION ]
    *     [ SCHEMABINDING]
    *     [ VIEW_METADATA ]
    *     }
- > Security
    - Lưu ý: 
        - Cập nhật (sửa) xem như có quyền xem (ngược lại thì không).
    - Phân quyền cho các nhóm: 
        - Admin: 
            > - Tạo, sửa, xóa tài khoản Manager, Staff.
            > - Cấp và thu hồi quyền. 
            > - Quản lý toàn bộ dữ liệu. 
            > - Truy cập và chỉnh sửa. 
            > - Xóa dữ liệu cũ, dọn dẹp, kiểm tra.
        - Manager:
            > - Xem, thêm, sửa, xóa dữ liệu về: khách hàng, phương tiện, thẻ xe, mất thẻ xe, phiên gửi xe. 
            > - Xem báo cáo thống kê.
            > - Tạo, sửa, xóa tài khoản staff.
            > - Không thể truy cập hoặc chỉnh sửa dữ liệu hệ thống cấp cao.
            > - Không thể tạo/sửa tài khoản Manager hoặc Admin.
        - Staff:
            > - Xem, thêm, sửa, xóa dữ liệu vài bản.
            > - Cập nhật trạng thái gửi xe, vào/ra bãi xe.
            > - Báo cáo sự cố (mất thẻ, phương tiện bất thường...).
            > - Không được cấp quyền hoặc quản lý tài khoản.
            > - Không thể xóa hoặc thay đổi dữ liệu quan trọng.
            > - Không thể truy cập báo cáo thống kê tổng hợp.
    - Role chính: 
        - Admin: phát triển ở phần mềm or dùng sa.
        - Manager: 
            > + resident_cards:
                >> - Thêm: thẻ cư dân.
                >> - Sửa: trạng thái thẻ.
                >> - Xóa: khi cư dân không dùng dịch vụ trong khoảng thời gian.
            > + lost_resident_cards:
                >> - Thêm: thẻ mất.
                >> - Xem: danh sách thẻ mất.
                >> - Xóa: sau 1 khoảng thời gian dài.
            > + parking_sessions:
                >> - Thêm: lượt gửi xe.
                >> - Cập nhật: thời gian ra, ca trực ra, amount,...
                >> - Xóa: sau 1 khoảng thời gian dài.
            > + vehicles:
                >> - Thêm: Thêm xe.  
                >> - Sửa: màu xe, tên xe, mã nhận dạng,...
                >> - Xóa: sau 1 khoảng thời gian.
            > + customers:
                >> - Thêm: thêm khách hàng.
                >> - Sửa: thông tin khách hàng. 
                >> - Xóa: sau khoảng thời gian dài chưa dùng dịch vụ.
        - Staff: 
            > + resident_cards:
                >> - Xem: thông tin thẻ khi cần.
            > + lost_resident_cards:
                >> - Xem: thông tin khi cần.
                >> - Thêm: khi có người báo mất. 
            > + parking_sessions:
                >> - Thêm: lượt gửi xe mới.
                >> - Sửa (cập nhật): thời gian ra, ca trực ra, amount,... 
            > + vehicles:
                >> - Thêm: khi có xe mới. 
                >> - Sửa (cập nhật): màu xe, tên xe, mã nhận dạng,...
            > + customers:
                >> - Xem: khi cần thiết.
# 26-03-2025
- > GUI: 
    - Enable EditRow in JTable (Done).
    - Format Header in initTable(). 
- > Database:
    - Done Query, Insert Data.
    - Store Procedures (customer, resident_cards, lost_resident_cards, parking_sessions, vehicles).
    - Trigger (Create resident_cards_registration).
# 28-03-2025
- > Login Server.
- > Combine: Store Procedure + View.
# (02&03)-04-2025: 
- > Load Data + Edit Dao. 
- > Trigger. 
