void Player(int id){
    int PID;
    int coin, coinl, coinr;
    int end, winner;
    int("PID,", ?PID);
    do {
        PID++;
        coin = rand() % 2;
        out("RESULT", id, PID, coin);
        out("RESULT", id, PID, coin);
        in("RESULT", (id + 1)%3, PID, ?coinl);
        in("RESULT", (id + 2)%3, PID, ?coinr);
        end = !((coin == coinl) && (coin == coinr) && (coinr==coinl));
        winner = (coin!=coinl) && (coin !=  coinr);
    } while(!end);
}

void init(){
    out("PID", 0);
    out("PID", 0);
    out("PID", 0);
    eval(Player(0));
    eval(Player(1));
    eval(Player(2));
}