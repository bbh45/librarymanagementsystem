package com.bb.libraryManagementSystem.request;

import com.bb.libraryManagementSystem.model.Author;
import com.bb.libraryManagementSystem.model.Book;
import com.bb.libraryManagementSystem.model.Genre;
import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;
    @NotNull
    private Author author;
    @Positive
    private int cost;
    @NotNull
    private Genre genre;

    public Book to(){
        return Book.builder()
                .name(this.name)
                .author(this.author)
                .cost(this.cost)
                .genre(this.genre)
                .build();
    }



}
