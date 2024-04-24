package models.bookstoremodels;

import lombok.Data;

@Data
public class AddBooksRequestModel {
    String userId;
    IsbnModel[] collectionOfIsbns;
}
