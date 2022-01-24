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