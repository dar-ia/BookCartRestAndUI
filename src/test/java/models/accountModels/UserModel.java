package models.accountModels;

import lombok.Data;
import models.bookStoreModels.BookResponseModel;

@Data
public class UserModel {
    String userId,
    username;
    BookResponseModel[] books;
}
