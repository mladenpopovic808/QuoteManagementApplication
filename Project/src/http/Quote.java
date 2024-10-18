package http;

public class Quote {
    String author;
    String quote;

    public Quote(String author, String quote) {

        this.author = author;

        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }


    public String getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return author + "-" + quote;
    }


}
