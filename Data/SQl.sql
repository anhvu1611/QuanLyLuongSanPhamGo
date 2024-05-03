USE [master]
GO
/****** Object:  Database [QuanLyLuongSanPham]    Script Date: 12/14/2023 12:25:10 PM ******/
CREATE DATABASE [QuanLyLuongSanPham]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyLuongSanPham', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS01\MSSQL\DATA\QuanLyLuongSanPham.mdf' , SIZE = 5312KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QuanLyLuongSanPham_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS01\MSSQL\DATA\QuanLyLuongSanPham_log.ldf' , SIZE = 2368KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [QuanLyLuongSanPham] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyLuongSanPham].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyLuongSanPham] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLyLuongSanPham] SET QUERY_STORE = OFF
GO
USE [QuanLyLuongSanPham]
GO
/****** Object:  UserDefinedFunction [dbo].[danhSachThongKe]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[danhSachThongKe](
	@thang int,
	@nam int
)RETURNS @ResultTable TABLE
(
    LuongThucNhan INT,
    NgayLam INT,
    LuongCoBan INT,
    HeSoLuong FLOAT,
    LuongTruocThue INT,
    KhauTruBaoHiem INT,
    MaNhanVien NVARCHAR(8)
)
as
begin
	
	declare @maNhanVien Nvarchar(8)
	set @maNhanVien = 'NV0001'
	while @maNhanVien <= (select max(maNhanVien) from NhanVien)
	begin
		if @maNhanVien in (select distinct maNhanVien from BangLuongNhanVien where month(ngayLap) = @thang and year(ngayLap) = @nam and soNgayDiLam > 0)
		begin
			declare @tongNgayLam int, @caNgay int, @nuaNgay int, @coPhep int, @khongPhep int,@tangCa int,@tre int,@luongCoBan int, @heSoLuong float, @tongThuNhapTheoNgayCong int, @phuCap int, @bhyt nvarchar(8),@bhxh nvarchar(8)
			select @caNgay = soNgayLamCaNgay,
			@nuaNgay = soNgayLamNuaNgay,
			@coPhep = soNgayVangCoPhep,
			@khongPhep = soNgayVangKhongPhep,
			@tangCa = soGioTangCa,
			@tre = soNgayTre,
			@luongCoBan = cv.luongCungTheoChucVu,
			@heSoLuong = nv.[heSoLuong],
			@phuCap = phuCap,
			@bhxh = BHXH,
			@bhyt = BHYT,
			@tongNgayLam = soNgayDiLam
			from BangLuongNhanVien bl
			join NhanVien nv on nv.maNhanVien = bl.maNhanVien
			join ChucVu cv on cv.maChucVu = nv.maChucVu
			where soNgayDiLam > 0 and month(ngayLap) = @thang and year(ngayLap) = @nam and bl.maNhanVien = @maNhanVien

			if @coPhep <=2
				begin
					set @coPhep = 0
				end

			declare @luong1Ngay int
			set @luong1Ngay = (@luongCoBan*@heSoLuong)/26
			set @tongThuNhapTheoNgayCong = @luong1Ngay*(@tongNgayLam - @coPhep - @khongPhep) - @tre* 50000 + @tangCa*(@luong1Ngay/8*1.5)
			declare @luongTruocThue int
			set @luongTruocThue = @tongThuNhapTheoNgayCong + @phuCap
			declare @khauTruBaoHiem int, @bhytFloat float, @bhxhFloat float

			if @bhyt = N'Tham Gia'
				begin
					set @bhytFloat = 0.015
				end
			else
				begin
					set @bhytFloat = 0;
				end
			if @bhxh = N'Tham Gia'
				begin
					set @bhxhFloat = 0.08
				end
			else
				begin
					set @bhxhFloat = 0;
				end
			set @khauTruBaoHiem = @luongTruocThue*@bhxhFloat +@luongTruocThue * @bhytFloat

			declare @luongThuc int
			set @luongThuc = @luongTruocThue - @khauTruBaoHiem
			insert @ResultTable
			select @luongThuc as LuongThucNhan, ngayLam = @tongNgayLam, @luongCoBan, @heSoLuong, @luongTruocThue, @khauTruBaoHiem, @maNhanVien
		
		end
		SET @maNhanVien = 'NV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@maNhanVien, 3, LEN(@maNhanVien) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
	end
	return
end
GO
/****** Object:  UserDefinedFunction [dbo].[danhSachThongKeTheoThang]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[danhSachThongKeTheoThang](
	@tuThang int,
	@tuNam int,
	@denThang int,
	@denNam int
)RETURNS @ResultTable TABLE
(
    LuongThucNhan INT,
    NgayLam INT,
    LuongCoBan INT,
    HeSoLuong FLOAT,
    LuongTruocThue INT,
    KhauTruBaoHiem INT,
    MaNhanVien NVARCHAR(8)
)
as
begin
	
	declare @maNhanVien Nvarchar(8)
	set @maNhanVien = 'NV0001'
	while @maNhanVien <= (select max(maNhanVien) from NhanVien)
	begin
		if @maNhanVien in (select distinct maNhanVien from BangLuongNhanVien where month(ngayLap) >= @tuThang and month(ngayLap)<=@denThang and year(ngayLap) >= @tuNam and year(ngayLap)<=@denNam and soNgayDiLam > 0)
		begin
			declare @tongNgayLam int, @caNgay int, @nuaNgay int, @coPhep int, @khongPhep int,@tangCa int,@tre int,@luongCoBan int, @heSoLuong float, @tongThuNhapTheoNgayCong int, @phuCap int, @bhyt nvarchar(8),@bhxh nvarchar(8)
			select @caNgay = soNgayLamCaNgay,
			@nuaNgay = soNgayLamNuaNgay,
			@coPhep = soNgayVangCoPhep,
			@khongPhep = soNgayVangKhongPhep,
			@tangCa = soGioTangCa,
			@tre = soNgayTre,
			@luongCoBan = cv.luongCungTheoChucVu,
			@heSoLuong = nv.[heSoLuong],
			@phuCap = phuCap,
			@bhxh = BHXH,
			@bhyt = BHYT,
			@tongNgayLam = soNgayDiLam
			from BangLuongNhanVien bl
			join NhanVien nv on nv.maNhanVien = bl.maNhanVien
			join ChucVu cv on cv.maChucVu = nv.maChucVu
			where soNgayDiLam > 0 and month(ngayLap) >= @tuThang and month(ngayLap)<=@denThang and year(ngayLap) >= @tuNam and year(ngayLap)<=@denNam and bl.maNhanVien = @maNhanVien

			if @coPhep <=2
				begin
					set @coPhep = 0
				end

			declare @luong1Ngay int
			set @luong1Ngay = (@luongCoBan*@heSoLuong)/26
			set @tongThuNhapTheoNgayCong = @luong1Ngay*(@tongNgayLam - @coPhep - @khongPhep) - @tre* 50000 + @tangCa*(@luong1Ngay/8*1.5)
			declare @luongTruocThue int
			set @luongTruocThue = @tongThuNhapTheoNgayCong + @phuCap
			declare @khauTruBaoHiem int, @bhytFloat float, @bhxhFloat float

			if @bhyt = N'Tham Gia'
				begin
					set @bhytFloat = 0.015
				end
			else
				begin
					set @bhytFloat = 0;
				end
			if @bhxh = N'Tham Gia'
				begin
					set @bhxhFloat = 0.08
				end
			else
				begin
					set @bhxhFloat = 0;
				end
			set @khauTruBaoHiem = @luongTruocThue*@bhxhFloat +@luongTruocThue * @bhytFloat

			declare @luongThuc int
			set @luongThuc = @luongTruocThue - @khauTruBaoHiem
			insert @ResultTable
			select @luongThuc as LuongThucNhan, ngayLam = @tongNgayLam, @luongCoBan, @heSoLuong, @luongTruocThue, @khauTruBaoHiem, @maNhanVien
		
		end
		SET @maNhanVien = 'NV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@maNhanVien, 3, LEN(@maNhanVien) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
	end
	return
end
GO
/****** Object:  UserDefinedFunction [dbo].[layBangNhanVienTinhLuong]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layBangNhanVienTinhLuong](
    @thang INT,
    @nam INT
)
RETURNS @nhanVienCoTheTinhLuong TABLE (
    maNhanVien NVARCHAR(6),
    tenNhanVien NVARCHAR(50),
    tenChucVu NVARCHAR(255),
    tenPhongBan NVARCHAR(50)
)
AS
BEGIN
    IF (@thang IS NOT NULL AND @thang <> 0)
    BEGIN
		if (@nam is not null and @nam <> 0)
			begin
				INSERT INTO @nhanVienCoTheTinhLuong (maNhanVien, tenNhanVien, tenChucVu, tenPhongBan)
				SELECT DISTINCT
					bcc.maNhanVienDuocChamCong,
					nv.ten,
					cv.tenChucVu,
					pb.tenPhongBan
				FROM
					NhanVien nv
					JOIN ChucVu cv ON cv.maChucVu = nv.maChucVu
					JOIN PhongBan pb ON pb.maPhongBan = nv.maPhongBan
					JOIN BangChamCongNhanVien bcc ON bcc.maNhanVienDuocChamCong = nv.maNhanVien
				where month(ngayChamCong) = @thang and year(ngayChamCong)=@nam
			end
        else
			begin
				INSERT INTO @nhanVienCoTheTinhLuong (maNhanVien, tenNhanVien, tenChucVu, tenPhongBan)
				SELECT DISTINCT
					bcc.maNhanVienDuocChamCong,
					nv.ten,
					cv.tenChucVu,
					pb.tenPhongBan
				FROM
					NhanVien nv
					JOIN ChucVu cv ON cv.maChucVu = nv.maChucVu
					JOIN PhongBan pb ON pb.maPhongBan = nv.maPhongBan
					JOIN BangChamCongNhanVien bcc ON bcc.maNhanVienDuocChamCong = nv.maNhanVien
					where month(ngayChamCong) = @thang		
			end
    END
	else
		begin
			if(@nam is not null and @nam <> 0)
			begin
				INSERT INTO @nhanVienCoTheTinhLuong (maNhanVien, tenNhanVien, tenChucVu, tenPhongBan)
				SELECT DISTINCT
					bcc.maNhanVienDuocChamCong,
					nv.ten,
					cv.tenChucVu,
					pb.tenPhongBan
				FROM
					NhanVien nv
					JOIN ChucVu cv ON cv.maChucVu = nv.maChucVu
					JOIN PhongBan pb ON pb.maPhongBan = nv.maPhongBan
					JOIN BangChamCongNhanVien bcc ON bcc.maNhanVienDuocChamCong = nv.maNhanVien
				where year(ngayChamCong) = @thang
			end
			else
			begin
				INSERT INTO @nhanVienCoTheTinhLuong (maNhanVien, tenNhanVien, tenChucVu, tenPhongBan)
				SELECT DISTINCT
					bcc.maNhanVienDuocChamCong,
					nv.ten,
					cv.tenChucVu,
					pb.tenPhongBan
				FROM
					NhanVien nv
					JOIN ChucVu cv ON cv.maChucVu = nv.maChucVu
					JOIN PhongBan pb ON pb.maPhongBan = nv.maPhongBan
					JOIN BangChamCongNhanVien bcc ON bcc.maNhanVienDuocChamCong = nv.maNhanVien
			end
		end
    RETURN;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[layMaBangLuong]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE function [dbo].[layMaBangLuong]()
returns nvarchar(8)
AS
BEGIN
   declare @maBangLuong nvarchar(8) = (select max(maBangLuongCongNhan) from BangLuongCongNhan)
   if @maBangLuong is null
		set @maBangLuong = 'BLNV0001'
	else
		SET @maBangLuong = 'BLCN' + RIGHT('0000' + CAST(CAST(SUBSTRING(@maBangLuong, 5, LEN(@maBangLuong) - 4) AS INT) + 1 AS NVARCHAR(5)), 4);

	return @maBangLuong
   
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaBangLuongNhanVienTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaBangLuongNhanVienTiepTheo]()
RETURNS NVARCHAR(8)
AS
BEGIN
    DECLARE @nextId NVARCHAR(8);
    DECLARE @lastId NVARCHAR(8);

    -- Lấy mã nhân viên cuối cùng từ bảng NhanVien
    SELECT @lastId = MAX(maBangLuongNhanVien) FROM BangLuongNhanVien;

    IF @lastId IS NULL
        SET @nextId = 'BLNV0001';
    ELSE
    BEGIN
        SET @nextId = 'BLNV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 5, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaCongDoanTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaCongDoanTiepTheo](
	@maSanPham nvarchar(6)
)
RETURNS NVARCHAR(12)
AS
BEGIN
    DECLARE @nextId NVARCHAR(12);
    DECLARE @lastId NVARCHAR(12);

    -- Lấy mã  cuối cùng từ bảng NhanVien
    SELECT @lastId = MAX(maCongDoan) FROM BangPhanCongCongDoan where SUBSTRING(maCongDoan, 7,6) = @maSanPham

    -- Nếu không có mã nhân viên nào, trả về 'NV0001'
    IF @lastId IS NULL
        SET @nextId = CONCAT('CD0001', '', @maSanPham)
    ELSE
    BEGIN
        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả
        SET @nextId = 'CD' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 3, 4) AS INT) + 1 AS NVARCHAR(10)), 4)+ @maSanPham;
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaCongNhanTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaCongNhanTiepTheo]()
RETURNS NVARCHAR(6)
AS
BEGIN
    DECLARE @nextId NVARCHAR(6);
    DECLARE @lastId NVARCHAR(6);
    SELECT @lastId = MAX(maCongNhan) FROM CongNhan;
    IF @lastId IS NULL
        SET @nextId = 'CN0001';
    ELSE
    BEGIN
        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả
        SET @nextId = 'CN' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaNhanVienTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaNhanVienTiepTheo]()
RETURNS NVARCHAR(10)
AS
BEGIN
    DECLARE @nextId NVARCHAR(6);
    DECLARE @lastId NVARCHAR(6);

    -- Lấy mã nhân viên cuối cùng từ bảng NhanVien
    SELECT @lastId = MAX(maNhanVien) FROM NhanVien;

    -- Nếu không có mã nhân viên nào, trả về 'NV0001'
    IF @lastId IS NULL
        SET @nextId = 'NV0001';
    ELSE
    BEGIN
        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả
        SET @nextId = 'NV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaSanPhamTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaSanPhamTiepTheo]()
RETURNS NVARCHAR(6)
AS
BEGIN
    DECLARE @nextId NVARCHAR(6);
    DECLARE @lastId NVARCHAR(6);

    -- Lấy mã nhân viên cuối cùng từ bảng NhanVien
    SELECT @lastId = MAX(maSanPham) FROM SanPham;

    -- Nếu không có mã nhân viên nào, trả về 'NV0001'
    IF @lastId IS NULL
        SET @nextId = 'SP0001';
    ELSE
    BEGIN
        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả
        SET @nextId = 'SP' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[layMaTaiKhoanTiepTheo]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[layMaTaiKhoanTiepTheo]()
RETURNS NVARCHAR(10)
AS
BEGIN
    DECLARE @nextId NVARCHAR(6);
    DECLARE @lastId NVARCHAR(6);

    -- Lấy mã nhân viên cuối cùng từ bảng NhanVien
    SELECT @lastId = MAX([maTaiKhoan]) FROM taiKhoan;

    -- Nếu không có mã nhân viên nào, trả về 'NV0001'
    IF @lastId IS NULL
        SET @nextId = 'TK0001';
    ELSE
    BEGIN
        -- Tăng mã nhân viên cuối cùng lên 1 đơn vị và định dạng lại chuỗi kết quả
        SET @nextId = 'TK' + RIGHT('0000' + CAST(CAST(SUBSTRING(@lastId, 3, LEN(@lastId) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
    END

    RETURN @nextId;
END
GO
/****** Object:  UserDefinedFunction [dbo].[taoBangLuongNhanVien]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[taoBangLuongNhanVien](
	@maNhanVien nvarchar(6),
	@maBangLuong nvarchar(8),
	@thang int, 
	@nam int
)returns @bangLuongNhanVien table(
	maBangLuongNhanVien nvarchar(8),
	ngayLap date,
	soNgayDiLam tinyint,
	soNgayLamCaNgay tinyint,
	soNgayLamNuaNgay tinyint,
	soNgayVangCoPhep tinyint,
	soNgayVangKhongPhep tinyint,
	soNgayTre tinyint,
	soGioTangCa tinyint default 0, 
	maNhanVien nvarchar(6)
)
as 
begin
	Declare @ngayLap date
	set @ngayLap = getDate()
	declare @soNgayLamToanThoiGian int
	set @soNgayLamToanThoiGian = (select count(*) from BangChamCongNhanVien where caLam = N'Full-time' and (trangThai = N'Đi làm' or trangThai = N'Đi trễ') and maNhanVienDuocChamCong = @maNhanVien and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)
	declare @soNgayLamNuaThoiGian int
	set @soNgayLamNuaThoiGian = (select count(*) from BangChamCongNhanVien where caLam = N'Part-time' and (trangThai = N'Đi làm' or trangThai = N'Đi trễ') and maNhanVienDuocChamCong = @maNhanVien and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)
	--tổng số ngày đi làm
	declare @tongSoNgayDiLam int
	set @tongSoNgayDiLam = @soNgayLamNuaThoiGian/2 + @soNgayLamToanThoiGian
	if(@tongSoNgayDiLam = 0)
	begin
		return
	end
	
	declare @soNgayVangCoPhep int 
	set @soNgayVangCoPhep = (select count(*) from BangChamCongNhanVien 
							where (caLam = N'Part-time' or caLam = N'Full-time') and (trangThai = N'Nghỉ có phép') 
							and maNhanVienDuocChamCong = @maNhanVien
							and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)
	
	declare @soNgayVangKhongPhep int 
	set @soNgayVangKhongPhep = (select count(*) from BangChamCongNhanVien where (caLam = N'Part-time' or caLam = N'Full-time') 
								and (trangThai = N'Nghỉ không phép')
								and maNhanVienDuocChamCong = @maNhanVien
								and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)

	declare @soNgayTre int
	set @soNgayTre = (select count(*) from BangChamCongNhanVien where (caLam = N'Part-time' or caLam = N'Full-time') and (trangThai = N'Đi trễ') and maNhanVienDuocChamCong = @maNhanVien)

	declare @soGioTangCa int
	set @soGioTangCa = (select sum(soGio) from BangChamCongNhanVien where
						maNhanVienDuocChamCong = @maNhanVien
						and month(ngayChamCong) = @thang and year(ngayChamCong) = @nam)
	if(@soGioTangCa is null)
		set @soGioTangCa = 0

	insert @bangLuongNhanVien
	values(@maBangLuong, @ngayLap, @tongSoNgayDiLam, @soNgayLamToanThoiGian,@soNgayLamNuaThoiGian, @soNgayVangCoPhep, @soNgayVangKhongPhep, @soNgayTre,@soGioTangCa, @maNhanVien)
	return
end
GO
/****** Object:  UserDefinedFunction [dbo].[thongKeTheoThoiGian]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[thongKeTheoThoiGian](
	@tuThang int,
	@tuNam int,
	@denThang int,
	@denNam int
)RETURNS @ResultTable TABLE
(
    LuongThucNhan INT,
    NgayLam INT,
    LuongCoBan INT,
    HeSoLuong FLOAT,
    LuongTruocThue INT,
    KhauTruBaoHiem INT,
    MaNhanVien NVARCHAR(8)
)
as
begin
	while @tuNam <= @denNam
	begin
		if @tuNam = @denNam
		begin
			while @tuThang <= @denThang
			begin
				insert @ResultTable
				select * from [dbo].[danhSachThongKe](@tuThang, @tuNam)
				set @tuThang = @tuThang + 1
			end
		end
		else
			begin
				while @tuThang <= 12
				begin
					insert @ResultTable
					select * from [dbo].[danhSachThongKe](@tuThang, @tuNam)
					set @tuThang = @tuThang + 1
				end
			end

			set @tuNam = @tuNam + 1
			set @tuThang = 1 -- Reset month to 1 for the next year
	end
	return
end
GO
/****** Object:  UserDefinedFunction [dbo].[tinhLuongToanBoNhanVienTheoThangVaNam]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[tinhLuongToanBoNhanVienTheoThangVaNam](
	@thang int,
	@nam int
)returns @bangLuongNhanVien table(
	maBangLuongNhanVien nvarchar(8),
	ngayLap date,
	soNgayDiLam tinyint,
	soNgayLamCaNgay tinyint,
	soNgayLamNuaNgay tinyint,
	soNgayVangCoPhep tinyint,
	soNgayVangKhongPhep tinyint,
	soNgayTre tinyint,
	soGioTangCa tinyint default 0,
	maNhanVien nvarchar(6))
as
begin
	DECLARE @maNhanVien NVARCHAR(8) = 'NV0001';
	declare @maBangLuong nvarchar(8)
	set @maBangLuong = (select max(maBangLuongNhanVien) from BangLuongNhanVien)
	if(@maBangLuong is null)
	begin
		set @maBangLuong = 'BLNV0000'
	end
	WHILE @maNhanVien <= (select max(maNhanVien) from NhanVien)
	BEGIN
		set @maBangLuong = 'BLNV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@maBangLuong, 5, LEN(@maBangLuong) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
		insert @bangLuongNhanVien
		select * from [taoBangLuongNhanVien](@maNhanVien,@maBangLuong, @thang, @nam)
		-- Tăng giá trị của mã nhân viên
		SET @maNhanVien = 'NV' + RIGHT('0000' + CAST(CAST(SUBSTRING(@maNhanVien, 3, LEN(@maNhanVien) - 2) AS INT) + 1 AS NVARCHAR(10)), 4);
	END;
	return
end
GO
/****** Object:  Table [dbo].[BangChamCongCongNhan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongCongNhan](
	[maBangChamCongCongNhan] [nvarchar](8) NOT NULL,
	[trangThai] [int] NOT NULL,
	[ngayChamCong] [date] NOT NULL,
	[soLuongHoanThanh] [tinyint] NOT NULL,
	[calam] [nvarchar](20) NOT NULL,
	[maCongNhan] [nvarchar](8) NOT NULL,
	[maNhanVienChamCong] [nvarchar](8) NOT NULL,
	[maCongDoan] [nvarchar](12) NULL,
PRIMARY KEY CLUSTERED 
(
	[maBangChamCongCongNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongDoan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongDoan](
	[maCongDoan] [nvarchar](12) NOT NULL,
	[soCongNhan] [tinyint] NULL,
	[soLuongThanhPhan] [tinyint] NULL,
	[luongTrenMotCongDoan] [int] NOT NULL,
	[maSanPham] [nvarchar](8) NOT NULL,
	[loaiCongDoan] [nvarchar](50) NULL,
	[ngayChiaCongDoan] [date] NULL,
	[trangThai] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maCongDoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongNhan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongNhan](
	[maCongNhan] [nvarchar](8) NOT NULL,
	[ho] [nvarchar](255) NULL,
	[ten] [nvarchar](255) NULL,
	[soDienThoai] [nvarchar](11) NOT NULL,
	[ngaySinh] [date] NULL,
	[ngayVaoLam] [date] NULL,
	[CCCD] [nvarchar](11) NOT NULL,
	[phuCap] [int] NOT NULL,
	[gioiTinh] [nvarchar](8) NOT NULL,
	[maChuyenMon] [nvarchar](6) NOT NULL,
	[trangThai] [int] NULL,
	[maDiaChi] [int] NULL,
	[anh] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maCongNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [nvarchar](8) NOT NULL,
	[giaThanh] [money] NOT NULL,
	[tenSanPham] [nvarchar](50) NOT NULL,
	[chatGo] [nvarchar](50) NOT NULL,
	[anh] [nvarchar](255) NULL,
	[kichThuoc] [nvarchar](255) NULL,
	[thoiGianSanXuatDuKien] [date] NULL,
	[soLuongSanPham] [int] NULL,
	[trangThaiSanPham] [int] NULL,
	[khoiLuong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChuyenMon]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenMon](
	[maChuyenMon] [nvarchar](6) NOT NULL,
	[tenChuyenMon] [nvarchar](50) NOT NULL,
	[heSoLuongTheoChuyenMon] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maChuyenMon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  UserDefinedFunction [dbo].[dsCanTinhLuong]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[dsCanTinhLuong](
	--@maCongNhan NVarchar(6),
	@thang int)
RETURNS TABLE
AS
RETURN
(
    select cd.maSanPham, sp.tenSanPham,bcc.maCongDoan, bcc.maCongNhan, cn.ho+' '+cn.ten as tenCongNhan, tenChuyenMon, cd.luongTrenMotCongDoan,calam, SUM(soLuongHoanThanh) as tongSoLuong, tongTien = SUM(soLuongHoanThanh)*cd.luongTrenMotCongDoan*heSoLuongTheoChuyenMon*(case
	when caLam = N'Ca Ba' then 1.5
	else 1
end)
from [dbo].[BangChamCongCongNhan] bcc 
join CongNhan cn on cn.maCongnhan = bcc.maCongNhan 
join ChuyenMon cm on cm.maChuyenMon = cn.maChuyenMon
join CongDoan cd on cd.maCongDoan = bcc.maCongDoan
join SanPham sp on sp.maSanPham = cd.maSanPham
where  month(ngayChamCong) =@thang and soLuongHoanThanh > 0
group by  cd.maSanPham, tenSanPham, bcc.maCongDoan, bcc.maCongNhan, cn.ho+' '+cn.ten, tenChuyenMon, caLam, luongTrenMotCongDoan, heSoLuongTheoChuyenMon, cn.phuCap
);
GO
/****** Object:  UserDefinedFunction [dbo].[dsChamCongTinhLuongTheoMa]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[dsChamCongTinhLuongTheoMa](
	@maCongNhan NVarchar(6),
	@thang int)
RETURNS TABLE
AS
RETURN
(
     select cd.maSanPham, sp.tenSanPham,bcc.maCongDoan, cd.loaiCongDoan,  calam, SUM(soLuongHoanThanh) as tongSoLuong, tongTien = SUM(soLuongHoanThanh)*cd.luongTrenMotCongDoan*heSoLuongTheoChuyenMon*(case
	when caLam = N'Ca Ba' then 1.5
	else 1
end)
from [dbo].[BangChamCongCongNhan] bcc 
join CongNhan cn on cn.maCongnhan = bcc.maCongNhan 
join ChuyenMon cm on cm.maChuyenMon = cn.maChuyenMon
join CongDoan cd on cd.maCongDoan = bcc.maCongDoan
join SanPham sp on sp.maSanPham = cd.maSanPham
where cn.maCongNhan = @maCongNhan and  month(ngayChamCong) = @thang and soLuongHoanThanh > 0
group by  cd.maSanPham, tenSanPham, bcc.maCongDoan, bcc.maCongNhan, cn.ho+' '+cn.ten, tenChuyenMon, caLam, luongTrenMotCongDoan, heSoLuongTheoChuyenMon, cn.phuCap, cd.loaiCongDoan
);
GO
/****** Object:  Table [dbo].[BangChamCongNhanVien]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangChamCongNhanVien](
	[maBangChamCongNhanVien] [nvarchar](8) NOT NULL,
	[trangThai] [nvarchar](20) NOT NULL,
	[ngayChamCong] [date] NOT NULL,
	[ghiChu] [nvarchar](255) NULL,
	[caLam] [nvarchar](20) NOT NULL,
	[maNhanVienDuocChamCong] [nvarchar](8) NOT NULL,
	[maNhanVienChamCong] [nvarchar](8) NOT NULL,
	[soGio] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maBangChamCongNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangLuongCongNhan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangLuongCongNhan](
	[maBangLuongCongNhan] [nvarchar](14) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[maCongNhan] [nvarchar](8) NOT NULL,
	[luongCongNhanTheoSanPham] [int] NOT NULL,
	[luongThucCuaCongNhan] [int] NOT NULL,
	[tongSoCaLam] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maBangLuongCongNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangLuongNhanVien]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangLuongNhanVien](
	[maBangLuongNhanVien] [nvarchar](8) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[soNgayDiLam] [tinyint] NOT NULL,
	[soNgayVangCoPhep] [tinyint] NOT NULL,
	[soNgayVangKhongPhep] [tinyint] NOT NULL,
	[soNgayTre] [tinyint] NOT NULL,
	[maNhanVien] [nvarchar](8) NOT NULL,
	[soGioTangCa] [int] NULL,
	[soNgayLamCaNgay] [tinyint] NULL,
	[soNgayLamNuaNgay] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[maBangLuongNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BangPhanCongCongDoan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangPhanCongCongDoan](
	[soLuong] [tinyint] NOT NULL,
	[maCongDoan] [nvarchar](12) NOT NULL,
	[maCongNhan] [nvarchar](8) NOT NULL,
	[ngayPhanCong] [date] NULL,
	[soLuongConLai] [int] NULL,
 CONSTRAINT [PK_BangPhanCongCongDoan] PRIMARY KEY CLUSTERED 
(
	[maCongDoan] ASC,
	[maCongNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[maChucVu] [nvarchar](8) NOT NULL,
	[tenChucVu] [nvarchar](255) NULL,
	[luongCungTheoChucVu] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiaChi]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiaChi](
	[maDiaChi] [int] IDENTITY(1,1) NOT NULL,
	[tenDuong] [nvarchar](255) NULL,
	[tenTinhThanh] [nvarchar](50) NULL,
	[tenQuanHuyen] [nvarchar](50) NULL,
	[tenThiXa] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDiaChi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](8) NOT NULL,
	[ho] [nvarchar](50) NOT NULL,
	[ten] [nvarchar](50) NOT NULL,
	[soDienThoai] [nvarchar](50) NOT NULL,
	[email] [nvarchar](255) NULL,
	[ngayVaoLam] [date] NULL,
	[CCCD] [nvarchar](11) NOT NULL,
	[thamNien] [float] NOT NULL,
	[heSoLuong] [float] NOT NULL,
	[phuCap] [int] NOT NULL,
	[gioiTinh] [nvarchar](8) NOT NULL,
	[maChucVu] [nvarchar](8) NULL,
	[maTaiKhoan] [nvarchar](6) NULL,
	[BHYT] [nvarchar](8) NOT NULL,
	[BHXH] [nvarchar](8) NOT NULL,
	[maPhongBan] [nvarchar](8) NOT NULL,
	[anh] [nvarchar](255) NOT NULL,
	[maDiaChi] [int] NULL,
	[ngaySinh] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBan](
	[maPhongBan] [nvarchar](8) NOT NULL,
	[tenPhongBan] [nvarchar](50) NOT NULL,
	[soLuongNhanVien] [int] NOT NULL,
	[ngayThanhLap] [date] NOT NULL,
	[soDienThoaiLienLac] [nvarchar](11) NOT NULL,
	[moTa] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[maPhongBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phuong]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phuong](
	[phuong_id] [int] NOT NULL,
	[quanHuyen_id] [int] NOT NULL,
	[ten] [nvarchar](64) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[phuong_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuanHuyen]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuanHuyen](
	[quanHuyen_id] [int] NOT NULL,
	[tinh_id] [int] NOT NULL,
	[ten] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[quanHuyen_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[taiKhoan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[taiKhoan](
	[maTaiKhoan] [nvarchar](6) NOT NULL,
	[tenTaiKhoan] [nvarchar](50) NOT NULL,
	[matKhau] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TinhThanh]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TinhThanh](
	[tinh_id] [int] NOT NULL,
	[ten] [nvarchar](64) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tinh_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BangChamCongNhanVien] ADD  DEFAULT (NULL) FOR [soGio]
GO
ALTER TABLE [dbo].[BangLuongNhanVien] ADD  DEFAULT (NULL) FOR [soGioTangCa]
GO
ALTER TABLE [dbo].[BangLuongNhanVien] ADD  DEFAULT ((0)) FOR [soNgayLamCaNgay]
GO
ALTER TABLE [dbo].[BangLuongNhanVien] ADD  DEFAULT ((0)) FOR [soNgayLamNuaNgay]
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan] ADD  DEFAULT (getdate()) FOR [ngayPhanCong]
GO
ALTER TABLE [dbo].[CongDoan] ADD  CONSTRAINT [DF_ngayChiaCongDoan]  DEFAULT (getdate()) FOR [ngayChiaCongDoan]
GO
ALTER TABLE [dbo].[CongDoan] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_NgayVaoLam]  DEFAULT (getdate()) FOR [ngayVaoLam]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [df_TaiKhoan]  DEFAULT (NULL) FOR [maTaiKhoan]
GO
ALTER TABLE [dbo].[PhongBan] ADD  DEFAULT (getdate()) FOR [ngayThanhLap]
GO
ALTER TABLE [dbo].[PhongBan] ADD  CONSTRAINT [df_MoTa]  DEFAULT ('') FOR [moTa]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [soLuongSanPham]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT ((0)) FOR [trangThaiSanPham]
GO
ALTER TABLE [dbo].[BangChamCongCongNhan]  WITH CHECK ADD FOREIGN KEY([maCongDoan])
REFERENCES [dbo].[CongDoan] ([maCongDoan])
GO
ALTER TABLE [dbo].[BangChamCongCongNhan]  WITH CHECK ADD FOREIGN KEY([maCongNhan])
REFERENCES [dbo].[CongNhan] ([maCongNhan])
GO
ALTER TABLE [dbo].[BangChamCongCongNhan]  WITH CHECK ADD FOREIGN KEY([maNhanVienChamCong])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[BangChamCongNhanVien]  WITH CHECK ADD FOREIGN KEY([maNhanVienDuocChamCong])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[BangChamCongNhanVien]  WITH CHECK ADD FOREIGN KEY([maNhanVienChamCong])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[BangLuongCongNhan]  WITH CHECK ADD FOREIGN KEY([maCongNhan])
REFERENCES [dbo].[CongNhan] ([maCongNhan])
GO
ALTER TABLE [dbo].[BangLuongNhanVien]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan]  WITH CHECK ADD FOREIGN KEY([maCongDoan])
REFERENCES [dbo].[CongDoan] ([maCongDoan])
GO
ALTER TABLE [dbo].[BangPhanCongCongDoan]  WITH CHECK ADD FOREIGN KEY([maCongNhan])
REFERENCES [dbo].[CongNhan] ([maCongNhan])
GO
ALTER TABLE [dbo].[CongDoan]  WITH CHECK ADD FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD FOREIGN KEY([maChuyenMon])
REFERENCES [dbo].[ChuyenMon] ([maChuyenMon])
GO
ALTER TABLE [dbo].[CongNhan]  WITH CHECK ADD FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maChucVu])
REFERENCES [dbo].[ChucVu] ([maChucVu])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maDiaChi])
REFERENCES [dbo].[DiaChi] ([maDiaChi])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maPhongBan])
REFERENCES [dbo].[PhongBan] ([maPhongBan])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[taiKhoan] ([maTaiKhoan])
GO
ALTER TABLE [dbo].[Phuong]  WITH CHECK ADD FOREIGN KEY([quanHuyen_id])
REFERENCES [dbo].[QuanHuyen] ([quanHuyen_id])
GO
ALTER TABLE [dbo].[QuanHuyen]  WITH CHECK ADD FOREIGN KEY([tinh_id])
REFERENCES [dbo].[TinhThanh] ([tinh_id])
GO
ALTER TABLE [dbo].[taiKhoan]  WITH CHECK ADD  CONSTRAINT [kt_doDai] CHECK  ((len([matKhau])>=(4)))
GO
ALTER TABLE [dbo].[taiKhoan] CHECK CONSTRAINT [kt_doDai]
GO
/****** Object:  StoredProcedure [dbo].[capNhatThongTinCongNhan]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[capNhatThongTinCongNhan]
    @maCongNhan nvarchar(6),
	@ho nvarchar(50),
	@ten nvarchar(50),
	@soDienThoai nvarchar(11),
	@ngaySinh date,
	@ngayVaoLam date,
	@CCCD nvarchar(11),
	@phuCap int,
	@gioiTinh nvarchar(8),
	@maChuyenMon nvarchar(6),
	@trangThai int,
	@maDiaChi int,
	@tenDuong nvarchar(255),
	@tenTinhThanh nvarchar(50),
	@tenQuanHuyen nvarchar(50),
	@tenThiXa nvarchar(50),
	@anh nvarchar(255)
AS
BEGIN
    update CongNhan
	set ho = @ho,
	ten = @ten,
	soDienThoai = @soDienThoai,
	ngaySinh = @ngaySinh,
	ngayVaoLam = @ngayVaoLam,
	CCCD = @CCCD,
	phuCap = @phuCap,
	gioiTinh = @gioiTinh,
	maChuyenMon = @maChuyenMon,
	trangThai = @trangThai,
	maDiaChi = @maDiaChi,
	anh = @anh
	where maCongNhan = @maCongNhan

	update DiaChi
	set tenDuong = @tenDuong,
	tenTinhThanh = @tenTinhThanh,
	tenQuanHuyen = @tenQuanHuyen,
	tenThiXa = @tenThiXa
	where maDiaChi = @maDiaChi
END
GO
/****** Object:  StoredProcedure [dbo].[capNhatThongTinNhanVien]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[capNhatThongTinNhanVien]
    @maNhanVien nvarchar(8),
	@ho nvarchar(50),
	@ten nvarchar(50),
	@soDienThoai nvarchar(11),
	@email nvarchar(255),
	@ngayVaoLam date,
	@CCCD nvarchar(11),
	@thamNien float,
	@heSoLuong float,
	@phuCap int,
	@gioiTinh nvarchar(8),
	@maChucVu nvarchar(8),
	@BHYT nvarchar(8),
	@BHXH nvarchar(8),
	@maPhongBan nvarchar(8),
	@anh nvarchar(255),
	@maDiaChi int,
	@tenDuong nvarchar(255),
	@tenTinhThanh nvarchar(50),
	@tenQuanHuyen nvarchar(50),
	@tenThiXa nvarchar(50),
	@ngaySinh date
AS
BEGIN
    update NhanVien
	set ho = @ho,
	ten = @ten,
	soDienThoai = @soDienThoai,
	email = @email,
	ngayVaoLam = @ngayVaoLam,
	CCCD = @CCCD,
	thamNien = @thamNien,
	heSoLuong = @heSoLuong,
	phuCap = @phuCap,
	gioiTinh = @gioiTinh,
	maChucVu = @maChucVu,
	BHYT = @BHYT, 
	BHXH = @BHXH,
	maPhongBan = @maPhongBan,
	ngaySinh = @ngaySinh,
	anh = @anh
	where maNhanVien = @maNhanVien

	update DiaChi
	set tenDuong = @tenDuong,
	tenTinhThanh = @tenTinhThanh,
	tenQuanHuyen = @tenQuanHuyen,
	tenThiXa = @tenThiXa
	where maDiaChi = @maDiaChi
END
GO
/****** Object:  StoredProcedure [dbo].[chenVaLayDuLieuDiaChi]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[chenVaLayDuLieuDiaChi]
    @tenDuong NVARCHAR(255),
    @tenTinhThanh NVARCHAR(50),
    @tenQuanHuyen NVARCHAR(50),
    @tenThiXa NVARCHAR(50),
    @maDiaChi INT OUTPUT, -- Tham số đầu ra cho cột maDiaChi
    @tenDuongOutput NVARCHAR(255) OUTPUT, -- Tham số đầu ra cho cột tenDuong
    @tenTinhThanhOutput NVARCHAR(50) OUTPUT, -- Tham số đầu ra cho cột tenTinhThanh
    @tenQuanHuyenOutput NVARCHAR(50) OUTPUT, -- Tham số đầu ra cho cột tenQuanHuyen
	@tenThiXaOutput NVARCHAR(50) out
AS
BEGIN
   insert DiaChi([tenDuong], [tenTinhThanh], [tenQuanHuyen], [tenThiXa]) values (@tenDuong, @tenTinhThanh, @tenQuanHuyen, @tenThiXa)
   select Top 1 @maDiaChi = maDiaChi, @tenDuongOutput = [tenDuong],
				@tenTinhThanhOutput = [tenTinhThanh], @tenQuanHuyenOutput = [tenQuanHuyen],
				@tenThiXaOutput = [tenThiXa]
	from DiaChi order by maDiaChi desc
END;
GO
/****** Object:  StoredProcedure [dbo].[layPhuongDuaTrenThanhPhoVaTinh]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[layPhuongDuaTrenThanhPhoVaTinh]
	@tenQuanHuyen nvarchar(50),
	@tenThanhPho nvarchar(50)
as
begin
	select p.ten from Phuong p 
	join QuanHuyen qh on qh.quanHuyen_id=p.quanHuyen_id 
	join TinhThanh t on t.tinh_id = qh.tinh_id
	where TRIM(qh.ten) = @tenQuanHuyen and TRIM(t.ten) = @tenThanhPho
end
GO
/****** Object:  StoredProcedure [dbo].[layQuanHuyenTheoTinhThanhPho]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layQuanHuyenTheoTinhThanhPho]
    @tinhThanhPho nvarchar(64)
AS
BEGIN
    select h.ten from QuanHuyen h join TinhThanh t on t.tinh_id = h.tinh_id where trim(t.ten) = @tinhThanhPho
END;
GO
/****** Object:  StoredProcedure [dbo].[layTongSoLuongPhanCong]    Script Date: 12/14/2023 12:25:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[layTongSoLuongPhanCong]
    @maCongDoan nvarchar(12),
    @result int OUTPUT
AS 
BEGIN
    DECLARE @soLuongConLai int
    SET @soLuongConLai = (SELECT sum(soLuong) FROM BangPhanCongCongDoan where maCongDoan = @maCongDoan)

    DECLARE @soLuongDaPhanCong int
    SET @soLuongDaPhanCong = (
        SELECT SUM(soLuongHoanThanh)
        FROM BangChamCongCongNhan bcccn
        JOIN CongNhan cn ON bcccn.maCongNhan = cn.maCongNhan
        JOIN BangPhanCongCongDoan bcccd ON bcccd.maCongNhan = cn.maCongNhan
        WHERE maCongDoan = @maCongDoan
        GROUP BY maCongDoan
    )

    SET @result = @soLuongConLai + @soLuongDaPhanCong
END;
GO
USE [master]
GO
ALTER DATABASE [QuanLyLuongSanPham] SET  READ_WRITE 
GO
