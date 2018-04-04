import { Component, OnInit } from '@angular/core';
import {Response} from "@angular/http";
import {BooksService} from "./books.service";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html'
})
export class BookComponent implements OnInit {
  books = [];

  constructor(private booksService: BooksService){}

  ngOnInit(): void {

  }

  loadBooks() {
    this.booksService.getBooks()
      .subscribe((response: Response) => {
        const data = response.json();
        this.books = data;
      });
  }
}
