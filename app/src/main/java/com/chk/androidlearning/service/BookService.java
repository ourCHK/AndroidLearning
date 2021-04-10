package com.chk.androidlearning.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.chk.androidlearning.bean.aidl.Book;
import com.chk.androidlearning.bean.aidl.BookManager;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {

    List<Book> mBookList = new ArrayList<>();

    public BookService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new BookManagerBinder();
    }

    class BookManagerBinder extends BookManager.Stub {

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return mBookList;
        }

    }
}