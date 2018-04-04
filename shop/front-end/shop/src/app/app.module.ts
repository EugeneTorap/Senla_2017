import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {BooksService} from "./book/books.service";
import {HttpModule} from "@angular/http";
import { BookComponent } from './book/book.component';
import { OrderComponent } from './order/order.component';
import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    OrderComponent,
    UserComponent
  ],
  imports: [
    HttpModule,
    BrowserModule
  ],
  providers: [BooksService],
  bootstrap: [AppComponent]
})
export class AppModule { }
