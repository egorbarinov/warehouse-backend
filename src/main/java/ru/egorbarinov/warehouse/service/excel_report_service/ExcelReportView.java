package ru.egorbarinov.warehouse.service.excel_report_service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ru.egorbarinov.warehouse.dto.DeliveryDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {

    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) {

        response.setHeader("Content-Disposition", "attachment;filename=\"report.xls\"");
        List<DeliveryDto> deliveryDtos = (List<DeliveryDto>) model.get("reportDeliveries");
        String[] headers =(String[]) model.get("reportHeaders");

        CellStyle style = workbook.createCellStyle();
        CellStyle dateCell = workbook.createCellStyle(); // стиль для даты

        CreationHelper createHelper = workbook.getCreationHelper();
        dateCell.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyyy"));

        Sheet sheet = workbook.createSheet("Delivery");

        // Задаем область печати
        //set print area with indexes
        workbook.setPrintArea(
                0, //sheet index
                0, //start column
                12, //end column
                0, //start row
                40 //end row
        );

        //set paper size
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.setAutobreaks(true);
        //set display grid lines or not
        sheet.setDisplayGridlines(true);
        //set print grid lines or not
        sheet.setPrintGridlines(true);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        dateCell.setBorderTop(BorderStyle.THIN);
        dateCell.setBorderRight(BorderStyle.THIN);
        dateCell.setBorderBottom(BorderStyle.THIN);
        dateCell.setBorderLeft(BorderStyle.THIN);

        // Определение цвета граничных значений стиля
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        dateCell.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dateCell.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dateCell.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dateCell.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        dateCell.setWrapText(true);
        dateCell.setAlignment(HorizontalAlignment.CENTER);
        dateCell.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.getIndex());

        int width = (int) (14 * 1.14388) * 256;
//        sheet.autoSizeColumn(0);

        Row row = sheet.createRow((short) 0); // задаем номер исходной строки
        Cell cell;

//        row.setHeightInPoints(30.0f);
        for(int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            switch (headers[i]) {
                case "No" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("№ п/п");
                    break;
                case "id" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("id");
                    break;
                case "deliveryDate" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Дата поставки");
                    break;
                case "deliveryTime" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Время поставки");
                    break;
                case "carInfo" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Данные на машину");
                    break;
                case "driverInfo" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Данные на водителя");
                    break;
                case "brand" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Brand");
                    break;
                case "orderNumber" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Номер заказа");
                    break;
                case "deliveryType" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Тип поставки");
                    break;
                case "sender" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Поставщик");
                    break;
                case "comment" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Комментарии");
                    break;
                case "shop" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Shop");
                    break;
                case "numberOfPlaces" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Количество мест");
                    break;
                case "torgNumber" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Торг-12");
                    break;
                case "invoice" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Счет-фактура");
                    break;

                case "user" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Пользователь системы");
                    break;
                case "warehouse" :
                    sheet.setColumnWidth(i, width);
                    cell.setCellValue("Склад");
                    break;

            }
            sheet.setColumnWidth(i, width);
        }

        int rowNum = 0;
        for(DeliveryDto deliveryDto : deliveryDtos) {
            row = sheet.createRow((short)++rowNum);
            for (int i = 0; i < headers.length; ++i) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                switch (headers[i]) {
                    case "No":
                        cell.setCellValue(rowNum);
                        break;
                    case "id":
                        cell.setCellValue(deliveryDto.getId());
                        break;
                    case "deliveryDate":
                        cell.setCellStyle(dateCell);
                        cell.setCellValue(deliveryDto.getDeliveryDate());
                        break;
                    case "deliveryTime":
                        cell.setCellValue(deliveryDto.getDeliveryTime().getDeliveryTime());
                        break;
                    case "carInfo":
                        cell.setCellValue(deliveryDto.getCarInfo());
                        break;
                    case "driverInfo":
                        cell.setCellValue(deliveryDto.getDriverInfo());
                        break;
                    case "brand":
                        cell.setCellValue(deliveryDto.getBrand().getName());
                        break;
                    case "orderNumber":
                        cell.setCellValue(deliveryDto.getOrderNumber());
                        break;
                    case "deliveryType":
                        cell.setCellValue(deliveryDto.getDeliveryType().getType());
                        break;
                    case "sender":
                        cell.setCellValue(deliveryDto.getSender());
                        break;
                    case "comment":
                        cell.setCellValue(deliveryDto.getComment());
                        break;
                    case "shop":
                        cell.setCellValue(deliveryDto.getShop().getName());
                        break;
                    case "numberOfPlaces":
                        cell.setCellValue(deliveryDto.getNumberOfPlaces());
                        break;
                    case "torgNumber":
                        cell.setCellValue(deliveryDto.getTorgNumber());
                        break;
                    case "invoice":
                        cell.setCellValue(deliveryDto.getInvoice());
                        break;
                    case "user" :
                        cell.setCellValue(deliveryDto.getUser().getUsername());
                        break;
                    case "warehouse" :
                        sheet.setColumnWidth(i, width);
                        cell.setCellValue(deliveryDto.getWarehouse().getName());
                        break;

                }
            }
        }
    }
}
