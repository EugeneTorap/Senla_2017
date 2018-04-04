import { Injectable } from '@angular/core';
import {Http} from "@angular/http";

@Injectable()
export class BooksService {

  constructor(private http: Http) { }

  getBooks() {
    return this.http.get("http://localhost:8080/books?orderby=PRICE")
  }
}
