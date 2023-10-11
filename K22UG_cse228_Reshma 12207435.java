#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Book {
private:
    int bookId;
    string title;
    string author;
    string publicationDate;
    bool available;

public:
    // Constructor
    Book(int id, string t, string a, string date) {
        bookId = id;
        title = t;
        author = a;
        publicationDate = date;
        available = true;
    }

    // Getters and setters for book attributes
    int getBookId() const { return bookId; }
    string getTitle() const { return title; }
    string getAuthor() const { return author; }
    string getPublicationDate() const { return publicationDate; }
    bool isAvailable() const { return available; }
    void setAvailable(bool status) { available = status; }
};

void addBook(vector<Book>& bookList) {
    int id;
    string title, author, date;

    cout << "Enter book ID: ";
    cin >> id;
    cin.ignore();

    cout << "Enter book title: ";
    getline(cin, title);

    cout << "Enter book author: ";
    getline(cin, author);

    cout << "Enter publication date: ";
    getline(cin, date);

    Book newBook(id, title, author, date);
    bookList.push_back(newBook);

    cout << "Book added successfully!" << endl;
}

void displayBooks(const vector<Book>& bookList) {
    if (bookList.empty()) {
        cout << "No books in the library." << endl;
        return;
    }

    cout << "------ Book List ------" << endl;
    for (const Book& book : bookList) {
        cout << "Book ID: " << book.getBookId() << endl;
        cout << "Title: " << book.getTitle() << endl;
        cout << "Author: " << book.getAuthor() << endl;
        cout << "Publication Date: " << book.getPublicationDate() << endl;
        cout << "Availability: " << (book.isAvailable() ? "Available" : "Not Available") << endl;
        cout << endl;
    }
}

void searchBooks(const vector<Book>& bookList, const string& searchTerm) {
    bool found = false;

    for (const Book& book : bookList) {
        // Perform case-insensitive search for book titles
        string lowerTitle = book.getTitle();
        transform(lowerTitle.begin(), lowerTitle.end(), lowerTitle.begin(), ::tolower);

        string lowerSearchTerm = searchTerm;
        transform(lowerSearchTerm.begin(), lowerSearchTerm.end(), lowerSearchTerm.begin(), ::tolower);

        if (lowerTitle.find(lowerSearchTerm) != string::npos) {
            cout << "Book ID: " << book.getBookId() << endl;
            cout << "Title: " << book.getTitle() << endl;
            cout << "Author: " << book.getAuthor() << endl;
            cout << "Publication Date: " << book.getPublicationDate() << endl;
            cout << "Availability: " << (book.isAvailable() ? "Available" : "Not Available") << endl;
            cout << endl;
            found = true;
        }
    }

    if (!found) {
        cout << "No matching books found." << endl;
    }
}

int main() {
    vector<Book> bookList;
    string searchTerm; // Declare searchTerm outside the switch statement

    while (true) {
        cout << "------ Library Book Tracker ------" << endl;
        cout << "1. Add Book" << endl;
        cout << "2. Display Books" << endl;
        cout << "3. Search Books" << endl;
        cout << "4. Exit" << endl;
        cout << "Enter your choice: ";
        int choice;
        cin >> choice;

        switch (choice) {
        case 1:
            addBook(bookList);
            break;
        case 2:
            displayBooks(bookList);
            break;
        case 3:
            cin.ignore();
            cout << "Enter search term: ";
            getline(cin, searchTerm);
            searchBooks(bookList, searchTerm);
            break;
        case 4:
            exit(0);
        default:
            cout << "Invalid choice. Please try again." << endl;
            break;
        }
    }
    return 0;
}