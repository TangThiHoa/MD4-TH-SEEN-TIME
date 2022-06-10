package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
    @GetMapping("/world-clock")        //ánh xạ request lên phương thức xử lí(hiển thị ở đường dẫn)
    public String getTimeByTimezone(ModelMap model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
//        required : yêu cầu
//khai báo tham số của phương thức xử lý, ràng buộc với tham số từ request
        Date date = new Date();                                    // Lấy ra time zone hiện tại
        TimeZone local = TimeZone.getDefault();                    // Lấy ra time zone của 1 thành phố cụ thể
        TimeZone locale = TimeZone.getTimeZone(city);
        long locale_time = date.getTime() +
                (locale.getRawOffset() - local.getRawOffset());
        date.setTime(locale_time);                                // Cài đặt lại thời gian cho biến date thành thời gian hiện tại của 1 thành phố cụ thể
//chuyển dữ liệu qua view
        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "index";
    }}
