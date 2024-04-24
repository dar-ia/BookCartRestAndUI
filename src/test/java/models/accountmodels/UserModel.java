package models.accountmodels;

import lombok.Data;
import models.bookstoremodels.BookResponseModel;

@Data
public class UserModel {
    String userId,
            username;
    BookResponseModel[] books;
}
