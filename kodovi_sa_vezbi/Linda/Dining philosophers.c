void phil(int i){
    int i;
    while(1){
        think();
        in("room ticket");
        in("chopstick", i);
        in("chopstick", (i+1)%Num);
        eat();
        out("chopstick", i);
        out("chopstick", (i+1)%Num);
        out("room ticket")
    }
}

void initialize(){
    int i;
    for(int i = 0;i<Num;i++) {
        out("chopstick", i);
        eval(phil(i));
        if(i<(Num-1)) out("room ticket");
    }
}