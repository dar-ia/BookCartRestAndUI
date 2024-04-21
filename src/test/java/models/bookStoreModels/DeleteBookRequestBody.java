package models.bookStoreModels;

import lombok.Data;

@Data
public class DeleteBookRequestBody {
    String userId,
    isbn;
}
