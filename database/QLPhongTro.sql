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
values ('CNTT', N'Công nghệ thông tin'),
	('CNSH', N'Công nghệ sinh học'),
	('QTKD', N'Quản trị kinh doanh'),
	('CNHH', N'Công nghệ hóa học')

insert into ChuyenNganh (MaChuyenNganh, TenChuyenNganh, MaKhoa)
values ('KHMT', N'Khoa học máy tính', 'CNTT'),
	('KTPM', N'Kĩ thuật phần mềm', 'CNTT'),
	('QTKD', N'Quản trị kinh doanh', 'QTKD'),
	('HPT', N'Hóa phân tích', 'CNHH')


