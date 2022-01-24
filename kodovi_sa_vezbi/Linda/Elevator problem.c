
void passenger(unsigned ID, x, y){
    int s1, s2;
    if(x!=y){
        in("start", x, ?s1); 
        if(s1 == 0) out("floor", x); 
        out("start", x, s1+1);

        in("on", x);
        in("start", x, ?s1);
        in("stop", y, ?s2);
        if(s2==0)out("floor", y);
        inp("floor");
    }
}