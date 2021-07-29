class RefItem extends Item{
    RefItem(int id, String title, double value){
        super(id, title, value);
    }

    void display(){
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Value: $" + value);
        System.out.println("Type: Reference");
    }
}