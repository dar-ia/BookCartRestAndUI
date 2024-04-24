package models.bookstoremodels;

import lombok.Data;

@Data
public class ListBooksResponseModel {
    BookResponseModel[] books;
}
