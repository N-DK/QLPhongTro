CREATE DATABASE QLPhongTro;
go
use QLPhongTro;
go

create table Khoa (
	MaKhoa nvarchar(25) not null primary key,
	TenKhoa nvarchar(50),
)

create table Lop (
	MaLop nvarchar(25) not null primary key,
	TenLop nvarchar(50),
	TenGVCN nvarchar(50),
	MaKhoa nvarchar(25)
	FOREIGN KEY (MaKhoa) REFERENCES Khoa(MaKhoa)
)

create table SinhVien (
	MaSinhVien nvarchar(8) not null primary key,
	Ho nvarchar(30),
	Ten nvarchar(30),
	MaLop nvarchar(25),
	QueQuan nvarchar(20),
	GioiTinh int,
	NgaySinh Date,
	Sdt nvarchar(10),
	FOREIGN KEY (MaLop) REFERENCES Lop(MaLop)
)

create table ChuPhong(
	MaChuPhong nvarchar(20) not null primary key,
	Ho nvarchar(15),
	Ten nvarchar(20),
	Sdt nvarchar(10),
	DiaChi nvarchar(25),
	NgaySinh Date,
	GioiTinh int
)

create table PhongTro(
	MaPhong nvarchar(20) not null primary key,	
	MaChuPhong nvarchar(20),
	Gia float,
	DiaChi nvarchar(25),
	TinhTrang int,
	FOREIGN KEY (MaChuPhong) REFERENCES ChuPhong(MaChuPhong)
)

create table HopDong (
	MaHopDong nvarchar(20) not null primary key,
	NgayThue Date,
	SoThangThue int,
	MaSinhVien nvarchar(8),
	MaPhong nvarchar(20),
	FOREIGN KEY (MaSinhVien) REFERENCES SinhVien(MaSinhVien),
	FOREIGN KEY (MaPhong) REFERENCES PhongTro(MaPhong)
)



