// BookManager.aidl
package com.chk.androidlearning.bean.aidl;

// Declare any non-default types here with import statements

import com.chk.androidlearning.bean.aidl.Book;

interface BookManager {

    void addBook(in Book book);

    List<Book> getBooks();

}