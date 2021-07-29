class Request{
    Item item;
    User user;
    Request(Item item, User user){
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
