package models.bookstoremodels;

import lombok.Data;

@Data
public class DeleteBookRequestBody {
    String userId,
            isbn;
}
