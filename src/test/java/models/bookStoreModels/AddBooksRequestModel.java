package models.bookStoreModels;

import lombok.Data;

@Data
public class AddBooksRequestModel {
    String userId;
    IsbnModel[] collectionOfIsbns;
}
