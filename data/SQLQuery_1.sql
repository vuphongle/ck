create database QLNVIEN
go
use QLNVIEN
go
CREATE TABLE PhongBan(
   maPhong  VARCHAR (10) primary key,
   tenPhong NVARCHAR (30) NOT NULL,     
);
CREATE TABLE NhanVien(
   maNV  NVARCHAR (30) primary key,
   ho NVARCHAR (50)  NULL,
   ten NVARCHAR (50)  NULL,
   tuoi int NULL,
   phai bit  NULL,
   maPhong  VARCHAR (10) NULL,  
   tienLuong float,
   Constraint F_LP_HN Foreign key(maPhong) references PhongBan(maPhong),
);
INSERT PhongBan([maPhong], [tenPhong]) VALUES ('PHONG_TC', N'Phòng tổ chức')
INSERT PhongBan([maPhong], [tenPhong]) VALUES ('PHONG_KT', N'Phòng kỹ thuật')
INSERT PhongBan([maPhong], [tenPhong]) VALUES ('PHONG_NS', N'Phòng nhân sự')

SELECT * FROM PhongBan
SELECT NhanVien.maNV, NhanVien.ten, NhanVien.ho, NhanVien.tuoi, NhanVien.phai, NhanVien.maPhong, NhanVien.tienLuong, PhongBan.tenPhong FROM     NhanVien INNER JOIN PhongBan ON NhanVien.maPhong = PhongBan.maPhong
INSERT INTO dbo.NhanVien
(
    maNV,
    ho,
    ten,
    tuoi,
    phai,
    maPhong,
    tienLuong
)
VALUES
(   N'003',  -- maNV - nvarchar(30)
    N'Nguyễn Thuý',  -- ho - nvarchar(50)
    N'Tình',  -- ten - nvarchar(50)
    20,    -- tuoi - int
    0, -- phai - bit
    'PHONG_NS',   -- maPhong - varchar(10)
    2000.000   -- tienLuong - float
    )