package models.bookStoreModels;

import lombok.Data;

@Data
public class ListBooksResponseModel {
    BookResponseModel[] books;
}
