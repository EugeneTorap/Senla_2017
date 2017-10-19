public class Book {

    private String title;
    private Boolean isBusy;
    private int Id;

    public Book(String title, int Id) {
        this.title = title;
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return Id;
    }

    public void setBusy(Boolean isBusy) {
        this.isBusy = isBusy;
    }

    public Boolean getBusy() {
        return isBusy;
    }
}
