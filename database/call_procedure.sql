/*Lấy danh sách của từng loại*/
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
	ELSE IF @type = 'sinhVien'
	BEGIN
		SELECT * FROM SinhVien
	END
	ELSE IF @type = 'chuPhong'
	BEGIN
		SELECT * FROM ChuPhong
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
	IF @type = 'phongTro'
	BEGIN
		SELECT * FROM PhongTro WHERE MaPhong = @ma
	END
END

/*Xóa một đối tượng theo mã*/
/*CREATE PROCEDURE deleteOneById(@type varchar(255), @ma varchar(255))
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
END*/

/*CREATE PROCEDURE saveSinhVien(@type varchar(255), @ma varchar(8), @ho nvarchar(50), @ten nvarchar(30), @maLop varchar(10), @queQuan nvarchar(40), @gioiTinh int, @ngaySinh Date, @sdt varchar(12))
AS 
BEGIN
	IF @type = 'insert'
	BEGIN
		insert into SinhVien(MaSinhVien, Ho, Ten, MaLop, QueQuan, GioiTinh, NgaySinh, Sdt) values (@ma, @ho, @ten, @maLop, @queQuan, @gioiTinh, @ngaySinh, @sdt)
	END
	ELSE IF @type = 'udpate'
	BEGIN
		UPDATE SinhVien SET Ho = @ho, Ten = @ten, MaLop = @maLop, QueQuan = @queQuan, GioiTinh = @gioiTinh, NgaySinh = @ngaySinh, Sdt = @sdt WHERE MaSinhVien = @ma
	END
END*/

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

/*CREATE PROCEDURE updateKhoa(@ma varchar(255), @ten nvarchar(255))
AS UPDATE Khoa SET TenKhoa = @ten WHERE MaKhoa = @ma*/

/*
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
END*/

/*CREATE PROCEDURE saveChuyenNganh(@type varchar(255), @ma varchar(255), @ten nvarchar(255), @maKhoa varchar(255))
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
END*/

--CREATE PROCEDURE savePhongTro(@type varchar(255), @ma varchar(255), @machuphong varchar(255), @gia varchar(255), @diachi varchar(255), @tinhtrang varchar(255))
--AS
--BEGIN
--	IF @type = 'insert'
--	BEGIN
--		insert into PhongTro (MaPhong, MaChuPhong, Gia, DiaChi, TinhTrang) values (@ma, @machuphong, @gia, @diachi, @tinhtrang)
--	END
--	IF @type = 'update'
--	BEGIN
--		UPDATE PhongTro SET MaPhong = @ma, MaChuPhong = @machuphong, Gia = @gia, DiaChi = @diachi, TinhTrang = @tinhtrang
--	END
--END


CREATE PROCEDURE findSinhVien(@ma varchar(255), @ho nvarchar(255), @ten nvarchar(255), @maLop varchar(255), @queQuan nvarchar(255))
AS
BEGIN
	IF @ma is not null
	BEGIN
		SELECT * FROM SinhVien WHERE MaSinhVien = @ma
	END
	IF @ho is not null
	BEGIN
		SELECT * FROM SinhVien WHERE Ho like '%' + @ho + '%'
	END
	IF @ten is not null
	BEGIN
		SELECT * FROM SinhVien WHERE Ten like '%' + @ten + '%'
	END
	IF @maLop is not null
	BEGIN
		SELECT * FROM SinhVien WHERE MaLop = @maLop
	END
	ELSE IF @queQuan is not null
	BEGIN
		SELECT * FROM SinhVien WHERE QueQuan like '%' + @queQuan + '%'
	END
END

SELECT * FROM Lop

