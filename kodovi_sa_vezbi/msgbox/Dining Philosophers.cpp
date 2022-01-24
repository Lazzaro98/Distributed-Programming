mbx[N] Phil;
void Philosophers(int id){
    boolean status;
    msg m;
    int first = id;
    int second = id+1;
    if(id == N - 1){
        first = 0;
        second = id;
    }
    while(1){
        think();
        m.id = id;
        msg_put(m, ForkGet[first]);
        msg_get(n, Phil[first], INF, status);
        msg_put(m, ForkGet[second]);
        msg_get(n, Phil[second], INF, status);
        //uzme viljuske
        eat();

        //vrati viljuske
        mbx_put(m, ForkPut[first]);
        mbx_put(m, ForkPut[second]);
    }
}

mbx[N] ForkGet;
mbx[N] ForkPut;
void Fork(int id){
    boolean status;
    msg m;, n
    while(){
        mbx_get(m, Fork[id], INF, status);
        mbx_put(n, Phil[m.id]);
        mbx_get(m, ForkPut[id], INF, status);
    }
}