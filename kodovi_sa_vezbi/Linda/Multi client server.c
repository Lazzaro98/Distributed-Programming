void client() {
    int index;
    in("client index", ?index);
    out("client index", index+1);

    out("request", index, request);
    in("response", index, ?response);
}

void server(){
    int index;
    int cnt = ;
    in("num of servers", ?x);
    out("num of servers", x + 1);
    while(1){
        if(rdp("summing")){
            out("num of requests", cnt)
            //blokiramo se dok se ne zavrsi sumator
            rd("not summing"); // blokiraj se dok ne dobijes poruku "not summing"
        }
        else {

            in("request",index, ?request);
            out("response",index, response);
       
            in("server index", ?index);
            out("server index", index+1);
            cnt++;
        }
    }

}


void sumator(){
    int x;
    int sum = 0;
    in("num of servers", ?x);
    out("num of servers", x);
    in("not summing"); // ukinemo prvo summing
    out("summing"); // a onda proglasimo summing stanje
    rd("num of  servers", ?n); // samo procitam, bez brisanja
    for(int i = 0; i < n; i++){
        in("Num of requests", ?x);
        sum += x;
    }

    in("summing"); // ukinemo summing stanje
    out("not summing");
}

void init(){
    out("not summing");
    out("num of servers", 0);
    out("client index", 1);
    out("server index", 1);
    for(int i = 0;i<10;i++)
        eval(server());
    for(int i = 0; i<10; i++)
        eval(client());
        eval(sumator());
}