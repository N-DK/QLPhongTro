/*Lấy danh sách của từng loại*/
/*CREATE PROCEDURE findAll(@type nvarchar(255))
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
END*/

/*Lấy một đối tượng dụa vào mã*/
/*CREATE PROCEDURE findOneById(@type nvarchar(255), @ma nvarchar(255))
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
END*/

/*Xóa một đối tượng theo mã*/
/*CREATE PROCEDURE deleteOneById(@type varchar(255), @ma varchar(255))
AS
BEGIN
	IF @type = 'khoa'
	BEGIN
		DELETE FROM Khoa WHERE MaKhoa = @ma
	END
END*/

CREATE PROCEDURE updateKhoa(@ma varchar(255), @ten nvarchar(255))
AS UPDATE Khoa SET TenKhoa = @ten WHERE MaKhoa = @ma