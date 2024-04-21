package models.bookStoreModels;

import lombok.Data;

@Data
public class BookResponseModel {
    String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher,
            pages,
            description,
            website;
}
