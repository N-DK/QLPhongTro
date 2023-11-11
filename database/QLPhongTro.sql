CREATE DATABASE QLPhongTro;
go
use QLPhongTro;
go

create table Khoa (
	MaKhoa nvarchar(30) not null primary key,
	TenKhoa nvarchar(50),
)

create table ChuyenNganh (
	MaChuyenNganh nvarchar(30) not null primary key,
	TenChuyenNganh nvarchar(50),
	MaKhoa nvarchar(30),
	FOREIGN KEY (MaKhoa) REFERENCES Khoa(MaKhoa)
)

create table Lop (
	MaLop nvarchar(30) not null primary key,
	TenLop nvarchar(50),
	TenGVCN nvarchar(30),
	MaChuyenNganh nvarchar(30),
	FOREIGN KEY (MaChuyenNganh) REFERENCES ChuyenNganh(MaChuyenNganh)
)

create table SinhVien (
	MaSinhVien nvarchar(8) not null primary key,
	Ho nvarchar(30),
	Ten nvarchar(30),
	MaLop nvarchar(30),
	QueQuan nvarchar(50),
	GioiTinh int,
	NgaySinh Date,
	Sdt nvarchar(10),
	FOREIGN KEY (MaLop) REFERENCES Lop(MaLop)
)

create table ChuPhong(
	MaChuPhong nvarchar(30) not null primary key,
	Ho nvarchar(30),
	Ten nvarchar(30),
	Sdt nvarchar(10),
	DiaChi nvarchar(50),
	NgaySinh Date,
	GioiTinh int
)

create table PhongTro(
	MaPhong nvarchar(30) not null primary key,	
	MaChuPhong nvarchar(30),
	Gia float,
	DiaChi nvarchar(50),
	TinhTrang int,
	FOREIGN KEY (MaChuPhong) REFERENCES ChuPhong(MaChuPhong)
)

create table HopDong (
	MaHopDong nvarchar(30) not null primary key,
	NgayKyHopDong Date,
	MaSinhVien nvarchar(8),
	MaPhong nvarchar(30),
	NgayHetHopDong Date,
	FOREIGN KEY (MaSinhVien) REFERENCES SinhVien(MaSinhVien),
	FOREIGN KEY (MaPhong) REFERENCES PhongTro(MaPhong)
)


insert into Khoa (MaKhoa, TenKhoa)
values	('CNTT', N'Công nghệ thông tin'),
		('CNSH', N'Công nghệ sinh học'),
		('QTKD', N'Quản trị kinh doanh'),
		('CNHH', N'Công nghệ hóa học')

insert into ChuyenNganh (MaChuyenNganh, TenChuyenNganh, MaKhoa)
values	('KHMT', N'Khoa học máy tính', 'CNTT'),
		('KTPM', N'Kĩ thuật phần mềm', 'CNTT'),
		('QTKD', N'Quản trị kinh doanh', 'QTKD'),
		('HPT', N'Hóa phân tích', 'CNHH')

insert into Lop (MaLop, TenLop, TenGVCN, MaChuyenNganh)
values	('DHKHMT17A', N'Đại học khoa học máy tính 17A', N'Cô Phúc', 'KHMT'),
		('DHKHMT17B', N'Đại học khoa học máy tính 17B', N'Thầy Danh', 'KHMT'),
		('DHKHMT17C', N'Đại học khoa học máy tính 17C', N'Cô Phượng', 'KHMT'),
		('DHKHMT17D', N'Đại học khoa học máy tính 17D', N'Thầy Phúc', 'KHMT')

<<<<<<< HEAD
SELECT * FROM Lop
=======
insert into SinhVien(MaSinhVien, Ho, Ten, MaLop, QueQuan, GioiTinh, NgaySinh, Sdt)
values	('21134361', N'Ngô Đăng', N'Khoa', 'DHKHMT17B', N'Quảng Nam', 1, '2003-11-27', '0792798777'),
		('21134362', N'Nguyễn Thị', N'Hoa', 'DHKHMT17C', N'Quảng Bình', 0, '2003-11-30', '0792798777'),
		('21134363', N'Nguyễn Mạnh', N'Tân', 'DHKHMT17B', N'Quảng Ngãi', 1, '2003-11-30', '0792798777'),
		('21134364', N'Nguyễn Văn', N'Thương', 'DHKHMT17B', N'Hồ Chí Minh', 1, '2003-11-30', '0792798777'),
		('21134365', N'Đăng Nguyễn Minh', N'Thiện', 'DHKHMT17B', N'Hà Nội', 1, '2003-12-11', '0792798777'),
		('21134366', N'Đào Huy', N'Hoàng', 'DHKHMT17B', N'Đà Nẵng', 1, '2003-11-30', '0792798777'),
		('21134367', N'Nguyễn Đức', N'Cường', 'DHKHMT17B', N'Hội An', 1, '2003-9-16', '0792798777'),
		('21134368', N'Võ Ngọc Trung', N'Quân', 'DHKHMT17B', N'Nghệ An', 1, '2003-11-15', '0792798777'),
		('21134369', N'Lê Đình', N'Nam', 'DHKHMT17A', N'Hà Tĩnh', 1, '2003-11-30', '0792798777')
SELECT * FROM SinhVien
>>>>>>> 1efa92c4308e578dcbc44a3b39e92858465bfc10
