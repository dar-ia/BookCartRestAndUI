package models.bookstoremodels;

import lombok.Data;

@Data
public class AddBooksResponse {
    IsbnModel[] books;
}
