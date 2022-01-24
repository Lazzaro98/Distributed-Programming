void reader() {
    int id, num;
    while(1) {
        in("id", ?id);
        out("id", id + 1);
        in("ok_to_work", id);
        in("readers_num", num);
        out("readers_num", num + );
        out("ok_to_work", id + 1);
        reading();
        in("readers_num", ?num);
        out("readers_num", num - 1);
    }
}

void writer(){
    int id;
    while(1){
        in("id", ?id);
        out("id". id + 1);
        in("ok_to_work", id);
        rd("readers_num", 0);
        writing();
        out("ok_to_work", id + 1);
    }
}

void init(){
    int i; 
    out("id", 0);
    out("ok_to_work", 0);
    out("readers_num" ,0);
    for(int i = 0;i<10;i++){
        eval(reader());
        eval(writer());
    }
}