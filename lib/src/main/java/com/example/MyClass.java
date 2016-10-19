package com.example;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass {
    public static void main(String[] args) {
        String sql = "insert into q_content(content,locationx,locationy,parentid) values(?,?,?,?);";
        PreparedStatement ps = null;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = connect();
            ps = conn.prepareStatement(sql);

            InputStream file = new FileInputStream(new File("F:\\kaoshi.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int length = workbook.getNumberOfSheets();
            for (int a = 0; a < length; a++) {
                XSSFSheet sheet = workbook.getSheetAt(a);
                System.out.println(sheet.getSheetName());
                insertType(conn, a, sheet.getSheetName());
                //Get the number of sheets in the xlsx file
                Row row;
                String cell;
                for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
                    // 循环行数
                    row = sheet.getRow(i);
                    if (row == null) continue;
                    for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                        // 循环列数
                        if (j < 0) continue;
                        if (row.getCell(j) == null) continue;
                        cell = row.getCell(j).toString();

                        System.out.println(cell);
                        ps.setString(1, cell);
                        ps.setInt(2, i);
                        ps.setInt(3, j);
                        ps.setInt(4, a);
                        ps.addBatch();
                    }
                }
            }
            conn.setAutoCommit(false);
            ps.executeBatch();
            conn.setAutoCommit(true);
            conn.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:F:\\sqlite\\question.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stat = conn.createStatement();
            stat.executeUpdate("drop table if exists q_type;create table q_type (id integer,name varchar(20));");//创建一个表，两列
            stat.executeUpdate("drop table if exists q_content;create table q_content (id INTEGER PRIMARY KEY NOT NULL,content varchar(20),locationx integer,locationy integer,parentid integer);");//创建一个表，两列
            stat.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    private static void insertType(Connection conn, int id, String name) {
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate("insert into q_type(id,name) values(" + id + ",'" + name + "');"); //插入数据
            stat.close(); //结束数据库的连接
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取Excel2007图片
     *
     * @param sheetNum 当前sheet编号
     * @param sheet    当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getSheetPictrues07(int sheetNum,
                                                              XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();

        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picIndex = String.valueOf(sheetNum) + "_"
                            + ctMarker.getRow() + "_" + ctMarker.getCol();
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }

        return sheetIndexPicMap;
    }
}
