abstract class Item{
    int id;
    String title;
    double value;

    Item(int id, String title, double value){
        this.id = id;
        this.title = title;
        this.value = value;
    }

    abstract void display();

    String getTitle(){
        return title;
    }
}