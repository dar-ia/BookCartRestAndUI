package models.bookStoreModels;

import lombok.Data;

@Data
public class AddBooksResponse {
    IsbnModel[] books;
}
