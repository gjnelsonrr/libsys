class Renew{
    Item item;
    User user;
    Renew(Item item, User user){
        this.item = item;
        this.user = user;
    }
    Item getItem(){
        return item;
    }

    User getUser(){
        return user;
    }
}