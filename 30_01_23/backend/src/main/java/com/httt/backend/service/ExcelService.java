package com.httt.backend.service;

import com.httt.backend.dto.UserExport;
import com.httt.backend.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ExcelService {
    List<User> readUserFromExcelFile(String excelFilePath) throws IOException;

    void writeUserToExcelFile(ArrayList<UserExport> userExports) throws IOException;
    void InsertUserToDB(List<User> userList);
}
