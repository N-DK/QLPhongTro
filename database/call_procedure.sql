/*Lấy danh sách của từng loại*/
use QLPhongTro;
CREATE PROCEDURE findAll(@type nvarchar(255))
AS
BEGIN
	IF @type = 'khoa'
	BEGIN
		SELECT * FROM Khoa
	END
	IF @type = 'lop'
	BEGIN
		SELECT * FROM Lop
	END
	IF @type = 'chuyenNganh'
	BEGIN
		SELECT * FROM ChuyenNganh
	END
	IF @type = 'sinhVien'
	BEGIN
		SELECT * FROM SinhVien
	END
	IF @type = 'chuPhong'
	BEGIN
		SELECT * FROM ChuPhong
	END
	IF @type = 'hopDong'
	BEGIN
		SELECT * FROM HopDong
	END
	ELSE IF @type = 'phongTro'
	BEGIN
		SELECT * FROM PhongTro
	END
END

/*Lấy một đối tượng dụa vào mã*/
CREATE PROCEDURE findOneById(@type nvarchar(255), @ma nvarchar(255))
AS 
BEGIN
	IF @type = 'khoa'
	BEGIN
		SELECT * FROM Khoa WHERE MaKhoa = @ma
	END
	IF @type = 'lop'
	BEGIN
		SELECT * FROM Lop WHERE MaLop = @ma
	END
	IF @type = 'chuyenNganh'
	BEGIN
		SELECT * FROM ChuyenNganh WHERE MaChuyenNganh = @ma
	END
	IF @type = 'chuPhong'
	BEGIN
		SELECT * FROM ChuPhong WHERE MaChuPhong = @ma
	END
	ElSE IF @type = 'phongTro'
	BEGIN
		SELECT * FROM PhongTro WHERE MaPhong = @ma
	END
END

/*Xóa một đối tượng theo mã*/
CREATE PROCEDURE deleteOneById(@type va	rchar(255), @ma varchar(255))
AS
BEGIN
	IF @type = 'khoa'
	BEGIN
		DELETE FROM Khoa WHERE MaKhoa = @ma
	END
	IF @type = 'sinhVien'
	BEGIN
		DELETE FROM SinhVien WHERE MaSinhVien = @ma
	END
	IF @type = 'lop'
	BEGIN
		DELETE FROM Lop WHERE MaLop = @ma
	END
	IF @type = 'chuyenNganh'
	BEGIN
		DELETE FROM ChuyenNganh WHERE MaChuyenNganh = @ma
	END
	IF @type = 'phongTro'
	BEGIN
		DELETE FROM PhongTro WHERE MaPhong = @ma
	END
	IF @type = 'chuPhong'
	BEGIN
		DELETE FROM ChuPhong WHERE MaChuPhong = @ma
	END
END

CREATE PROCEDURE saveSinhVien(@type varchar(255), @ma varchar(8), @ho nvarchar(50), @ten nvarchar(30), @maLop varchar(10), @queQuan nvarchar(40), @gioiTinh int, @ngaySinh Date, @sdt varchar(12))
AS 
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into SinhVien(MaSinhVien, Ho, Ten, MaLop, QueQuan, GioiTinh, NgaySinh, Sdt) values (@ma, @ho, @ten, @maLop, @queQuan, @gioiTinh, @ngaySinh, @sdt)
	END
	ELSE IF @type = 'update'
	BEGIN
		UPDATE SinhVien SET Ho = @ho, Ten = @ten, MaLop = @maLop, QueQuan = @queQuan, GioiTinh = @gioiTinh, NgaySinh = @ngaySinh, Sdt = @sdt WHERE MaSinhVien = @ma
	END
END

CREATE PROCEDURE saveLop(@type varchar(255), @ma varchar(255), @ten nvarchar(255), @gvcn nvarchar(255), @macn varchar(255))
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into Lop (MaLop, TenLop, TenGVCN, MaChuyenNganh) values(@ma, @ten, @gvcn, @macn)
	END
	ELSE IF @type = 'update'
	BEGIN
		UPDATE Lop SET TenLop = @ten, TenGVCN = @gvcn, MaChuyenNganh = @macn WHERE MaLop = @ma
	END
END

CREATE PROCEDURE saveKhoa(@type varchar(255), @ma varchar(255), @ten nvarchar(255))
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into Khoa (MaKhoa, TenKhoa) values(@ma, @ten)
	END
	IF @type = 'update'
	BEGIN
		UPDATE Khoa SET TenKhoa = @ten WHERE MaKhoa = @ma
	END
END

CREATE PROCEDURE saveChuyenNganh(@type varchar(255), @ma varchar(255), @ten nvarchar(255), @maKhoa varchar(255))
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into  ChuyenNganh (MaChuyenNganh, TenChuyenNganh, MaKhoa) values(@ma, @ten, @maKhoa)
	END
	IF @type = 'update'
	BEGIN
		UPDATE ChuyenNganh SET TenChuyenNganh = @ten, MaKhoa = @maKhoa WHERE MaChuyenNganh = @ma
	END
END

CREATE PROCEDURE savePhongTro(@type varchar(255), @ma varchar(255), @machuphong varchar(255), @gia varchar(255), @diachi nvarchar(255), @tinhtrang varchar(255))
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into PhongTro (MaPhong, MaChuPhong, Gia, DiaChi, TinhTrang) values (@ma, @machuphong, @gia, @diachi, @tinhtrang)
	END
	IF @type = 'update'
	BEGIN
		UPDATE PhongTro SET MaChuPhong = @machuphong, Gia = @gia, DiaChi = @diachi, TinhTrang = @tinhtrang WHERE MaPhong = @ma
	END
END


CREATE PROCEDURE findPhongTro
	@ma varchar(255),
	@diachi nvarchar(255), 
	@maChuPhong nvarchar(255)
AS
BEGIN
	SELECT *
	FROM PhongTro
	WHERE
		(@ma is null or MaPhong = @ma)
		AND (@diachi is null or DiaChi like '%' + @diachi + '%')
		AND (@maChuPhong is null or MaChuPhong = @maChuPhong)
END

CREATE PROCEDURE findSinhVien(@ma varchar(255),@ho nvarchar(255),@ten nvarchar(255),@maLop varchar(255),@queQuan nvarchar(255),@maKhoa varchar(255), @maCN varchar(255))
AS
BEGIN
    SELECT MaSinhVien, Ho, Ten,GioiTinh,NgaySinh,Sdt ,SinhVien.MaLop, QueQuan, ChuyenNganh.MaKhoa, ChuyenNganh.MaChuyenNganh
	FROM SinhVien JOIN Lop ON SinhVien.MaLop = Lop.MaLop JOIN ChuyenNganh ON Lop.MaChuyenNganh = ChuyenNganh.MaChuyenNganh 
    WHERE 
        (@ma IS NULL OR MaSinhVien = @ma)
        AND (@ho IS NULL OR Ho like '%' + @ho + '%')
        AND (@ten IS NULL OR Ten like '%' + @ten + '%')
        AND (@maLop IS NULL OR SinhVien.MaLop = @maLop)
        AND (@queQuan IS NULL OR QueQuan like '%' + @queQuan + '%')
		AND (@maKhoa IS NULL OR ChuyenNganh.MaKhoa like '%' + @maKhoa + '%')
		AND (@maCN IS NULL OR ChuyenNganh.MaChuyenNganh like '%' + @maCN + '%')
END

CREATE PROCEDURE saveChuPhong(@type varchar(255), @ma varchar(255),@ho nvarchar(255),@ten nvarchar(255), @sdt varchar(15), @diaChi nvarchar(255), @ngaySinh date, @gioiTinh int)
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		INSERT INTO ChuPhong(MaChuPhong,Ho,Ten,Sdt,DiaChi,NgaySinh,GioiTinh) values (@ma,@ho,@ten,@sdt,@diaChi,@ngaySinh,@gioiTinh)
	END
	IF @type = 'update'
	BEGIN
		update ChuPhong set Ho = @ho, Ten = @ten, Sdt = @sdt, DiaChi = @diaChi, NgaySinh=@ngaySinh, GioiTinh = @gioiTinh WHERE MaChuPhong = @ma
	END
END

CREATE PROCEDURE savePhongTro(@type varchar(255), @ma varchar(255), @machuphong varchar(255), @gia varchar(255), @diachi nvarchar(255), @tinhtrang int)
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into PhongTro (MaPhong, MaChuPhong, Gia, DiaChi, TinhTrang) values (@ma, @machuphong, @gia, @diachi, @tinhtrang)
	END
	IF @type = 'update'
	BEGIN
		UPDATE PhongTro SET MaChuPhong = @machuphong, Gia = @gia, DiaChi = @diachi, TinhTrang = @tinhtrang WHERE MaPhong = @ma
	END
END

CREATE PROCEDURE saveHopDong(@type varchar(255), @ma varchar(255), @maSV varchar(255), @maPhong varchar(255), @ngayKy date, @ngayHet date)
AS
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into HopDong(MaHopDong, MaSinhVien, MaPhong, NgayKyHopDong, NgayHetHopDong) values (@ma, @maSV, @maPhong, @ngayKy, @ngayHet)
		UPDATE PhongTro SET TinhTrang = 0 WHERE MaPhong = @maPhong
	END
	IF @type = 'update'
	BEGIN
		UPDATE HopDong SET MaSinhVien = @maSV, MaPhong = @maPhong, NgayKyHopDong = @ngayKy, NgayHetHopDong = @ngayHet WHERE MaHopDong = @ma
	END
END


CREATE PROCEDURE deleteHopDong(@ma varchar(255), @maPhong varchar(255))
AS
BEGIN
	DELETE FROM HopDong WHERE MaHopDong = @ma
	UPDATE PhongTro SET TinhTrang = 1 WHERE MaPhong = @maPhong

END
