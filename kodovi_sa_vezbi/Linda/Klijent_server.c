void client() {
    int index;
    in("client index", ?index);
    out("client index", index+1);

    out("request", index, request);
    in("response", index, ?response);
}

void server(){
    int index = 1;

    while(1){
        in("request",index, ?request);
        out("response",index, response);
        index++;
    }
}

void init(){
    out("client index", 1);
    eval(server());
    for(int i = 0; i<10; i++)
        eval(client());
}